package com.heelstrike.meal.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "recipe_image")
public class RecipeImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    public RecipeImageEntity() {}

    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }
}
