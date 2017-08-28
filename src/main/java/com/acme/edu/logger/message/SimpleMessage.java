package com.acme.edu.logger.message;

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
}
