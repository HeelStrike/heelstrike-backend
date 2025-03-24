package com.heelstrike.meal.application.service;

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
import jakarta.persistence.PersistenceException;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class RecipeService {
    @Inject
    RecipeRepository recipeRepository;

    @Inject
    MacroIngredientRepository macroIngredientRepository;

    @Inject
    DifficultyRepository difficultyRepository;

    @Inject
    DietRepository dietRepository;

    public void addRecipe(RecipeDTO recipeDTO) {
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

        try {
            recipeEntity.setTitle(recipeDTO.getTitle());
            recipeEntity.setDescription(recipeDTO.getDescription());
            recipeEntity.setCookingInstructions(recipeDTO.getCookingInstructions());
            recipeEntity.setPreparationTime(recipeDTO.getPreparationTime());
            recipeEntity.setCookingTime(recipeDTO.getCookingTime());
            recipeEntity.setServes(recipeDTO.getServes());

            DifficultyEntity difficulty = difficultyRepository.findByName(recipeDTO.getDifficulty())
                    .orElseThrow(() -> new RuntimeException("Difficulty level not found." + recipeDTO.getDifficulty()));

            recipeEntity.setDifficulty(difficulty);

            Set<MacroIngredientEntity> macroIngredients = recipeDTO.getMacroIngredients()
                    .stream()
                    .map(mi -> macroIngredientRepository.findByName(mi.getName())
                            .orElseThrow(() -> new RuntimeException("MacroIngredient not found: " + mi.getName())))
                    .collect(Collectors.toSet());

            recipeEntity.setMacroIngredients(macroIngredients);

            Set<DietEntity> dietarySuitability = recipeDTO.getDietarySuitability()
                            .stream()
                                    .map(d -> dietRepository.findByName(d.getName())
                                            .orElseThrow(() -> new RuntimeException("Diet not found: " + d.getName())))
                                            .collect(Collectors.toSet());

            recipeEntity.setDietarySuitability(dietarySuitability);

        } catch (Exception e) {
            throw new RuntimeException("Failed to convert RecipeDTO to RecipeEntity", e);
        }

        return recipeEntity;
    }
}

