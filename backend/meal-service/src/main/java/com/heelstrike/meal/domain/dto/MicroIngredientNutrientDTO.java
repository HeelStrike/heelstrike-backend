package com.heelstrike.meal.domain.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.heelstrike.meal.domain.dto.NutrientDTO;

public class MicroIngredientNutrientDTO {
    private long id;
    private Map<NutrientDTO, Double> nutrientsInMicroIngredient = new HashMap<>();

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<NutrientDTO, Double> getNutrientsInMicroIngredient() {
        return this.nutrientsInMicroIngredient;
    }

    public void setNutrientsInMicroIngredient(Map<NutrientDTO, Double> nutrientsInMicroIngredient) {
        this.nutrientsInMicroIngredient = nutrientsInMicroIngredient;
    }
}
