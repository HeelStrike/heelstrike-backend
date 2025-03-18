package com.heelstrike.meal.domain.dto;

import java.time.Duration;
import java.util.List;

public class RecipeRequirementsDTO {
    private Duration[] cookingTimeRange = new Duration[2];
    private Duration[] preparationTimeRange = new Duration[2];
    private int servesModifier;
    private String difficulty;
    private List<NutrientDTO> nutrients;
    private List<AllergenDTO> allergensToAvoid;
    private List<DietDTO> suitableForTheseDiets;

    public Duration[] getCookingTimeRange() {
        return this.cookingTimeRange;
    }

    public void setCookingTimeRange(Duration[] cookingTimeRange) {
        this.cookingTimeRange = cookingTimeRange;
    }

    public Duration[] getPreparationTimeRange() {
        return this.preparationTimeRange;
    }

    public void setPreparationTimeRange(Duration[] preparationTimeRange) {
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
        return allergensToAvoid;
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
