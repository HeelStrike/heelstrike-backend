package com.heelstrike.meal.domain.entity;

import com.heelstrike.core.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "diet")
public class DietEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "diet_name")
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
