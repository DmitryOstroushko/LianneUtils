package com.lianne.utils.value.string;

import java.util.Optional;

/**
 * Utility class for safely converting strings to numeric types.
 * <p>
 * This class provides utility methods for converting strings to {@code Integer} or {@code Double}
 * values with built-in safety checks to handle invalid, empty, or {@code null} strings.
 * </p>
 */
public class StringToNumberUtils {

    /**
     * Safely converts a string to an {@code Integer}.
     * <p>
     * If the string is {@code null}, empty, or invalid (i.e., not a valid integer), the method returns {@code null}.
     * </p>
     *
     * @param str The string to be converted to an {@code Integer}.
     * @return The resulting {@code Integer} or {@code null} if the string is invalid.
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
     * Safely converts a string to an {@code Integer}, providing a default value if conversion fails.
     * <p>
     * If the string is {@code null}, empty, or invalid (i.e., not a valid integer), the method returns the specified default value.
     * </p>
     *
     * @param str The string to be converted to an {@code Integer}.
     * @param defaultValue The default value to return if the conversion fails.
     * @return The resulting {@code Integer} or the specified {@code defaultValue} if the string is invalid.
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
     * Safely converts a string to an {@code Optional<Integer>}.
     * <p>
     * If the string is {@code null}, empty, or invalid (i.e., not a valid integer), the method returns {@code Optional.empty()}.
     * </p>
     *
     * @param str The string to be converted to an {@code Optional<Integer>}.
     * @return An {@code Optional<Integer>} containing the parsed value or {@code Optional.empty()} if invalid.
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
     * Safely converts a string to an {@code Integer} within a given range.
     * <p>
     * If the string is {@code null}, empty, invalid, or the integer is out of the specified range, the method returns {@code null}.
     * </p>
     *
     * @param str The string to be converted to an {@code Integer}.
     * @param min The minimum acceptable value for the integer.
     * @param max The maximum acceptable value for the integer.
     * @return The resulting {@code Integer} within the range, or {@code null} if the string is invalid or out of range.
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
     * Safely converts a string to a {@code Double}.
     * <p>
     * If the string is {@code null}, empty, or invalid (i.e., not a valid double), the method returns {@code null}.
     * </p>
     *
     * @param str The string to be converted to a {@code Double}.
     * @return The resulting {@code Double} or {@code null} if the string is invalid.
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