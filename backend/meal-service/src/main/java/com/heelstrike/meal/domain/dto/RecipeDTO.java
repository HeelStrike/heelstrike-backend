package com.heelstrike.meal.domain.dto;

import java.util.List;
import java.util.Set;

public class RecipeDTO {
    private long id;
    private String title;
    private String description;
    private String cookingInstructions;
    private String cookingTime;
    private String preparationTime;
    private int serves;
    private String difficulty;
    private Set<MacroIngredientDTO> macroIngredients;
    private Set<MicroIngredientDTO> microIngredients;
    private Set<AllergenDTO> allergens;
    private Set<DietDTO> dietarySuitability;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCookingInstructions() {
        return this.cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public String getCookingTime() {
        return this.cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getServes() {
        return this.serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Set<MacroIngredientDTO> getMacroIngredients() {
        return macroIngredients;
    }

    public void setMacroIngredients(Set<MacroIngredientDTO> macroIngredients) {
        this.macroIngredients = macroIngredients;
    }

    public Set<MicroIngredientDTO> getMicroIngredients() {
        return microIngredients;
    }

    public void setMicroIngredients(Set<MicroIngredientDTO> microIngredients) {
        this.microIngredients = microIngredients;
    }

    public Set<AllergenDTO> getAllergens() {
        return this.allergens;
    }

    public void setAllergens(Set<AllergenDTO> allergens) {
        this.allergens = allergens;
    }

    public Set<DietDTO> getDietarySuitability() {
        return dietarySuitability;
    }

    public void setDietarySuitability(Set<DietDTO> dietarySuitability) {
        this.dietarySuitability = dietarySuitability;
    }
}
