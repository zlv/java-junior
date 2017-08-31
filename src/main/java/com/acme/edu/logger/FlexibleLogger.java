package com.acme.edu.logger;

import com.acme.edu.logger.formatters.LoggerFormatter;
import com.acme.edu.logger.messaging.messages.*;
import com.acme.edu.logger.savers.LoggerPrinter;
import com.acme.edu.logger.states.State;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Supplier;

/**
 * Created by Java_9 on 25.08.2017.
 */
public class FlexibleLogger {
    private final LoggerPrinter loggerPrinter;
    private final LoggerFormatter loggerFormatter;
    private State currentState;
    private final Supplier<State> initialStateSupplier;

    public FlexibleLogger(Supplier<State> initialStateSupplier, LoggerPrinter loggerPrinter, LoggerFormatter loggerFormatter) {
        this.initialStateSupplier = initialStateSupplier;
        this.loggerPrinter = loggerPrinter;
        this.loggerFormatter = loggerFormatter;
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
        for (LoggerMessage message : state.getResultOutput()) {
            loggerPrinter.println(message.visit(loggerFormatter));
        }
    }


    private static String doStuff(Object arr) {
        if (arr.getClass().isArray()) {
            StringJoiner result = new StringJoiner(", ", "{" + System.lineSeparator(), "}" + System.lineSeparator());
            if (arr instanceof int[]) {
                int[] castedArr = (int[]) arr;
                for (int value : castedArr) {
                    result.add(String.valueOf(value));
                }
            } else if (arr instanceof Object[]) {
                Object[] castedArr = (Object[]) arr;
                for (Object value : castedArr) {
                    result.add(doStuff(value));
                }
            }
            return result.toString();
        } else {
            return Objects.toString(arr);
        }
    }

}
