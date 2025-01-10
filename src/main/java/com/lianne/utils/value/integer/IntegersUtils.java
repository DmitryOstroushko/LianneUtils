package com.lianne.utils.value.integer;

public class IntegersUtils {

    /**
     * Util method performs checks if one integer number is multiple of the another integer number
     * @param dividend is an integer number
     * @param divisor is a positive Integer number
     * @return true if dividend integer value is a multiple of the divisor integer value
     */
    public static boolean isMultipleOfNumber(int dividend, int divisor) {
        if (divisor > 0) {
            return dividend % divisor == 0;
        } else return false;
    }
}
