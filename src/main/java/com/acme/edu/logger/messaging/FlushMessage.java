package com.acme.edu.logger.messaging;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class FlushMessage implements LoggerMessage {

    @Override
    public <T> T accept(MessageVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
