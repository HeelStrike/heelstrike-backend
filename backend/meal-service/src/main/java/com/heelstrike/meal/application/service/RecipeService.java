package com.heelstrike.meal.application.service;

import com.heelstrike.meal.domain.dto.*;
import com.heelstrike.meal.domain.entity.*;
import com.heelstrike.meal.domain.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.util.*;
import java.util.stream.Collectors;


@ApplicationScoped
public class RecipeService {

    private static final Logger LOG = Logger.getLogger(RecipeService.class);

    @Inject
    RecipeRepository recipeRepository;

    @Inject
    MacroIngredientRepository macroIngredientRepository;

    @Inject
    DifficultyRepository difficultyRepository;

    @Inject
    DietRepository dietRepository;

    @Inject
    MicroIngredientRepository microIngredientRepository;

    public void addRecipe(RecipeDTO recipeDTO) {

        LOG.info("Adding new recipe by title: " + recipeDTO.toString());

        if (recipeRepository.findByTitle(recipeDTO.getTitle()).isEmpty()) {
            RecipeEntity recipeEntity = recipeEntityFromDTO(recipeDTO);
            try {
                recipeRepository.persistRecipeEntity(recipeEntity);
            } catch (PersistenceException e) {
                throw new PersistenceException("Failed to persist recipe.", e);
            }
        } else {
            throw new RuntimeException("Recipe already exists.");
        }
    }

    public List<RecipeEntity> getRecipesByRequirements(RecipeRequirementsDTO requirements) {

        LOG.info("Searching for recipe based on requirements.");

        String query = "SELECT DISTINCT r FROM RecipeEntity r " +
                "LEFT JOIN FETCH r.macroIngredients m " +
                "LEFT JOIN FETCH m.microIngredients mi " +
                "LEFT JOIN FETCH mi.nutrients n " +
                "LEFT JOIN FETCH r.dietarySuitability dts " +
                "LEFT JOIN FETCH mi.allergens a " +
                "WHERE 1=1";

        Map<String, Object> params = new HashMap<>();

        if (requirements.getAllergensToAvoid() != null && !requirements.getAllergensToAvoid().isEmpty()) {
            query += "AND COALESCE(a.name, '') NOT IN (:allergens) ";
            params.put("allergens",
                    requirements.getAllergensToAvoid()
                            .stream()
                            .map(AllergenDTO::getName)
                            .toList()
            );
        }

        if (requirements.getSuitableForTheseDiets() != null && !requirements.getSuitableForTheseDiets().isEmpty()) {
            query += "AND COALESCE(dts.name, '') IN (:diets) ";
            params.put("diets", requirements.getSuitableForTheseDiets()
                    .stream()
                    .map(DietDTO::getName)
                    .toList()
            );
        }

        if (requirements.getNutrients() != null && !requirements.getNutrients().isEmpty()) {
            query += "AND n.id IN (:nutrientIds) ";
            params.put("nutrientIds",
                    requirements.getNutrients()
                            .stream()
                            .map(NutrientDTO::getId)
                            .toList()
            );
        }

        if (requirements.getDifficulty() != null && !requirements.getDifficulty().isEmpty()) {
            query += "AND r.difficulty.name = :difficulty ";
            params.put("difficulty", requirements.getDifficulty());
        }

        return recipeRepository.findByDynamicQuery(query, params);
    }

