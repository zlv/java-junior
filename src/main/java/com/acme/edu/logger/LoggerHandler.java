package com.acme.edu.logger;

import com.acme.edu.logger.messaging.LoggerMessage;
import com.acme.edu.logger.states.NoAggregationState;
import com.acme.edu.logger.states.State;

import java.util.function.Consumer;

/**
 * Created by Java_9 on 04.09.2017.
 */
public class LoggerHandler {
    private State currentState;
    private Consumer<LoggerMessage> printerConsumer;

    public LoggerHandler(Consumer<LoggerMessage> printerConsumer) {
        this.printerConsumer = printerConsumer;
        clear();
    }

    private void clear() {
        currentState = new NoAggregationState();
    }

    public void log(LoggerMessage loggerMessage) {
        State previousState = currentState;
        State newState = loggerMessage.accept(currentState);
        if (newState.isDifferentState(previousState)) {
            flushState(previousState);
        }
        printStateIfReady(newState);
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
