package com.superlapp.meal.domain.dto;

import com.superlapp.core.BaseDTO;
import com.superlapp.meal.domain.entity.AllergenEntity;
import com.superlapp.meal.domain.entity.MicroIngredientEntity;

import java.util.List;

public class MacroIngredientDTO extends BaseDTO {

    public List<AllergenEntity> allergens;
    public List<MicroIngredientEntity> microIngredients;

    public MacroIngredientDTO(int id, String name, List<AllergenEntity> allergens) {
        super(id, name);
    }

    public List<AllergenEntity> getAllergens() {
        return this.allergens;
    }

    public void setAllergens(List<AllergenEntity> newAllergens) {
        this.allergens = newAllergens;
    }

}
