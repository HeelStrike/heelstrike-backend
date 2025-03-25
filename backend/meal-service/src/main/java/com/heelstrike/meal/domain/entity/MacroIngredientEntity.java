package com.heelstrike.meal.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

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
    @JsonManagedReference(value = "macro-micro")
    private Set<MicroIngredientEntity> microIngredients;

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

    public Set<MicroIngredientEntity> getMicroIngredients() {
        return microIngredients;
    }
    public void setMicroIngredients(Set<MicroIngredientEntity> microIngredients) {
        this.microIngredients = microIngredients;
    }
}
