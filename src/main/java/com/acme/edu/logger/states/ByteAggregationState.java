package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.ByteMessage;
import com.acme.edu.logger.messaging.IntMessage;
import com.acme.edu.logger.messaging.LoggerMessage;
import com.acme.edu.logger.messaging.StringMessage;

import java.util.ArrayList;
import java.util.List;

import static com.acme.edu.logger.states.AggregationStateUtils.getRestInConstraints;
import static com.acme.edu.logger.states.AggregationStateUtils.getSumInConstraints;

/**
 * Created by Java_9 on 31.08.2017.
 */
public class ByteAggregationState extends State {

    private long sum;

    private static final byte UPPER_BOUND = Byte.MAX_VALUE;
    private static final byte LOWER_BOUND = Byte.MIN_VALUE;

    public ByteAggregationState(long sum) {
        this.sum = sum;
    }

    @Override
    public List<LoggerMessage> getResultOutput() {
        List<LoggerMessage> result = new ArrayList<>();
        long sumInConstraints = getSumInConstraints(sum, LOWER_BOUND, UPPER_BOUND);
        long rest = getRestInConstraints(sum, LOWER_BOUND, UPPER_BOUND);
        if (rest != 0) {
            result.add(new ByteMessage((byte) rest));
        }
        result.add(new ByteMessage((byte) sumInConstraints));
        return result;
    }

    @Override
    public boolean isDifferentState(State other) {
        return !(other instanceof ByteAggregationState);
    }

    @Override
    public State visit(IntMessage message) {
        return new IntAggregationState(message.getValue());
    }

    @Override
    public State visit(ByteMessage message) {
        sum += message.getValue();
        return this;
    }

    @Override
    public State visit(StringMessage message) {
        return new StringAggregationState(message.getValue(), message.getStringCount());
    }

}
