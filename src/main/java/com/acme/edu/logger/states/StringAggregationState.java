package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.messages.ByteMessage;
import com.acme.edu.logger.messaging.messages.IntMessage;
import com.acme.edu.logger.messaging.messages.LoggerMessage;
import com.acme.edu.logger.messaging.messages.StringMessage;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class StringAggregationState extends State {

    private String currentValue;
    private int count;

    public StringAggregationState(String currentValue, int count) {
        this.currentValue = currentValue;
        this.count = count;
    }

    @Override
    public List<LoggerMessage> getResultOutput() {
        return Collections.singletonList(new StringMessage(currentValue, count));
    }

    @Override
    public boolean isDifferentState(State other) {
        if (other instanceof StringAggregationState) {
            StringAggregationState that = (StringAggregationState) other;
            return !Objects.equals(that.currentValue, this.currentValue);
        }
        return true;
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
        if (Objects.equals(message.getValue(), currentValue)) {
            ++count;
            return this;
        } else {
            return new StringAggregationState(message.getValue(), message.getStringCount());
        }
    }

}
