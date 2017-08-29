package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class IntMessage implements LoggerMessage {
    private int value;

    public IntMessage(int value) {
        this.value = value;
    }

    @Override
    public String getFormattedMessage() {
        return null;
    }

    @Override
    public void visit(MessageVisitor visitor) {

    }
}
