package com.superlapp.meal.domain.entity;

import com.superlapp.core.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "macro_ingredient")
public class MacroIngredientEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "macro_ingredient_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "macro_ingredient_allergen",
            joinColumns = @JoinColumn(name = "macro_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name ="allergen_id")
    )
    private List<AllergenEntity> allergens = new ArrayList<>();

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int newId) {
        this.id = newId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    public List<AllergenEntity> getAllergens() {
        return this.allergens;
    }
}
