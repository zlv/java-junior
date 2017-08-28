package com.acme.edu.logger.states;

import com.acme.edu.logger.message.ByteMessage;
import com.acme.edu.logger.message.IntMessage;
import com.acme.edu.logger.message.SimpleMessage;
import com.acme.edu.logger.message.StringMessage;

import java.util.Objects;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class StringAggregationState extends State {

    private String currentValue;
    private int count;

    public StringAggregationState(String currentValue, String[] builtOutput) {
        super(builtOutput);
        this.currentValue = currentValue;
        this.count = 1;
    }

    @Override
    public String[] getCurrentOutput() {
        return new String[] {buildValue()};
    }

    @Override
    public State acceptMessage(IntMessage message) {
        return null;
    }

    @Override
    public State acceptMessage(ByteMessage message) {
        return null;
    }

    @Override
    public State acceptMessage(StringMessage message) {
        if (Objects.equals(message.getValue(), currentValue)) {
            ++count;
            return this;
        } else {
            return new StringAggregationState(message.getValue(), getCurrentOutput());
        }
    }

    @Override
    public State acceptMessage(SimpleMessage message) {
        String resultValue = buildValue();
        return new NoAggregationState(new String[] {resultValue, message.getFormattedMessage()});
    }

    private String buildValue() {
        StringBuilder builder = new StringBuilder(Objects.toString(currentValue));
        if (count > 1) {
            builder.append(" (x")
                    .append(count)
                    .append(")");
        }
        return builder.toString();
    }
}
