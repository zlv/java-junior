package com.acme.edu;

import java.util.Objects;

public class Logger {

    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String PRIMITIVE_ARRAY_PREFIX = "primitives array: ";
    private static final String REFERENCE_PREFIX = "reference: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String STRING_PREFIX = "string: ";

    private static long currentSum;
    private static String currString;
    private static int currStringCount;
    private static State state = State.NONE;

    static {
        clear();
    }

    public static void flush() {
        printBuffer();
        clear();
    }

    public static void clear() {
        currentSum = 0;
        currString = null;
        currStringCount = 0;
        state = State.NONE;
    }

    public static void log(int value) {
        if (state != State.INT) {
            flush();
        }

        currentSum += value;
        state = State.INT;
    }

    public static void log(byte value) {
        if (state != State.BYTE) {
            flush();
        }

        currentSum += value;
        state = State.BYTE;
    }

    public static void log(boolean value) {
        println(PRIMITIVE_PREFIX + value);
    }

    public static void log(char value) {
        println(CHAR_PREFIX + value);
    }

    public static void log(String value) {
        if (state != State.STRING) {
            flush();
        }
        if (currStringCount > 0 && !Objects.equals(value, currString)) {
            flush();
        }
        currString = value;
        currStringCount++;
        if (currStringCount == 1) {
            print(STRING_PREFIX + value);
        }
        state = State.STRING;
    }

    public static void log(Object value) {
        println(REFERENCE_PREFIX + value);
    }

    public static void log(int[] arr) {
        print(PRIMITIVE_ARRAY_PREFIX );
        //String.join
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
        println(builder.toString());
        state = State.NONE;
    }

    private static void printBuffer() {
        switch (state) {
            case INT:
                printSumInContraints(Integer.MIN_VALUE, Integer.MAX_VALUE);
                break;
            case STRING:
                printStringCountSuffix();
                break;
            case BYTE:
                printSumInContraints(Byte.MIN_VALUE, Byte.MAX_VALUE);
                break;
        }
    }

    private static void printSumInContraints(long lowBound, long upperBound) {
        long sumInConstraints = getSumInConstraints(lowBound, upperBound);
        long rest = currentSum - sumInConstraints;
        if (rest > 0) {
            println(PRIMITIVE_PREFIX + rest);
        }
        println(PRIMITIVE_PREFIX + sumInConstraints);
    }


    private static void println(String value) {
        System.out.println(value);
    }

    private static void print(String value) {
        System.out.print(value);
    }

    private static void printStringCountSuffix() {
        if (currStringCount == 1) {
            println("");
        } else if (currStringCount > 1) {
            println(" (x" + currStringCount + ")");
        }
    }

    private static long getSumInConstraints(long lowBound, long upperBound) {
        return Math.max(Math.min(upperBound, currentSum), lowBound);
    }

    private enum State {
        NONE, INT, STRING, BYTE
    };

}
