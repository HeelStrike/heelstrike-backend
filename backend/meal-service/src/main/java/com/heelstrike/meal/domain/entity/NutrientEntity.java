package com.heelstrike.meal.domain.entity;

import com.heelstrike.core.BaseEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nutrient")
public class NutrientEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nutrient_name")
    private String name;

    @ManyToMany(mappedBy = "nutrients")
    private List<RecipeEntity> recipes = new ArrayList<>();

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
