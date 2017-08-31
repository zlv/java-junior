package com.acme.edu.legacy;

import com.acme.edu.logger.messaging.messages.*;
import com.acme.edu.logger.states.NoAggregationState;
import com.acme.edu.ПавликМорозов;
import com.acme.edu.logger.FlexibleLogger;
import com.acme.edu.logger.formatters.DefaultLoggerFormatter;
import com.acme.edu.logger.savers.ConsoleLoggerSaver;
import com.sun.istack.internal.Nullable;

public class Logger {
    private static final FlexibleLogger logger;

    static {
        logger = new FlexibleLogger(NoAggregationState::new, new ConsoleLoggerSaver(), new DefaultLoggerFormatter());
    }

    public static void flush() {
        logger.flush();
    }

    public static void log(int value) {
        logger.log(new IntMessage(value));
    }

    public static void log(byte value) {
        logger.log(new ByteMessage(value));
    }

    public static void log(boolean value) {
        logger.log(new BooleanMessage(value));
    }

    public static void log(char value) {
        logger.log(new CharMessage(value));
    }

    public static void log(@Nullable String value) {
        logger.log(new StringMessage(value, 1));
    }

    public static void log(@Nullable Object value) {
        logger.log(new ObjectMessage(value));
    }

    public static void log(@Nullable int[] value) {
        logger.log(new IntArrayMessage(value));
    }

    public static void main(String[] args) {
        System.out.println(ПавликМорозов.стукануть(logger));
    }

}
