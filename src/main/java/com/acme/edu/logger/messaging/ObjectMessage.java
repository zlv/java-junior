package com.acme.edu.logger.messaging;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class ObjectMessage implements LoggerMessage {

    private final Object value;

    public ObjectMessage(Object value) {
        this.value = value;
    }

    @Override
    public <T> T accept(MessageVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Object getValue() {
        return value;
    }

}
