package com.acme.edu.logger.messaging;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class IntMessage implements LoggerMessage {

    private final int value;

    public IntMessage(int value) {
        this.value = value;
    }

    @Override
    public <T> T accept(MessageVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public int getValue() {
        return value;
    }

}
