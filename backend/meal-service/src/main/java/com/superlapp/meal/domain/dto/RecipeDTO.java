package com.superlapp.meal.domain.dto;

import com.superlapp.core.BaseDTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeDTO extends BaseDTO {

    private String cookingInstructions;
    private List<AllergenDTO> allergens = new ArrayList<>();
    private List<DietDTO> dietarySuitability = new ArrayList<>();
    private String recipeImage;
    private List<MicroIngredientDTO> microIngredients;
    private List<MacroIngredientDTO> macroIngredients;

    /*
    * id: 1
    * name: Penne and Meatball Ragu
    * cooking_instructions: 1. Boil 500ml of water with a pinch of salt. 2. Place the pasta in the pot, leave to boil for 13 minutes. 3...
    * ingredients: Penne Pasta, Tinnned Tomatos, Tomato, Thyme, Oregano, Parsley, Basil, Smoked Paprika, Pasata, Black Pepper, Salt, Minced Meat
    * allergens: Wheat, Gluten
    * dietary_suitability: Halal, Kosher
    * */
    public RecipeDTO(int id, String name, String cookingInstructions, String recipeImage, List<DietDTO> dietarySuitability, List<AllergenDTO> allergens, List<MacroIngredientDTO> macroIngredients, List<MicroIngredientDTO> microIngredients){
        super(id, name);
        this.cookingInstructions = cookingInstructions;
        this.microIngredients = microIngredients;
        this.macroIngredients = macroIngredients;
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

    public void setMicroIngredients(List<MicroIngredientDTO> newMicroIngredients) {
        this.microIngredients = newMicroIngredients;
    }

    public List<MicroIngredientDTO> getMicroIngredients() {
        return this.microIngredients;
    }

    public void setMacroIngredients(List<MacroIngredientDTO> newMacroIngredients) {
        this.macroIngredients = newMacroIngredients;
    }

    public List<MacroIngredientDTO> getMacroIngredients() {
        return this.macroIngredients;
    }

    public void setAllergens(List<AllergenDTO> newAllergens) {
        this.allergens = newAllergens;
    }

    public List<AllergenDTO> getAllergens() {
        return this.allergens;
    }

    public List<DietDTO> getDietarySuitability() {
        return this.dietarySuitability;
    }

    public void setDietarySuitability(List<DietDTO> newDietarySuitability) {
        this.dietarySuitability = newDietarySuitability;
    }

    public void setRecipeImage(String newRecipeImage) {
        this.recipeImage = newRecipeImage;
    }

    public String getRecipeImage() {
        return this.recipeImage;
    }


}
