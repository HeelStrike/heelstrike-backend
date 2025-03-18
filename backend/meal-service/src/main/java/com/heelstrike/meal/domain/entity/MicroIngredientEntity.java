package com.heelstrike.meal.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "micro_ingredient")
public class MicroIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "microIngredients")
    private List<MacroIngredientEntity> macroIngredients;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MacroIngredientEntity> getMacroIngredients() {
        return this.macroIngredients;
    }

    public void setMacroIngredients(List<MacroIngredientEntity> macroIngredients) {
        this.macroIngredients = macroIngredients;
    }
}
