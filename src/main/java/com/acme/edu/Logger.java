package com.acme.edu;

import java.util.Objects;

public class Logger {

    private static final String PRIMITIVE = "primitive: ";
    private static final String REFERENCE = "reference: ";
    private static final String CHAR = "char: ";
    private static final String STRING = "string: ";

    private static long currentSum;
    private static String currString;
    private static int currStringCount;

    static {
        clear();
    }

    public static void flush() {
        printBufferAndClear();
    }

    private enum State {
        NONE, INT, STRING, BYTE
    };

    private static State state = State.NONE;

    public static void log(int value) {
        if (state != State.INT) {
            printBufferAndClear();
        }

        currentSum += value;
        state = State.INT;
    }

    private static void printBufferAndClear() {
        printBuffer();
        clear();
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
            println(PRIMITIVE + rest);
        }
        println(PRIMITIVE + sumInConstraints);
    }

    public static void log(byte value) {
        if (state != State.BYTE) {
            printBufferAndClear();
        }

        currentSum += value;
        state = State.BYTE;
    }

    public static void log(boolean value) {
        println(PRIMITIVE + value);
    }

    public static void log(char value) {
        println(CHAR + value);
    }

    public static void log(String value) {
        if (state != State.STRING) {
            printBufferAndClear();
        }
        if (currStringCount > 0 && !Objects.equals(value, currString)) {
            printBufferAndClear();
        }
        currString = value;
        currStringCount++;
        if (currStringCount == 1) {
            print(STRING + value);
        }
        state = State.STRING;
    }

    public static void log(Object value) {
        println(REFERENCE + value);
    }

    private static void println(String value) {
        System.out.println(value);
    }

    private static void print(String value) {
        System.out.print(value);
    }

    public static void main(String[] args) {
        double x = Double.POSITIVE_INFINITY, y =1;
        System.out.println((x + y));
        System.out.println(Double.MIN_VALUE < (x / y));
        System.out.println(null instanceof Object);
    }

    public static void printStringCountSuffix() {
        if (currStringCount == 1) {
            println("");
        } else if (currStringCount > 1) {
            println(" (x" + currStringCount + ")");
        }
    }

    public static void clear() {
        currentSum = 0;
        currString = null;
        currStringCount = 0;
        state = State.NONE;
    }

    private static long getSumInConstraints(long lowBound, long upperBound) {
        return Math.max(Math.min(upperBound, currentSum), lowBound);
    }


}
