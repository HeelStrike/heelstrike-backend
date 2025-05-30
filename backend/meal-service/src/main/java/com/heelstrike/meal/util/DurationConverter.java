package com.heelstrike.meal.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, String>  {

    @Override
    public String convertToDatabaseColumn(Duration duration) {
        return (duration == null) ? null : duration.toString();
    }

    @Override
    public Duration convertToEntityAttribute(String databaseData) {
        return (databaseData == null) ? null : Duration.parse(databaseData);
    }
}
