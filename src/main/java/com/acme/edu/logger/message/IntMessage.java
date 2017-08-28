package com.acme.edu.logger.message;

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
}
