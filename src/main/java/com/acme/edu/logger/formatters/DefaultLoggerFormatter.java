package com.acme.edu.logger.formatters;

import java.util.StringJoiner;

/**
 * Created by Java_9 on 25.08.2017.
 */
public class DefaultLoggerFormatter implements LoggerFormatter {

    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String PRIMITIVES_ARRAY_PREFIX = "primitives array: ";
    private static final String REFERENCE_PREFIX = "reference: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String STRING_PREFIX = "string: ";

    @Override
    public String format(long value) {
        return PRIMITIVE_PREFIX + value;
    }

    @Override
    public String format(boolean value) {
        return PRIMITIVE_PREFIX + value;
    }

    @Override
    public String format(char value) {
        return CHAR_PREFIX + value;
    }

    @Override
    public String format(String value) {
        return STRING_PREFIX + value;
    }

    @Override
    public String format(Object value) {
        return REFERENCE_PREFIX + value;
    }

    @Override
    public String format(int[] value) {
        return PRIMITIVES_ARRAY_PREFIX + formatArray(value);
    }

    private String formatArray(int[] array) {
        if (array == null) {
            return "null";
        }
        StringJoiner joiner = new StringJoiner(", ", "{", "}");
        for(int element : array) {
            joiner.add(String.valueOf(element));
        }
        return joiner.toString();
    }
}
