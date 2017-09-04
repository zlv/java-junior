package com.acme.edu.logger;

import com.acme.edu.logger.messaging.LoggerMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

/**
 * Created by Java_9 on 25.08.2017.
 */
public class LoggerContext {
    private final List<Consumer<LoggerMessage>> handlers;

    @SafeVarargs
    public LoggerContext(Consumer<LoggerMessage>... handlers) {
        this.handlers = new ArrayList<>();
        this.handlers.addAll(asList(handlers));
    }

    public void log(LoggerMessage message) {
        handlers.forEach(loggerMessageConsumer -> loggerMessageConsumer.accept(message));
    }

}
