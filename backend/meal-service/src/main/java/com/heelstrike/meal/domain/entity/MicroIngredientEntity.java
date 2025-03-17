package com.heelstrike.meal.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "micro_ingredient")
public class MicroIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "macro_ingredient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MicroIngredientMacroIngredientEntity> macroIngredientLinks;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MicroIngredientMacroIngredientEntity> getMacroIngredientLinks() {
        return this.macroIngredientLinks;
    }

    public void setMacroIngredientLinks(List<MicroIngredientMacroIngredientEntity> macroIngredientLinks) {
        this.macroIngredientLinks = macroIngredientLinks;
    }
}
