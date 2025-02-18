package com.heelstrike.meal.domain.entity;

import com.heelstrike.core.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "allergen", schema="public")
public class AllergenEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "allergen_name")
    private String name;

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
