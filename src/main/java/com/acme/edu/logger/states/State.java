package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.MessageVisitor;
import com.acme.edu.logger.messaging.messages.*;

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
    public State accept(BooleanMessage message) {
        return new NoAggregationState(message);
    }

    @Override
    public State accept(ObjectMessage message) {
        return new NoAggregationState(message);
    }

    @Override
    public State accept(CharMessage message) {
        return new NoAggregationState(message);
    }

    @Override
    public State accept(IntArrayMessage message) {
        return new NoAggregationState(message);
    }

    @Override
    public State accept(FlushMessage message) {
        return new NoAggregationState();
    }
}
