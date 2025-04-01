package com.heelstrike.meal.domain.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.Set;

@Schema(description = "A major ingredient in a recipe, typically a combination of micro-ingredients (e.g., Tomato Pasata, Pesto, Chunky Chips etc...).")
public class MacroIngredientDTO {
    private long id;
    private String name;
    private Set<MicroIngredientDTO> microIngredients;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<MicroIngredientDTO> getMicroIngredients() {
        return this.microIngredients;
    }

    public void setMicroIngredients(Set<MicroIngredientDTO> microIngredients) {
        this.microIngredients = microIngredients;
    }
}
