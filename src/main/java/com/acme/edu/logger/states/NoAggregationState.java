package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.messages.ByteMessage;
import com.acme.edu.logger.messaging.messages.IntMessage;
import com.acme.edu.logger.messaging.messages.SimpleMessage;
import com.acme.edu.logger.messaging.messages.StringMessage;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class NoAggregationState extends State {

    public NoAggregationState(String[] builtOutput) {
        super(builtOutput);
    }

    @Override
    public String[] getCurrentOutput() {
        return new String[0];
    }


    @Override
    public void accept(IntMessage message) {

    }

    @Override
    public void accept(ByteMessage message) {

    }

    @Override
    public void accept(StringMessage message) {

    }

    @Override
    public void accept(SimpleMessage message) {

    }
}
