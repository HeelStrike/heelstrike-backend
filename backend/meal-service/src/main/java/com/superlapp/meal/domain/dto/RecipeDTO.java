package com.superlapp.meal.domain;

import com.superlapp.core.BaseDTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeDTO extends BaseDTO {

    private String cookingInstructions;
    private List<String> ingredients = new ArrayList<>();
    private List<String> allergens = new ArrayList<>();
    private List<String> dietarySuitability = new ArrayList<>();
    private String recipeImage;

    /*
    * id: 1
    * name: Penne and Meatball Ragu
    * cooking_instructions: 1. Boil 500ml of water with a pinch of salt. 2. Place the pasta in the pot, leave to boil for 13 minutes. 3...
    * ingredients: Penne Pasta, Tinnned Tomatos, Tomato, Thyme, Oregano, Parsley, Basil, Smoked Paprika, Pasata, Black Pepper, Salt, Minced Meat
    * allergens: Wheat, Gluten
    * dietary_suitability: Halal, Kosher
    * */
    public RecipeDTO(int id, String name, String cookingInstructions, List<String> allergens, List<String> dietarySuitability, String recipeImage){
        super(id, name);
        this.cookingInstructions = cookingInstructions;
        this.allergens = allergens;
        this.dietarySuitability = dietarySuitability;
        this.recipeImage = recipeImage;
    }

    public void setCookingInstructions(String newCookingInstructions) {
        this.cookingInstructions = newCookingInstructions;
    }

    public String getCookingInstructions() {
        return this.cookingInstructions;
    }

    public void setIngredients(List<String> newIngredients) {
        this.ingredients = newIngredients;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public void setAllergens(List<String> newAllergens) {
        this.allergens = newAllergens;
    }

    public List<String> getAllergens() {
        return this.allergens;
    }

    public List<String> getDietarySuitability() {
        return this.dietarySuitability;
    }

    public void setDietarySuitability(List<String> newDietarySuitability) {
        this.dietarySuitability = newDietarySuitability;
    }

    public void setRecipeImage(String newRecipeImage) {
        this.recipeImage = newRecipeImage;
    }

    public String getRecipeImage() {
        return this.recipeImage;
    }

}
