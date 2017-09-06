package com.acme.edu.legacy;

import com.acme.edu.logger.LoggerContext;
import com.acme.edu.logger.LoggerHandler;
import com.acme.edu.logger.formatters.DefaultLoggerFormatter;
import com.acme.edu.logger.formatters.LoggerFormatter;
import com.acme.edu.logger.messaging.*;
import com.acme.edu.logger.savers.ConsoleLoggerPrinter;
import com.acme.edu.logger.savers.LoggerPrinter;
import com.acme.edu.logger.server.MessageAcceptor;
import org.jetbrains.annotations.Nullable;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Logger {
    private static final LoggerContext loggerContext;

    private Logger() {

    }

    static {
        LoggerPrinter printer = new ConsoleLoggerPrinter();
        LoggerFormatter formatter = new DefaultLoggerFormatter();
        LoggerHandler handler = new LoggerHandler(message -> {
            try {
                printer.println(message.accept(formatter));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loggerContext = new LoggerContext(handler::log);
    }

    public static void flush() {
        loggerContext.log(new FlushMessage());
    }

    public static void log(int value) {
        loggerContext.log(new IntMessage(value));
    }

    public static void log(byte value) {
        loggerContext.log(new ByteMessage(value));
    }

    public static void log(boolean value) {
        loggerContext.log(new BooleanMessage(value));
    }

    public static void log(char value) {
        loggerContext.log(new CharMessage(value));
    }

    public static void log(@Nullable String value) {
        loggerContext.log(new StringMessage(value, 1));
    }

    public static void log(@Nullable Object value) {
        loggerContext.log(new ObjectMessage(value));
    }

    public static void log(@Nullable int[] value) {
        loggerContext.log(new IntArrayMessage(value));
    }

    public static void main(String[] args) throws IOException {
        MessageAcceptor acceptor = new MessageAcceptor(loggerContext);
        acceptor.start();
    }

}
