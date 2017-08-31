package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class IntMessage implements LoggerMessage {

    private final int value;

    public IntMessage(int value) {
        this.value = value;
    }

    @Override
    public <T> T visit(MessageVisitor<T> visitor) {
        return visitor.accept(this);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntMessage that = (IntMessage) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value;
    }
}
