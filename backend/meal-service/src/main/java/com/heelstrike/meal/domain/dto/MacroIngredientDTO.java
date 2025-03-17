package com.heelstrike.meal.domain.dto;

import java.util.List;

public class MacroIngredientDTO {
    private long id;
    private String name;
    private List<MicroIngredientDTO> microIngredients;

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

    public List<MicroIngredientDTO> getMicroIngredients() {
        return this.microIngredients;
    }

    public void setMicroIngredients(List<MicroIngredientDTO> microIngredients) {
        this.microIngredients = microIngredients;
    }
}
