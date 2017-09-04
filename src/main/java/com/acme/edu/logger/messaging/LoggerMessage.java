package com.acme.edu.logger.messaging;

/**
 * Created by Java_9 on 28.08.2017.
 */
public interface LoggerMessage {
    <T> T accept(MessageVisitor<T> visitor);
}
