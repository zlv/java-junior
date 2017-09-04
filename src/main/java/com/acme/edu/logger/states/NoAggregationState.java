package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.*;
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
    public State visit(IntMessage message) {
        return new IntAggregationState(message.getValue());
    }

    @Override
    public State visit(ByteMessage message) {
        return new ByteAggregationState(message.getValue());
    }

    @Override
    public State visit(StringMessage message) {
        return new StringAggregationState(message.getValue(), message.getStringCount());
    }

    @Override
    public State visit(BooleanMessage message) {
        return setMessage(message);
    }

    @Override
    public State visit(CharMessage message) {
        return setMessage(message);
    }

    @Override
    public State visit(IntArrayMessage message) {
        return setMessage(message);
    }


    private State setMessage(LoggerMessage message) {
        this.message = message;
        return this;
    }

}
