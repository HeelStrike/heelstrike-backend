package com.heelstrike.meal.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "micro_ingredient")
public class MicroIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "microIngredients")
    @JsonBackReference(value = "macro-micro")
    private Set<MacroIngredientEntity> macroIngredients;

    @ManyToMany
    @JoinTable(
            name = "nutrient_micro_ingredient",
            joinColumns = @JoinColumn(name = "micro_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "nutrient_id")
    )
    private Set<NutrientEntity> nutrients;

    @ManyToMany
    @JoinTable(
            name = "allergen_micro_ingredient",
            joinColumns = @JoinColumn(name = "micro_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    @JsonManagedReference
    private Set<AllergenEntity> allergens;

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

    public Set<MacroIngredientEntity> getMacroIngredients() {
        return this.macroIngredients;
    }

    public void setMacroIngredients(Set<MacroIngredientEntity> macroIngredients) {
        this.macroIngredients = macroIngredients;
    }

    public Set<NutrientEntity> getNutrients() {
        return this.nutrients;
    }

    public void setNutrients(Set<NutrientEntity> nutrients) {
        this.nutrients = nutrients;
    }

    public Set<AllergenEntity> getAllergens() {
        return allergens;
    }

    public void setAllergens(Set<AllergenEntity> allergens) {
        this.allergens = allergens;
    }
}
