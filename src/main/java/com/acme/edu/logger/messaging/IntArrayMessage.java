package com.acme.edu.logger.messaging;

import com.acme.edu.logger.messaging.LoggerMessage;
import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 31.08.2017.
 */
public class IntArrayMessage implements LoggerMessage {

    private final int[] value;

    public IntArrayMessage(int[] value) {
        this.value = value;
    }

    @Override
    public <T> T accept(MessageVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public int[] getValue() {
        return value;
    }

}
