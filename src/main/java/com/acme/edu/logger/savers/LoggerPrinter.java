package com.acme.edu.logger.savers;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Java_9 on 25.08.2017.
 */
public interface LoggerPrinter {
    void print(@NotNull String value);
    void println(@NotNull String value);
}
