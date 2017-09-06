package com.acme.edu.logger.server;

import com.acme.edu.logger.messaging.FlushMessage;
import com.acme.edu.logger.messaging.IntMessage;
import com.acme.edu.logger.messaging.LoggerMessage;

import java.io.*;
import java.net.Socket;

/**
 * Created by Java_9 on 06.09.2017.
 */
public class LoggerClient implements Closeable {
    private final Socket socket;
    private final ObjectOutputStream objectOutputStream;

    public LoggerClient(String address, int port) throws IOException {
        socket = new Socket(address, port);

        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        objectOutputStream = new ObjectOutputStream(outputStream);
    }

    public void log(LoggerMessage message) throws IOException {
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        objectOutputStream.close();
    }
}
