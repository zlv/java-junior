package com.acme.edu.logger.messaging;

import java.io.Serializable;

/**
 * Created by Java_9 on 28.08.2017.
 */
public interface LoggerMessage extends Serializable {
    <T> T accept(MessageVisitor<T> visitor);
}
