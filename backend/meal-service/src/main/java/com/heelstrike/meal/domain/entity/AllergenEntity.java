package com.heelstrike.meal.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "allergen")
public class AllergenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private long id;

    @Column(name = "name")
    private String name;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
