package com.acme.edu.legacy;

import com.acme.edu.logger.LoggerContext;
import com.acme.edu.logger.LoggerHandler;
import com.acme.edu.logger.formatters.DefaultLoggerFormatter;
import com.acme.edu.logger.formatters.LoggerFormatter;
import com.acme.edu.logger.messaging.messages.*;
import com.acme.edu.logger.savers.ConsoleLoggerPrinter;
import com.acme.edu.logger.savers.LoggerPrinter;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class Logger {
    private static final LoggerContext logger;

    static {
        LoggerPrinter printer = new ConsoleLoggerPrinter();
        LoggerFormatter formatter = new DefaultLoggerFormatter();
        LoggerHandler handler = new LoggerHandler(message -> {
            try {
                printer.println(message.visit(formatter));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logger = new LoggerContext(handler::log);
    }

    public static void flush() {
        logger.log(new FlushMessage());
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

}
