package com.acme.edu.logger.messaging;

/**
 * Created by Java_9 on 29.08.2017.
 */
public interface MessageVisitor<T> {
    T visit(IntMessage message);
    T visit(ByteMessage message);
    T visit(StringMessage message);
    T visit(ObjectMessage message);
    T visit(BooleanMessage message);
    T visit(CharMessage message);
    T visit(IntArrayMessage message);
    T visit(FlushMessage message);
}
