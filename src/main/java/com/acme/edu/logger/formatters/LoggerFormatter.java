package com.acme.edu.logger.formatters;

import com.acme.edu.logger.messaging.MessageVisitor;

public interface LoggerFormatter extends MessageVisitor<String> {
}
