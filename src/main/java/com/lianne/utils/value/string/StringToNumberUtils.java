package com.lianne.utils.value.string;

import java.util.Optional;

public class StringToNumberUtils {

    public static Integer safeParseInt(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer safeParseInt(String str, int defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static Optional<Integer> safeParseIntWithOptional(String str) {

        if (str == null || str.trim().isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

    }

    public static Integer safeParseIntWithRangeCheck(String str, int min, int max) {

        if (str == null || str.trim().isEmpty()) {
            return null;
        }

        try {
            int value = Integer.parseInt(str);
            if (value < min || value > max) {
                return null;
            }
            return value;
        } catch (NumberFormatException e) {
            return null;
        }

    }

    public static Double safeParseDouble(String str) {

        if (str == null || str.trim().isEmpty()) {
            return null;
        }

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }

    }

}