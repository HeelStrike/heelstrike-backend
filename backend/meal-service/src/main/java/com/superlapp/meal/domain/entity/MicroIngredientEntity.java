package com.superlapp.meal.domain.entity;

import com.superlapp.core.BaseEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_ingredient")
public class MicroIngredientEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "micro_ingredient_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "micro_ingredient_allergen",
            joinColumns = @JoinColumn(name = "micro_ingredient_id"),
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




}
