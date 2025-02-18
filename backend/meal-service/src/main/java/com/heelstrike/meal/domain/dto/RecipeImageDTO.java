package com.heelstrike.meal.domain.dto;

public class RecipeImageDTO {
    private int id;
    private String url;

    public RecipeImageDTO(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public int getId() {
        return this.id;
    }

    public void setUrl(String newUrl) {
        this.url = newUrl;
    }

    public String getUrl() {
        return this.url;
    }

}
