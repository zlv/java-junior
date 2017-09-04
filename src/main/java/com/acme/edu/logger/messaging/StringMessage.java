package com.acme.edu.logger.messaging;

import com.acme.edu.logger.messaging.LoggerMessage;
import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class StringMessage implements LoggerMessage {

    private final String value;
    private final int stringCount;

    public StringMessage(String value, int stringCount) {
        this.value = value;
        this.stringCount = stringCount;
    }

    public String getValue() {
        return value;
    }

    public int getStringCount() {
        return stringCount;
    }


    @Override
    public <T> T accept(MessageVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
