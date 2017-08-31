package com.acme.edu.logger.messaging;

import com.acme.edu.logger.messaging.messages.*;

/**
 * Created by Java_9 on 29.08.2017.
 */
public interface MessageVisitor<T> {
    T accept(IntMessage message);
    T accept(ByteMessage message);
    T accept(StringMessage message);
    T accept(ObjectMessage message);
    T accept(BooleanMessage message);
    T accept(CharMessage message);
    T accept(IntArrayMessage message);
}
