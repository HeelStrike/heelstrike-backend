package com.heelstrike.meal.application.producer;

import com.heelstrike.meal.application.service.RecipeService;
import com.heelstrike.meal.domain.dto.RecipeRequirementsDTO;
import com.heelstrike.meal.domain.entity.RecipeEntity;
import jakarta.inject.Inject;

import java.util.List;

public class MealPlanProducer {

    @Inject
    RecipeService recipeService;

    /**
     * <h1>Eligible Recipes Producer</h1>
     * Produces List of eligible recipes
     * @return - List of RecipeEntity objects based upon requirements.
     */
    public List<RecipeEntity> produceEligibleRecipes(RecipeRequirementsDTO recipeRequirements) {
        return recipeService.getRecipesByRequirements(recipeRequirements);
    }
}
