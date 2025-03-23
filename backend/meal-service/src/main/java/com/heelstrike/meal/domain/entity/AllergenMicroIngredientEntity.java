package com.heelstrike.meal.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "allergen_micro_ingredient")
public class AllergenMicroIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "allergen_id", nullable = false)
    @JsonBackReference
    private AllergenEntity allergen_id;

    @ManyToOne
    @JoinColumn(name = "micro_ingredient_id", nullable = false)
    private MicroIngredientEntity microIngredient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MicroIngredientEntity getMicroIngredient() {
        return microIngredient;
    }

    public void setMicroIngredient(MicroIngredientEntity microIngredient) {
        this.microIngredient = microIngredient;
    }

    public AllergenEntity getAllergen_id() {
        return allergen_id;
    }

    public void setAllergen_id(AllergenEntity allergen_id) {
        this.allergen_id = allergen_id;
    }
}
