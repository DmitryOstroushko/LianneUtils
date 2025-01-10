package com.lianne.utils.value.string;

import java.util.Optional;

public class StringToNumberUtils {

    /**
     * Util method performs safe conversion a String object to an Integer object.
     * If the String object is an empty string, null object or invalid string then result is null.
     * @param str is the String object to convert
     * @return the Integer object as a result of conversion
     */
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

    /**
     * Util method performs safe conversion a String object to an Integer object.
     * If the String object is an empty string, null object or invalid string then result is provided default value.
     * @param str is the String object to convert
     * @param defaultValue is a default value to return if a String object is an empty string, null object or invalid string
     * @return the Integer object as a result of conversion
     */
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

    /**
     * Util method performs safe conversion a String object to an Optional<Integer> object.
     * If the String object is an empty string, null object or invalid string then result is null.
     * @param str is the String object to convert
     * @return the Optional<Integer> object as a result of conversion
     */
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

    /**
     * Util method performs safe conversion a String object to an Integer object in a given range.
     * If the String object is an empty string, null object, invalid string or out of the given range then result is null.
     * @param str is the String object to convert
     * @return the Integer object as a result of conversion
     */
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

    /**
     * Util method performs safe conversion a String object to a Double object.
     * If the String object is an empty string, null object or invalid string then result is null.
     * @param str is the String object to convert
     * @return the Double object as a result of conversion
     */
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