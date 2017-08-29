package com.acme.edu.logger;

import com.acme.edu.logger.formatters.LoggerFormatter;
import com.acme.edu.logger.messaging.messages.ByteMessage;
import com.acme.edu.logger.messaging.messages.IntMessage;
import com.acme.edu.logger.messaging.messages.SimpleMessage;
import com.acme.edu.logger.messaging.messages.StringMessage;
import com.acme.edu.logger.savers.LoggerPrinter;
import com.acme.edu.logger.states.State;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by Java_9 on 25.08.2017.
 */
public class FlexibleLogger {
    private final LoggerPrinter loggerPrinter;
    private final LoggerFormatter loggerFormatter;
    private State currentState;
    private final State initialState;

    private long currentSum;
    private String currentStringValue;
    private int currentStringStreak;

    public FlexibleLogger(State initialState, LoggerPrinter loggerPrinter, LoggerFormatter loggerFormatter) {
        this.initialState = initialState;
        this.currentState = initialState;
        this.loggerPrinter = loggerPrinter;
        this.loggerFormatter = loggerFormatter;
        clear();
    }


    public void flush() {
        printBuffer();
        clear();
    }

    private void clear() {
        currentSum = 0;
        currentStringValue = null;
        currentStringStreak = 0;
        currentState = initialState;
    }

    public void log(IntMessage loggerMessage) {
        currentState.accept(loggerMessage);
    }

    public void log(ByteMessage loggerMessage) {
        currentState.accept(loggerMessage);
    }

    public void log(SimpleMessage loggerMessage) {
        currentState.accept(loggerMessage);
    }

    public void log(StringMessage loggerMessage) {
        currentState.accept(loggerMessage);
    }

    /*public void log(int value) {
        if (currentState != State.INT) {
            flush();
        }

        currentSum += value;
        currentState = State.INT;
    }

    public void log(byte value) {
        if (currentState != State.BYTE) {
            flush();
        }

        currentSum += value;
        currentState = State.BYTE;
    }

    public void log(boolean value) {
        loggerPrinter.println(loggerFormatter.format(value));
    }

    public void log(char value) {
        loggerPrinter.println(loggerFormatter.format(value));
    }

    public void log(String value) {
        if (currentState != State.STRING) {
            flush();
        }
        if (currentStringStreak > 0 && !Objects.equals(value, currentStringValue)) {
            flush();
        }
        currentStringValue = value;
        currentStringStreak++;
        currentState = State.STRING;
    }

    public void log(Object value) {
        loggerPrinter.println(loggerFormatter.format(value));
    }

    public void log(int[] arr) {
        loggerPrinter.println(loggerFormatter.format(arr));
        currentState = State.NONE;
    }

    private void printStringWithStreak() {
        StringBuilder builder = new StringBuilder(Objects.toString(currentStringValue));
        if (currentStringStreak > 1) {
            builder.append(" (x")
                    .append(currentStringStreak)
                    .append(")");
        }
        loggerPrinter.println(loggerFormatter.format(builder.toString()));
    }*/

    private void printBuffer() {
        for (String value : currentState.getCurrentOutput()) {
            loggerPrinter.println(value);
        }
//        switch (currentState) {
//            case INT:
//                printSumInConstraints(Integer.MIN_VALUE, Integer.MAX_VALUE);
//                break;
//            case STRING:
//                printStringWithStreak();
//                break;
//            case BYTE:
//                printSumInConstraints(Byte.MIN_VALUE, Byte.MAX_VALUE);
//                break;
//        }
    }

//    private void printSumInConstraints(long lowBound, long upperBound) {
//        long sumInConstraints = getSumInConstraints(lowBound, upperBound);
//        long rest = currentSum - sumInConstraints;
//        if (rest != 0) {
//            loggerPrinter.println(loggerFormatter.format(rest));
//        }
//        loggerPrinter.println(loggerFormatter.format(sumInConstraints));
//    }
//
//    private long getSumInConstraints(long lowBound, long upperBound) {
//        return Math.max(Math.min(upperBound, currentSum), lowBound);
//    }

//    private enum State {
//        NONE, INT, STRING, BYTE
//    }

    public static void main(String[] args) {
        int[][][][] arr = new int[5][3][2][3];
        System.out.println(doStuff(arr));
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
