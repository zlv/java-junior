package com.acme.edu.logger.messaging;

import com.acme.edu.logger.messaging.messages.ByteMessage;
import com.acme.edu.logger.messaging.messages.IntMessage;
import com.acme.edu.logger.messaging.messages.SimpleMessage;
import com.acme.edu.logger.messaging.messages.StringMessage;

/**
 * Created by Java_9 on 29.08.2017.
 */
public interface MessageVisitor {
    void accept(IntMessage message);
    void accept(ByteMessage message);
    void accept(StringMessage message);
    void accept(SimpleMessage message);
}
