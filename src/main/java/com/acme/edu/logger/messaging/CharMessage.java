package com.acme.edu.logger.messaging;

import com.acme.edu.logger.messaging.LoggerMessage;
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
    public <T> T accept(MessageVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public char getValue() {
        return value;
    }

}
