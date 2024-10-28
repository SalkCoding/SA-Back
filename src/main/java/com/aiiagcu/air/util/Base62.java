package com.aiiagcu.air.util;

public class Base62 {
    private static String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String toBase62(long number) {
        return toBase(number, 62);
    }

    public static long fromBase62(String base62String) {
        return fromBase(base62String, 62);
    }

    private static String toBase(long number, int base) {
        if (number < base) {
            return Character.toString(alphabet.charAt((int) number));
        }

        long value = number;
        StringBuilder builder = new StringBuilder();

        while (value != 0) {
            int remind = (int) (value % base);
            value = (value - remind) / base;
            builder.append(alphabet.charAt(remind));
        }

        return builder.reverse().toString();
    }

    private static long fromBase(String baseString, int base) {
        long result = 0;
        int length = baseString.length();

        for (int i = 0; i < length; i++) {
            char character = baseString.charAt(i);
            int digit = alphabet.indexOf(character);
            result += (long) (digit * Math.pow(base, length - i - 1));
        }

        return result;
    }
}
