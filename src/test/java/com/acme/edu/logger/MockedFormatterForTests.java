package com.acme.edu.logger;

import com.acme.edu.logger.formatters.LoggerFormatter;
import com.acme.edu.logger.messaging.*;

import java.util.Arrays;

/**
 * Created by Java_9 on 04.09.2017.
 */
public class MockedFormatterForTests implements LoggerFormatter {
    @Override
    public String visit(IntMessage message) {
        return "my int value: " + message.getValue();
    }

    @Override
    public String visit(ByteMessage message) {
        return "my byte value: " + message.getValue();
    }

    @Override
    public String visit(StringMessage message) {
        return "my string " + message.getValue() + " occurred " + message.getStringCount() + " times";
    }

    @Override
    public String visit(ObjectMessage message) {
        return "my object: " + message.getValue();
    }

    @Override
    public String visit(BooleanMessage message) {
        return "my boolean: " + message.getValue();
    }

    @Override
    public String visit(CharMessage message) {
        return "my char" + message.getValue();
    }

    @Override
    public String visit(IntArrayMessage message) {
        return "my array: " + Arrays.toString(message.getValue());
    }

    @Override
    public String visit(FlushMessage message) {
        return null;
    }
}
