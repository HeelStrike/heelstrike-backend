package com.heelstrike.meal.application.mapper;

import com.heelstrike.meal.application.service.RecipeService;
import com.heelstrike.meal.domain.dto.*;
import com.heelstrike.meal.domain.entity.DietEntity;
import com.heelstrike.meal.domain.entity.DifficultyEntity;
import com.heelstrike.meal.domain.entity.MacroIngredientEntity;
import com.heelstrike.meal.domain.entity.RecipeEntity;
import com.heelstrike.meal.domain.repository.DietRepository;
import com.heelstrike.meal.domain.repository.DifficultyRepository;
import com.heelstrike.meal.domain.repository.MacroIngredientRepository;
import com.heelstrike.meal.domain.repository.RecipeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class RecipeMapper {

    private static final Logger LOG = Logger.getLogger(RecipeMapper.class);

    @Inject
    DietRepository dietRepository;

    @Inject
    DifficultyRepository difficultyRepository;

    @Inject
    MacroIngredientRepository macroIngredientRepository;


    public RecipeEntity recipeEntityFromDTO(RecipeDTO recipeDTO) {
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

    public RecipeDTO recipeDTOFromEntity(RecipeEntity recipeEntity) {
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