    private RecipeEntity recipeEntityFromDTO(RecipeDTO recipeDTO) {
        RecipeEntity recipeEntity = new RecipeEntity();
        LOG.info("Converting Recipe Data Transfer Object to Recipe Entity class: " + recipeDTO.toString());

        try {
            recipeEntity.setTitle(recipeDTO.getTitle());
            recipeEntity.setDescription(recipeDTO.getDescription());
            recipeEntity.setCookingInstructions(recipeDTO.getCookingInstructions());
            recipeEntity.setPreparationTime(recipeDTO.getPreparationTime());
            recipeEntity.setCookingTime(recipeDTO.getCookingTime());
            recipeEntity.setServes(recipeDTO.getServes());

            LOG.debug("Set Title, Description, Cooking Instructions, Preparation Time, Cooking Time and Serves to RecipeEntity.");

            DifficultyEntity difficulty = difficultyRepository.findByName(recipeDTO.getDifficulty())
                    .orElseThrow(() -> new RuntimeException("Difficulty level not found." + recipeDTO.getDifficulty()));

            recipeEntity.setDifficulty(difficulty);
            LOG.debug("Resolved difficulty level, RecipeEntity.setDifficulty=" + recipeEntity.getDifficulty());

            Set<MacroIngredientEntity> macroIngredients = recipeDTO.getMacroIngredients()
                    .stream()
                    .map(mi -> macroIngredientRepository.findByName(mi.getName())
                            .orElseThrow(() -> new RuntimeException("MacroIngredient not found: " + mi.getName())))
                    .collect(Collectors.toSet());

            recipeEntity.setMacroIngredients(macroIngredients);
            LOG.debug("Resolved Macro Ingredients, RecipeEntity.setMacroIngredients=" + recipeEntity.getMacroIngredients());

            Set<DietEntity> dietarySuitability = recipeDTO.getDietarySuitability()
                            .stream()
                                    .map(d -> dietRepository.findByName(d.getName())
                                            .orElseThrow(() -> new RuntimeException("Diet not found: " + d.getName())))
                                            .collect(Collectors.toSet());

            recipeEntity.setDietarySuitability(dietarySuitability);
            LOG.debug("Resolved Diets, RecipeEntity.setDietarySuitability=" + recipeEntity.getDietarySuitability());

        } catch (Exception e) {
            LOG.error("Failed to convert RecipeDTO to RecipeEntity " + e);
            throw new RuntimeException("Failed to convert RecipeDTO to RecipeEntity ", e);
        }

        return recipeEntity;
    }

    private RecipeDTO recipeDTOFromEntity(RecipeEntity recipeEntity) {
        RecipeDTO recipeDTO = new RecipeDTO();

        LOG.info("Converting Recipe Entity to Recipe Data Transfer Object: ");

        try {
            recipeDTO.setTitle(recipeEntity.getTitle());
            recipeDTO.setDescription(recipeEntity.getDescription());
            recipeDTO.setCookingInstructions(recipeEntity.getCookingInstructions());
            recipeDTO.setPreparationTime(recipeEntity.getPreparationTime());
            recipeDTO.setCookingTime(recipeEntity.getPreparationTime());
            recipeDTO.setServes(recipeEntity.getServes());
            recipeDTO.setDifficulty(recipeEntity.getDifficulty().getName());

            LOG.debug("Set Title, Description, Cooking Instructions, Preparation Time, Cooking Time, Serves and Difficulty to RecipeDTO.");


            /* TODO: Complete mapping for:
             * Allergens,
             * MicroIngredients
             * DietarySuitability
             * TODO: Refactor this out into mapper class because it's currently messy.
             * */
            Set<MacroIngredientDTO> macroIngredientDTOs = recipeEntity.getMacroIngredients()
                    .stream()
                    .map(macroIngredient -> {
                        MacroIngredientDTO macroIngredientDTO = new MacroIngredientDTO();
                        macroIngredientDTO.setId(macroIngredient.getId());
                        macroIngredientDTO.setName(macroIngredient.getName());

                        Set<MicroIngredientDTO> microIngredientDTOs = macroIngredient.getMicroIngredients()
                                .stream()
                                .map(microIngredient -> {
                                    MicroIngredientDTO microIngredientDTO = new MicroIngredientDTO();
                                    microIngredientDTO.setId(microIngredient.getId());
                                    microIngredientDTO.setName(microIngredient.getName());

                                    Set<AllergenDTO> allergenDTOs = microIngredient.getAllergens()
                                            .stream()
                                            .map(allergen -> {
                                                AllergenDTO allergenDTO = new AllergenDTO();
                                                allergenDTO.setId(allergen.getId());
                                                allergenDTO.setName(allergen.getName());
                                                return allergenDTO;
                                            })
                                            .collect(Collectors.toSet());

                                    microIngredientDTO.setAllergens(allergenDTOs);
                                    return microIngredientDTO;
                                })
                                .collect(Collectors.toSet());

                        macroIngredientDTO.setMicroIngredients(microIngredientDTOs);
                        return macroIngredientDTO;
                    })
                    .collect(Collectors.toSet());

            Set<DietDTO> dietarySuitability = recipeEntity.getDietarySuitability()
                    .stream()
                    .map(dietEntity -> {
                        DietDTO diet = new DietDTO();
                        diet.setId(dietEntity.getId());
                        diet.setName(dietEntity.getName());
                        return diet;
                    })
                    .collect(Collectors.toSet());

            return recipeDTO;

        } catch (Exception e) {
            LOG.error("Couldn't map RecipeEntity to RecipeDTO." + e);
            throw new RuntimeException("Could not map RecipeEntity to RecipeDTO.", e);
        }
    }
}

