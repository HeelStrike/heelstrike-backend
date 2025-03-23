package com.heelstrike.meal.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "nutrient")
public class NutrientEntity {
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

    @ManyToMany
    @JoinTable(
            name = "nutrient_micro_ingredient",
            joinColumns = @JoinColumn(name = "nutrient_id"),
            inverseJoinColumns = @JoinColumn(name = "micro_ingredient_id")
    )
    @JsonBackReference
    private Set<MicroIngredientEntity> microIngredients;

    public long getId() {
        return id;
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

    public Set<MicroIngredientEntity> getMicroIngredients() {
        return this.microIngredients;
    }

    public void setMicroIngredients(Set<MicroIngredientEntity> microIngredients) {
        this.microIngredients = microIngredients;
    }
}
