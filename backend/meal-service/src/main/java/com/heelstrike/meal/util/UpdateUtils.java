package com.heelstrike.meal.util;

import java.util.Optional;

public class UpdateUtils {
    public static <T> T updateIfNotNull(T newValue, T currentValue) {
        return newValue != null ? newValue : currentValue;
    }

    public static <T> T updateIfNotNullOptional(Optional<T> newValue, Optional<T> currentValue) {
        return newValue != null && newValue.isPresent()
                ? newValue.get()
                : currentValue != null && currentValue.isPresent()
                ? currentValue.get()
                : null;
    }
}
