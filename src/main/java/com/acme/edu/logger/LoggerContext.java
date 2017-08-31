package com.acme.edu.logger;

import com.acme.edu.logger.messaging.messages.LoggerMessage;
import com.acme.edu.logger.states.State;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by Java_9 on 25.08.2017.
 */
public class LoggerContext {
    private State currentState;
    private final Supplier<State> initialStateSupplier;
    private final Consumer<LoggerMessage> printerConsumer;

    public LoggerContext(Supplier<State> initialStateSupplier, Consumer<LoggerMessage> printerConsumer) {
        this.initialStateSupplier = initialStateSupplier;
        this.printerConsumer = printerConsumer;
        clear();
    }

    public void flush() {
        flushState(currentState);
        clear();
    }

    private void clear() {
        currentState = initialStateSupplier.get();
    }

    public void log(LoggerMessage loggerMessage) {
        State previousState = currentState;
        State newState = loggerMessage.visit(currentState);
        printStateIfReady(newState);
        if (newState != previousState) {
            flushState(previousState);
        }
        currentState = newState;
    }

    private void printStateIfReady(State state) {
        if (state.isReadyForPrint()) {
            flushState(state);
        }
    }

    private void flushState(State state) {
        state.getResultOutput().forEach(printerConsumer);
    }

}
