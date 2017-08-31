package com.acme.edu.logger.savers;

/**
 * Created by Java_9 on 25.08.2017.
 */
public class ConsoleLoggerPrinter implements LoggerPrinter {

    @Override
    public void print(String value) {
        System.out.print(value);
    }

    @Override
    public void println(String value) {
        System.out.println(value);
    }
}
