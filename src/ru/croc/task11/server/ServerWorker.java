package ru.croc.task11.server;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// The layer between the server and the client, supports the connection of a single client
public class ServerWorker implements Runnable {
    private Socket serverSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    // A thread-safe list that stores the currently connected clients
    public static List<ServerWorker> listOfUsers = new CopyOnWriteArrayList<>();
    private String clientUsername;

    public ServerWorker(Socket socket) throws IOException {
        try {
            this.serverSocket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = reader.readLine();
            listOfUsers.add(this);
            sendMessage(clientUsername + " has entered the chat!");
        } catch (IOException e) {
            e.printStackTrace();
            downServerWorker();
        }
    }

    @Override
    public void run() {
        while (getServerSocket().isConnected()) {
            try {
                String messageFromClient = getReader().readLine();
                sendMessage(messageFromClient);
            } catch (IOException e) {
                e.printStackTrace();
                downServerWorker();
                break;
            }
        }
    }

    private void sendMessage(String msg) {
        for (ServerWorker listener : listOfUsers) {
            try {
                if (!listener.getClientUsername().equals(this.getClientUsername())) {
                    listener.getWriter().write(msg);
                    listener.getWriter().newLine();
                    listener.getWriter().flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                downServerWorker();
            }
        }
    }

    private void downServerWorker() {
        try {
            if(!getServerSocket().isClosed()) {
                getServerSocket().close();
                getReader().close();
                getWriter().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}
