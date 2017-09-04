package com.acme.edu.logger.messaging;

/**
 * Created by Java_9 on 31.08.2017.
 */
public class BooleanMessage implements LoggerMessage {

    private final boolean value;

    public BooleanMessage(boolean value) {
        this.value = value;
    }

    @Override
    public <T> T accept(MessageVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public boolean getValue() {
        return value;
    }

}
