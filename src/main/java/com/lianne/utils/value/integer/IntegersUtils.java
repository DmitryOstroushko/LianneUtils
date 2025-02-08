package com.lianne.utils.value.integer;

/**
 * Utility class providing methods for working with integer values.
 * <p>
 * This class contains utility methods that perform checks and operations related to integer values.
 * </p>
 */
public class IntegersUtils {

    /**
     * Utility method that checks if one integer is a multiple of another integer.
     * <p>
     * This method returns true if the first integer (dividend) is divisible by the second integer (divisor)
     * without leaving a remainder. It ensures that the divisor is positive before performing the check.
     * </p>
     *
     * @param dividend The integer number to be checked.
     * @param divisor The integer number by which the dividend is divided. It must be a positive integer.
     * @return {@code true} if the dividend is a multiple of the divisor, otherwise {@code false}.
     * @throws IllegalArgumentException if the divisor is not a positive integer.
     */
    public static boolean isMultipleOfNumber(int dividend, int divisor) {
        if (divisor > 0) {
            return dividend % divisor == 0;
        } else {
            // Returning false if the divisor is not positive
            return false;
        }
    }
}
