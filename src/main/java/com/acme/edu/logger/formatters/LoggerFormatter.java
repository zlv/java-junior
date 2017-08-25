package com.acme.edu.logger.formatters;

public interface LoggerFormatter {
    String format(long value);
    String format(boolean value);
    String format(char value);
    String format(String value);
    String format(Object value);
    String format(int[] value);
}
