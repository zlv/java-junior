package com.acme.edu;

import java.util.Objects;

public class Logger {

    private static final String PRIMITIVE = "primitive: ";
    private static final String REFERENCE = "reference: ";
    private static final String CHAR = "char: ";
    private static final String STRING = "string: ";

    private static long currInt = 0;
    private static String currString = null;
    private static int currStringCount = 0;
    private static int currByte = 0;

    public static void flush() {
        printBufferAndClear();
    }

    private enum State{NONE, INT, STRING, BYTE};
    private static State state = State.NONE;

    public static void log(int value) {
        long tempValue = currInt;
        currInt = value + tempValue;

        if (state != State.INT) {
            printBufferAndClear();
        }
        state = State.INT;
    }

    private static void printBufferAndClear() {
        switch (state) {
            case INT:
                println(PRIMITIVE + getIntSum());
                break;
            case STRING:
                if (currStringCount != 0) {
                    if (currStringCount > 1) {
                        println(" (x" + currStringCount + ")");
                    } else {
                        println("");
                    }
                }
                break;
            case BYTE:
                println(PRIMITIVE + getByteSum());
                break;
        }
        clear();
    }

    public static void log(byte value) {

        println(PRIMITIVE + value);
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
        if (currStringCount > 0) {
            if (Objects.equals(value, currString)) {
            } else {
                printBufferAndClear();
                print(STRING + value);
            }
            currString = value;
            currStringCount++;
        } else {
            print(STRING + value);
            currString = value;
            currStringCount++;
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
        System.out.println("" instanceof String);
    }

    private static void clear() {
        currInt = 0;
        currString = null;
        currStringCount = 0;
        currByte = 0;
        state = State.NONE;
    }

    private static void checkInt() {
        if (state == State.INT) {
            println(PRIMITIVE + currInt);
        }
    }

    private static void add(int value) {
        currInt += value;
    }

    private static byte getByteSum() {
        return (byte) Math.max(Math.min(Byte.MAX_VALUE, currInt), Byte.MIN_VALUE);
    }

    private static int getIntSum() {
        return (int) Math.max(Math.min(Integer.MAX_VALUE, currInt), Integer.MIN_VALUE);
    }


}
