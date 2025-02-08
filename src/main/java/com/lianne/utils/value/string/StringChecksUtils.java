package com.lianne.utils.value.string;

/**
 * Utility class providing methods for string-related checks.
 * <p>
 * This class contains utility methods for checking the properties of string values.
 * </p>
 */
public class StringChecksUtils {

    /**
     * Utility method that checks if a provided String is either {@code null} or an empty string.
     * <p>
     * This method evaluates whether the given string is either {@code null} or is an empty string
     * (i.e., has a length of 0). It returns {@code true} if either condition is met, and {@code false}
     * otherwise.
     * </p>
     *
     * @param stringValue The string to be checked. Can be {@code null} or an empty string.
     * @return {@code true} if the string is either {@code null} or empty, otherwise {@code false}.
     */
    public static boolean isNullOrEmptyString(String stringValue) {
        return stringValue == null || stringValue.isEmpty();
    }
}
