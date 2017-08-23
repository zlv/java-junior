package com.acme.edu;

public class Logger {

    private static final String PRIMITIVE = "primitive: ";
    private static final String REFERENCE = "reference: ";
    private static final String CHAR = "char: ";
    private static final String STRING = "string: ";

    public static void log(int value) {
        print(PRIMITIVE + value);
    }

    public static void log(byte value) {
        print(PRIMITIVE + value);
    }

    public static void log(boolean value) {
        print(PRIMITIVE + value);
    }

    public static void log(char value) {
        print(CHAR + value);
    }

    public static void log(String value) {
        print(STRING + value);
    }

    public static void log(Object value) {
        print(REFERENCE + value);
    }

    private static void print(String value) {
        System.out.println(value);
    }

}
