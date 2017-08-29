package com.acme.edu.logger.messaging.messages;

import com.acme.edu.logger.messaging.MessageVisitor;

/**
 * Created by Java_9 on 28.08.2017.
 */
public interface LoggerMessage {
    String getFormattedMessage();
    void visit(MessageVisitor visitor);
}
