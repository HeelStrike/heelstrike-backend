package com.heelstrike.meal.domain.dto;

import com.heelstrike.meal.domain.entity.DietEntity;
import com.heelstrike.meal.domain.entity.MacroIngredientEntity;
import com.heelstrike.meal.domain.entity.MicroIngredientEntity;

import java.util.List;

public class RecipeDTO {
    private long id;
    private String title;
    private String description;
    private String cookingInstructions;
    private String cookingtime;
    private String preparationTime;
    private int serves;
    private List<MacroIngredientDTO> macroIngredients;
    private List<MicroIngredientDTO> microIngredients;
    private List<AllergenDTO> allergens;
    private List<DietDTO> dietarySuitability;


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

    public String getCookingtime() {
        return this.cookingtime;
    }

    public void setCookingtime(String cookingtime) {
        this.cookingtime = cookingtime;
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

    public List<MacroIngredientDTO> getMacroIngredients() {
        return macroIngredients;
    }

    public void setMacroIngredients(List<MacroIngredientDTO> macroIngredients) {
        this.macroIngredients = macroIngredients;
    }

    public List<MicroIngredientDTO> getMicroIngredients() {
        return microIngredients;
    }

    public void setMicroIngredients(List<MicroIngredientDTO> microIngredients) {
        this.microIngredients = microIngredients;
    }

    public List<DietDTO> getDietarySuitability() {
        return dietarySuitability;
    }

    public void setDietarySuitability(List<DietDTO> dietarySuitability) {
        this.dietarySuitability = dietarySuitability;
    }
}
