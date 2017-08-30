package com.acme.edu.legacy;

import com.acme.edu.logger.FlexibleLogger;
import com.acme.edu.logger.formatters.DefaultLoggerFormatter;
import com.acme.edu.logger.savers.ConsoleLoggerSaver;
import org.jetbrains.annotations.Nullable;

public class Logger {
    private static final FlexibleLogger logger;

    static {
        logger = new FlexibleLogger(new ConsoleLoggerSaver(), new DefaultLoggerFormatter());
    }

    public static void flush() {
        logger.flush();
    }

    public static void log(int value) {
        logger.log(value);
    }

    public static void log(byte value) {
        logger.log(value);
    }

    public static void log(boolean value) {
        logger.log(value);
    }

    public static void log(char value) {
        logger.log(value);
    }

    public static void log(@Nullable String value) {
        logger.log(value);
    }

    public static void log(@Nullable Object value) {
        logger.log(value);
    }

    public static void log(@Nullable int[] value) {
        logger.log(value);
    }

}
