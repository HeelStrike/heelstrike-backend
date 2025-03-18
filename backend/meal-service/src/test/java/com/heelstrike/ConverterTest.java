package com.heelstrike;

import com.heelstrike.meal.util.DurationConverter;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class ConverterTest {
    private final DurationConverter durationConverter = new DurationConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        Duration duration = Duration.ofMinutes(30);
        // Must return ISO-8601 format, intl standard for date - time data.
        String expected = "PT30M";

        assertEquals(expected, durationConverter.convertToDatabaseColumn(duration));
        assertNull(durationConverter.convertToDatabaseColumn(null));
    }

    @Test
    public void testConvertToEntityAttribute() {
        String databaseValue = "PT45M";
        Duration expected = Duration.ofMinutes(45);

        assertEquals(expected, durationConverter.convertToEntityAttribute(databaseValue));
        assertNull(durationConverter.convertToEntityAttribute(null));
    }
}
