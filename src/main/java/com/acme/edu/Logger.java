package com.acme.edu;

import java.util.Objects;

public class Logger {

    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String PRIMITIVES_ARRAY_PREFIX = "primitives array: ";
    private static final String REFERENCE_PREFIX = "reference: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String STRING_PREFIX = "string: ";

    private static long currentSum;
    private static String currentStringValue;
    private static int currentStringStreak;
    private static State currentState;

    static {
        clear();
    }

    public static void flush() {
        printBuffer();
        clear();
    }

    public static void clear() {
        currentSum = 0;
        currentStringValue = null;
        currentStringStreak = 0;
        currentState = State.NONE;
    }

    public static void log(int value) {
        if (currentState != State.INT) {
            flush();
        }

        currentSum += value;
        currentState = State.INT;
    }

    public static void log(byte value) {
        if (currentState != State.BYTE) {
            flush();
        }

        currentSum += value;
        currentState = State.BYTE;
    }

    public static void log(boolean value) {
        println(PRIMITIVE_PREFIX + value);
    }

    public static void log(char value) {
        println(CHAR_PREFIX + value);
    }

    public static void log(String value) {
        if (currentState != State.STRING) {
            flush();
        }
        if (currentStringStreak > 0 && !Objects.equals(value, currentStringValue)) {
            flush();
        }
        currentStringValue = value;
        currentStringStreak++;
        if (currentStringStreak == 1) {
            print(STRING_PREFIX + value);
        }
        currentState = State.STRING;
    }

    public static void log(Object value) {
        println(REFERENCE_PREFIX + value);
    }

    public static void log(int[] arr) {
        print(PRIMITIVES_ARRAY_PREFIX);
        //String.join

        println(convertToString(arr));
        currentState = State.NONE;
    }

    private static void printStringCountSuffix() {
        if (currentStringStreak == 1) {
            println("");
        } else if (currentStringStreak > 1) {
            println(" (x" + currentStringStreak + ")");
        }
    }

    private static void printBuffer() {
        switch (currentState) {
            case INT:
                printSumInConstraints(Integer.MIN_VALUE, Integer.MAX_VALUE);
                break;
            case STRING:
                printStringCountSuffix();
                break;
            case BYTE:
                printSumInConstraints(Byte.MIN_VALUE, Byte.MAX_VALUE);
                break;
        }
    }

    private static void printSumInConstraints(long lowBound, long upperBound) {
        long sumInConstraints = getSumInConstraints(lowBound, upperBound);
        long rest = currentSum - sumInConstraints;
        if (rest != 0) {
            println(PRIMITIVE_PREFIX + rest);
        }
        println(PRIMITIVE_PREFIX + sumInConstraints);
    }

    private static long getSumInConstraints(long lowBound, long upperBound) {
        return Math.max(Math.min(upperBound, currentSum), lowBound);
    }

    private static void println(String value) {
        System.out.println(value);
    }

    private static void print(String value) {
        System.out.print(value);
    }

    private static String convertToString(int[] arr) {
        StringBuilder builder = new StringBuilder();
        if (arr != null) {
            builder.append("{");
            for (int i = 0; i < arr.length - 1; i++) {
                builder.append(arr[i])
                        .append(", ");
            }
            if (arr.length > 0) {
                builder.append(arr[arr.length - 1]);
            }
            builder.append("}");
        } else {
            builder.append("null");
        }
        return builder.toString();
    }

    private enum State {
        NONE, INT, STRING, BYTE
    }

}
