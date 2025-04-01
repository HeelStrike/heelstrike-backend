package com.heelstrike.meal.domain.datastructure;

import com.heelstrike.meal.domain.entity.RecipeEntity;
import com.heelstrike.meal.domain.entity.ShopBoughtIngredientEntity;

import java.util.List;

public class Meal {
    private RecipeEntity recipe;
    private List<ShopBoughtIngredientEntity> shopBoughtIngredients;

    public Meal(RecipeEntity recipe, List<ShopBoughtIngredientEntity> shopBoughtIngredients) {
        this.recipe = recipe;
        this.shopBoughtIngredients = shopBoughtIngredients;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

    public List<ShopBoughtIngredientEntity> getShopBoughtIngredients() {
        return shopBoughtIngredients;
    }

    public void setShopBoughtIngredients(List<ShopBoughtIngredientEntity> shopBoughtIngredients) {
        this.shopBoughtIngredients = shopBoughtIngredients;
    }
}
