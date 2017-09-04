package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.messages.*;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class NoAggregationState extends State {

    @Nullable
    private LoggerMessage message;

    public NoAggregationState() {
        message = null;
    }

    public NoAggregationState(LoggerMessage message) {
        this.message = message;
    }

    @Override
    public List<LoggerMessage> getResultOutput() {
        return message != null ? Collections.singletonList(message) : Collections.emptyList();
    }

    @Override
    public boolean isReadyForPrint() {
        return true;
    }

    @Override
    public boolean isDifferentState(State other) {
        return !(other instanceof NoAggregationState);
    }

    @Override
    public State accept(IntMessage message) {
        return new IntAggregationState(message.getValue());
    }

    @Override
    public State accept(ByteMessage message) {
        return new ByteAggregationState(message.getValue());
    }

    @Override
    public State accept(StringMessage message) {
        return new StringAggregationState(message.getValue(), message.getStringCount());
    }

    @Override
    public State accept(BooleanMessage message) {
        return setMessage(message);
    }

    @Override
    public State accept(CharMessage message) {
        return setMessage(message);
    }

    @Override
    public State accept(IntArrayMessage message) {
        return setMessage(message);
    }


    private State setMessage(LoggerMessage message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
