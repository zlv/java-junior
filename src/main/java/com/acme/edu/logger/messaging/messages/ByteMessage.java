package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class ByteMessage implements LoggerMessage {

    private final byte value;

    public byte getValue() {
        return value;
    }

    public ByteMessage(byte value) {
        this.value = value;
    }

    @Override
    public <T> T visit(MessageVisitor<T> visitor) {
        return visitor.accept(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByteMessage that = (ByteMessage) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return (int) value;
    }

}
