package com.heelstrike.meal.application.service;

import com.heelstrike.meal.application.mapper.RecipeMapper;
import com.heelstrike.meal.domain.dto.*;
import com.heelstrike.meal.domain.entity.*;
import com.heelstrike.meal.domain.repository.*;
import com.heelstrike.meal.util.UpdateUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

import static com.heelstrike.meal.util.UpdateUtils.updateIfNotNull;


@ApplicationScoped
public class RecipeService {

    private static final Logger LOG = Logger.getLogger(RecipeService.class);

    @Inject
    RecipeRepository recipeRepository;

    @Inject
    RecipeMapper recipeMapper;

    public void addRecipe(RecipeDTO recipeDTO) {

        LOG.info("Adding new recipe by title: " + recipeDTO.toString());

        if (recipeRepository.findByTitle(recipeDTO.getTitle()).isEmpty()) {
            RecipeEntity recipeEntity = recipeMapper.recipeEntityFromDTO(recipeDTO);
            try {
                recipeRepository.persistRecipeEntity(recipeEntity);
            } catch (PersistenceException e) {
                throw new PersistenceException("Failed to persist recipe.", e);
            }
        } else {
            throw new RuntimeException("Recipe already exists.");
        }
    }

    public RecipeDTO getRecipe(Long id) {

        LOG.info("Getting recipe with ID:" + id);

        RecipeEntity recipeEntity = recipeRepository.findById(id);

        if (recipeEntity == null) {
            LOG.error("Recipe not found with ID: " + id);
            throw new NotFoundException("Recipe not found with ID: " + id);
        }

        return recipeMapper.recipeDTOFromEntity(recipeEntity);
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

    @Transactional
    public RecipeDTO updateRecipe(RecipeDTO recipeDTO) {
        Optional<RecipeEntity> recipeEntityOptional = recipeRepository.find("id", recipeDTO.getId()).firstResultOptional();

        LOG.debug("Updating recipe with ID: " + recipeDTO.getId());

        if (recipeEntityOptional.isPresent()) {
            final RecipeEntity recipeEntity = getRecipe(recipeDTO, recipeEntityOptional);

            LOG.debug("Successfully updated recipe with ID: " + recipeDTO.getId());
            return recipeMapper.recipeDTOFromEntity(recipeEntity);
        } else {
            LOG.error("Recipe not found with ID: " + recipeDTO.getId());
            throw new NotFoundException("Recipe not found with ID: " + recipeDTO.getId());
        }
    }

    private static @NotNull RecipeEntity getRecipe(RecipeDTO recipeDTO, Optional<RecipeEntity> recipeEntityOptional) {
            RecipeEntity recipeEntity = recipeEntityOptional.get();

            recipeEntity.setTitle(updateIfNotNull(recipeDTO.getTitle(), recipeEntity.getTitle()));
            recipeEntity.setDescription(updateIfNotNull(recipeDTO.getDescription(), recipeEntity.getDescription()));
            recipeEntity.setCookingInstructions(updateIfNotNull(recipeDTO.getCookingInstructions(), recipeEntity.getCookingInstructions()));
            recipeEntity.setCookingTime(updateIfNotNull(recipeDTO.getCookingTime(), recipeEntity.getCookingTime()));
            recipeEntity.setPreparationTime(updateIfNotNull(recipeDTO.getPreparationTime(), recipeEntity.getPreparationTime()));
            recipeEntity.setServes(updateIfNotNull(recipeDTO.getServes(), recipeEntity.getServes()));

            return recipeEntity;
    }
}

