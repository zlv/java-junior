package com.acme.edu.logger.messaging.messages;

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
    public <T> T visit(MessageVisitor<T> visitor) {
        return visitor.accept(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringMessage that = (StringMessage) o;

        if (stringCount != that.stringCount) return false;
        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + stringCount;
        return result;
    }
}
