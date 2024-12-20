package com.superlapp.meal.domain.dto;

import com.superlapp.core.BaseDTO;
import com.superlapp.meal.domain.entity.AllergenEntity;

import java.util.List;

public class MicroIngredientDTO extends BaseDTO {

    public List<AllergenEntity> allergens;

    public MicroIngredientDTO(int id, String name) {
        super(id, name);
    }

    public List<AllergenEntity> getAllergens() {
        return this.allergens;
    }

    public void setAllergens(List<AllergenEntity> newAllergens) {
        this.allergens = newAllergens;
    }
}
