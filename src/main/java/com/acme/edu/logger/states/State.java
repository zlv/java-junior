package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.*;

import java.util.List;


/**
 * Created by Java_9 on 28.08.2017.
 */
public abstract class State implements MessageVisitor<State> {

    public abstract List<LoggerMessage> getResultOutput();

    public boolean isReadyForPrint() {
        return false;
    }

    public abstract boolean isDifferentState(State other);

    @Override
    public State visit(BooleanMessage message) {
        return new NoAggregationState(message);
    }

    @Override
    public State visit(ObjectMessage message) {
        return new NoAggregationState(message);
    }

    @Override
    public State visit(CharMessage message) {
        return new NoAggregationState(message);
    }

    @Override
    public State visit(IntArrayMessage message) {
        return new NoAggregationState(message);
    }

    @Override
    public State visit(FlushMessage message) {
        return new NoAggregationState();
    }
}
