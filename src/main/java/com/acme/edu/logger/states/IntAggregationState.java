package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.messages.ByteMessage;
import com.acme.edu.logger.messaging.messages.IntMessage;
import com.acme.edu.logger.messaging.messages.LoggerMessage;
import com.acme.edu.logger.messaging.messages.StringMessage;

import java.util.ArrayList;
import java.util.List;

import static com.acme.edu.logger.states.AggregationStateUtils.getRestInConstraints;
import static com.acme.edu.logger.states.AggregationStateUtils.getSumInConstraints;

/**
 * Created by Java_9 on 31.08.2017.
 */
public class IntAggregationState extends State {

    private long sum;
    private static final int UPPER_BOUND = Integer.MAX_VALUE;
    private static final int LOWER_BOUND = Integer.MIN_VALUE;

    public IntAggregationState(long sum) {
        this.sum = sum;
    }

    @Override
    public List<LoggerMessage> getResultOutput() {
        List<LoggerMessage> result = new ArrayList<>();
        int sumInConstraints = (int) getSumInConstraints(sum, LOWER_BOUND, UPPER_BOUND);
        int restInConstraints = (int) getRestInConstraints(sum, LOWER_BOUND, UPPER_BOUND);
        if (restInConstraints != 0) {
            result.add(new IntMessage(restInConstraints));
        }
        result.add(new IntMessage(sumInConstraints));
        return result;
    }

    @Override
    public boolean isDifferentState(State other) {
        return !(other instanceof IntAggregationState);
    }

    @Override
    public State accept(IntMessage message) {
        sum += message.getValue();
        return this;
    }

    @Override
    public State accept(ByteMessage message) {
        return new ByteAggregationState(message.getValue());
    }

    @Override
    public State accept(StringMessage message) {
        return new StringAggregationState(message.getValue(), message.getStringCount());
    }

}