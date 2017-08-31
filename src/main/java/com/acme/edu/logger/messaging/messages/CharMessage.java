package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 31.08.2017.
 */
public class CharMessage implements LoggerMessage {

    private final char value;

    public CharMessage(char value) {
        this.value = value;
    }

    @Override
    public <T> T visit(MessageVisitor<T> visitor) {
        return visitor.accept(this);
    }

    public char getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharMessage that = (CharMessage) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
