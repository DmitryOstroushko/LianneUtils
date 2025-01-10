package com.lianne.utils.value.string;

public class StringChecksUtils {

    /**
     * Util method checks if a provided String object is null or empty string
     * @param stringValue is the String object to check
     * @return true if the provided String object is null or empty string, else result is false
     */
    public static boolean isNullOrEmptyString(String stringValue) {
        return stringValue == null || stringValue.isEmpty();
    }
}
