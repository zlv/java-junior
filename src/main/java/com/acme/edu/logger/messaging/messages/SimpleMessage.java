package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class SimpleMessage implements LoggerMessage {

    private Object value;

    public SimpleMessage(Object value) {
        this.value = value;
    }

    @Override
    public String getFormattedMessage() {
        return null;
    }

    @Override
    public void visit(MessageVisitor visitor) {
        //visitor.
    }
}
