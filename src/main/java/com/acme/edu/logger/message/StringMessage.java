package com.acme.edu.logger.message;

/**
 * Created by Java_9 on 28.08.2017.
 */
public class StringMessage implements LoggerMessage {

    private String value;

    public StringMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getFormattedMessage() {
        return value;
    }
}
