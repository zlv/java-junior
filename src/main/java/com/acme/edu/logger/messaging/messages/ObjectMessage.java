package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class ObjectMessage implements LoggerMessage {

    private final Object value;

    public ObjectMessage(Object value) {
        this.value = value;
    }

    @Override
    public <T> T visit(MessageVisitor<T> visitor) {
        return visitor.accept(this);
    }

    public Object getValue() {
        return value;
    }
}
