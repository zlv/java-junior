package com.acme.edu.logger.formatters;

import com.acme.edu.logger.messaging.messages.*;

import java.util.Objects;
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
    public String accept(IntMessage message) {
        return PRIMITIVE_PREFIX + message.getValue();
    }

    @Override
    public String accept(ByteMessage message) {
        return PRIMITIVE_PREFIX + message.getValue();
    }

    @Override
    public String accept(StringMessage message) {
        int stringCount = message.getStringCount();
        String suffix = (stringCount == 1 ? "" : " (x" + stringCount + ")");
        return STRING_PREFIX + message.getValue() + suffix;
    }

    @Override
    public String accept(ObjectMessage message) {
        return REFERENCE_PREFIX + Objects.toString(message.getValue());
    }

    @Override
    public String accept(BooleanMessage message) {
        return PRIMITIVE_PREFIX + message.getValue();
    }

    @Override
    public String accept(CharMessage message) {
        return CHAR_PREFIX + message.getValue();
    }

    @Override
    public String accept(IntArrayMessage message) {
        return PRIMITIVES_ARRAY_PREFIX + formatArray(message.getValue());
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
