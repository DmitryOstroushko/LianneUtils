package com.lianne.utils.value.integer;

public class IntegersUtils {

    /**
     *
     * @param dividend is an Integer number
     * @param divisor is a positive Integer number
     * @return if dividend is a multiple of the divisor
     */
    public static boolean isMultipleOfNumber(int dividend, int divisor) {
        if (divisor > 0) {
            return dividend % divisor == 0;
        } else return false;
    }
}
