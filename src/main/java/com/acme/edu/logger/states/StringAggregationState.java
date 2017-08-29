package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.messages.ByteMessage;
import com.acme.edu.logger.messaging.messages.IntMessage;
import com.acme.edu.logger.messaging.messages.SimpleMessage;
import com.acme.edu.logger.messaging.messages.StringMessage;

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
    public void accept(IntMessage message) {
//        return null;
    }

    @Override
    public void accept(ByteMessage message) {
//        return null;
    }

    @Override
    public void accept(StringMessage message) {
        if (Objects.equals(message.getValue(), currentValue)) {
            ++count;
//            return this;
        } else {
//            return new StringAggregationvoid(message.getValue(), getCurrentOutput());
        }
    }

    @Override
    public void accept(SimpleMessage message) {
        String resultValue = buildValue();
//        return new NoAggregationState(new String[] {resultValue, message.getFormattedMessage()});
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
