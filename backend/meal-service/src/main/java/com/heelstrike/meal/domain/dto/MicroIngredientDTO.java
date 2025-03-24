package com.heelstrike.meal.domain.dto;

import java.util.List;
import java.util.Set;

public class MicroIngredientDTO {
    private long id;
    private String name;
    private Set<AllergenDTO> allergens;

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

    public Set<AllergenDTO> getAllergens() {
        return this.allergens;
    }

    public void setAllergens(Set<AllergenDTO> allergens) {
        this.allergens = allergens;
    }
}
