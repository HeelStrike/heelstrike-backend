package com.heelstrike.meal.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "macro_ingredient")
public class MacroIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "micro_ingredient_macro_ingredient",
            joinColumns = @JoinColumn(name = "macro_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "micro_ingredient_id")
    )
    private List<MicroIngredientEntity> microIngredients;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MicroIngredientEntity> getMicroIngredients() {
        return microIngredients;
    }
    public void setMicroIngredients(List<MicroIngredientEntity> microIngredients) {
        this.microIngredients = microIngredients;
    }
}
