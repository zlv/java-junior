package com.acme.edu.logger.server;

import com.acme.edu.logger.LoggerContext;
import com.acme.edu.logger.messaging.FlushMessage;
import com.acme.edu.logger.messaging.IntMessage;
import com.acme.edu.logger.messaging.LoggerMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Java_9 on 06.09.2017.
 */
public class MessageAcceptor {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    private final LoggerContext loggerContext;

    public MessageAcceptor(LoggerContext loggerContext) {
        this.loggerContext = loggerContext;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.submit(() -> {
                try (
                        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                        ) {
                    while (true) {
                        Object receivedObject = in.readObject();
                        if (receivedObject instanceof LoggerMessage) {
                            LoggerMessage newMessage = (LoggerMessage) receivedObject;
                            loggerContext.log(newMessage);
                        }
                    }
                } catch (EOFException ignored) {
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            try {
                printToPort(6666);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                printToPort(6666);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void printToPort(int i) throws IOException {
        LoggerClient loggerClient = new LoggerClient("127.0.0.1",i);
        for (int j = 0; j < 2; j++) {
            loggerClient.log(new IntMessage(j));
        }
        loggerClient.close();
    }

}
