package com.heelstrike.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@ApplicationScoped
public class JsonFactory {

    public JsonFactory() {}

    protected JsonObject createJsonError(String errorMessage) {
        return Json.createObjectBuilder()
                .add("error", errorMessage)
                .build();
    }

    protected JsonObject createJson(JsonObjectBuilder jsonBuilder) {
        return Json.createObjectBuilder()
                .add("success", true)
                .add("data", jsonBuilder)
                .build();
    }
}
