package com.heelstrike.meal.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class RecipeRequirementsDTO {
    private String[] cookingTimeRange = new String[2];
    private String[] preparationTimeRange = new String[2];

    private int servesModifier;
    private String difficulty;
    private List<NutrientDTO> nutrients;
    private List<AllergenDTO> allergensToAvoid;
    private List<DietDTO> suitableForTheseDiets;

    public String[] getCookingTimeRange() {
        return this.cookingTimeRange;
    }

    public void setCookingTimeRange(String[] cookingTimeRange) {
        this.cookingTimeRange = cookingTimeRange;
    }

    public String[] getPreparationTimeRange() {
        return this.preparationTimeRange;
    }

    public void setPreparationTimeRange(String[] preparationTimeRange) {
        this.preparationTimeRange = preparationTimeRange;
    }

    public int getServesModifier() {
        return servesModifier;
    }

    public void setServesModifier(int servesModifier) {
        this.servesModifier = servesModifier;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<NutrientDTO> getNutrients() {
        return this.nutrients;
    }

    public void setNutrients(List<NutrientDTO> nutrients) {
        this.nutrients = nutrients;
    }

    public List<AllergenDTO> getAllergensToAvoid() {
        return this.allergensToAvoid;
    }

    public void setAllergensToAvoid(List<AllergenDTO> allergensToAvoid) {
        this.allergensToAvoid = allergensToAvoid;
    }

    public List<DietDTO> getSuitableForTheseDiets() {
        return suitableForTheseDiets;
    }

    public void setSuitableForTheseDiets(List<DietDTO> suitableForTheseDiets) {
        this.suitableForTheseDiets = suitableForTheseDiets;
    }
}
