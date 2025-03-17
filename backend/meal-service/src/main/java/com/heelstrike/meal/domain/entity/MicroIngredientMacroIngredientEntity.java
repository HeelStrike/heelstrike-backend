package com.heelstrike.meal.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "micro_ingredient_macro_ingredient")
public class MicroIngredientMacroIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "macro_ingredient_id", nullable = false)
    private MacroIngredientEntity macroIngredient;

    @ManyToOne
    @JoinColumn(name = "micro_ingredient_id", nullable = false)
    private MicroIngredientEntity microIngredient;

    public long getId() {
        return this.id;
    }

    public MacroIngredientEntity getMacroIngredient() {
        return this.macroIngredient;
    }

    public void setMacroIngredient(MacroIngredientEntity macroIngredient) {
        this.macroIngredient = macroIngredient;
    }

    public MicroIngredientEntity getMicroIngredient() {
        return this.microIngredient;
    }

    public void setMicroIngredient(MicroIngredientEntity microIngredient) {
        this.microIngredient = microIngredient;
    }
}
