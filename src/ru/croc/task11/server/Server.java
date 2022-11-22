package ru.croc.task11.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server realization
public class Server {
    private final ServerSocket serverSocket;
    public Server (ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = getServerSocket().accept();
                System.out.println("New client connected to our server!");
                ServerWorker serverWorker = new ServerWorker(socket);
                Thread thread = new Thread(serverWorker);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            downServer();
        }
    }

    private void downServer() {
        try {
            if (getServerSocket() != null) {
                getServerSocket().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket1 = new ServerSocket(8080);
        Server server = new Server(serverSocket1);
        server.startServer();
    }
}
