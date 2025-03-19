package com.heelstrike.meal.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nutrient_micro_ingredient")
public class NutrientMicroIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "micro_ingredient_id", nullable = false)
    private MicroIngredientEntity microIngredient;

    @ManyToOne
    @JoinColumn(name = "nutrient_id", nullable = false)
    private NutrientEntity nutrient;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setMicroIngredient(MicroIngredientEntity microIngredient) {
        this.microIngredient = microIngredient;
    }

    public MicroIngredientEntity getMicroIngredient() {
        return this.microIngredient;
    }

    public void setNutrient(NutrientEntity nutrient) {
        this.nutrient = nutrient;
    }

    public NutrientEntity getNutrient() {
        return this.nutrient;
    }
}
