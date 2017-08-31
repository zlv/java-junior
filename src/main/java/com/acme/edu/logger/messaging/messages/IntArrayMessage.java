package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

import java.util.Arrays;

/**
 * Created by Java_9 on 31.08.2017.
 */
public class IntArrayMessage implements LoggerMessage {

    private final int[] value;

    public IntArrayMessage(int[] value) {
        this.value = value;
    }

    @Override
    public <T> T visit(MessageVisitor<T> visitor) {
        return visitor.accept(this);
    }

    public int[] getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntArrayMessage that = (IntArrayMessage) o;

        return Arrays.equals(value, that.value);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
