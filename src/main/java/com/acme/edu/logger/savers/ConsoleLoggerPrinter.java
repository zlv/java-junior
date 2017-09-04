package com.acme.edu.logger.savers;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Java_9 on 25.08.2017.
 */
public class ConsoleLoggerPrinter implements LoggerPrinter {

    @Override
    public void print(@NotNull String value) {
        System.out.print(value);
    }

    @Override
    public void println(@NotNull String value) {
        System.out.println(value);
    }
}
