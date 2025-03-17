package com.heelstrike.meal.domain.dto;

import java.util.List;

public class MicroIngredientDTO {
    private long id;
    private String name;
    private List<AllergenDTO> allergens;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AllergenDTO> getAllergens() {
        return this.allergens;
    }

    public void setAllergens(List<AllergenDTO> allergens) {
        this.allergens = allergens;
    }
}
