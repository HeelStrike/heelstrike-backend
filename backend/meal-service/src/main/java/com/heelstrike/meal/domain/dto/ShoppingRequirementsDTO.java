package com.heelstrike.meal.domain.dto;

import java.util.List;

public class ShoppingRequirementsDTO {

    private Float calories;
    private List<String> keywords;
    private List<NutrientDTO> nutrientsRequired;
    private List<AllergenDTO> allergensToAvoid;
    private List<ShopDTO> shopPreferences;
    private List<MicroIngredientDTO> microIngredientsToAvoid;

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<NutrientDTO> getNutrientsRequired() {
        return nutrientsRequired;
    }

    public void setNutrientsRequired(List<NutrientDTO> nutrientsRequired) {
        this.nutrientsRequired = nutrientsRequired;
    }

    public List<AllergenDTO> getAllergensToAvoid() {
        return allergensToAvoid;
    }

    public void setAllergensToAvoid(List<AllergenDTO> allergensToAvoid) {
        this.allergensToAvoid = allergensToAvoid;
    }

    public List<ShopDTO> getShopPreferences() {
        return shopPreferences;
    }

    public void setShopPreferences(List<ShopDTO> shopPreferences) {
        this.shopPreferences = shopPreferences;
    }

    public List<MicroIngredientDTO> getMicroIngredientsToAvoid() {
        return microIngredientsToAvoid;
    }

    public void setMicroIngredientsToAvoid(List<MicroIngredientDTO> microIngredientsToAvoid) {
        this.microIngredientsToAvoid = microIngredientsToAvoid;
    }
}

