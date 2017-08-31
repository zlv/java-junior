package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 31.08.2017.
 */
public class BooleanMessage implements LoggerMessage {

    private final boolean value;

    public BooleanMessage(boolean value) {
        this.value = value;
    }

    @Override
    public <T> T visit(MessageVisitor<T> visitor) {
        return visitor.accept(this);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooleanMessage that = (BooleanMessage) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
