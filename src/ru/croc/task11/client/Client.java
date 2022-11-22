package ru.croc.task11.client;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

// Client realization and communication
public class Client {
    private Socket serverSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String clientUsername;

    public Client(Socket socket, String clientUsername) {
        try {
            this.serverSocket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = clientUsername;
        } catch (IOException e) {
            e.printStackTrace();
            downClient();
        }
    }

    private void sendMessage() {
        try {
            getWriter().write(getClientUsername());
            getWriter().newLine();
            getWriter().flush();

            Scanner scanner = new Scanner(System.in);
            while (getServerSocket().isConnected()) {
                String message = scanner.nextLine();
                getWriter().write("[Client: " + getClientUsername() + " ---write at--- " +
                        LocalDateTime.now() + "]: " + message);
                getWriter().newLine();
                getWriter().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            downClient();
        }
    }

    private void listenMessages() {
        Thread listenThread = new Thread(() -> {
            while (getServerSocket().isConnected()) {
                try {
                    String message = getReader().readLine();
                    System.out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    downClient();
                }
            }
        });
        listenThread.start();
    }

    private void downClient() {
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

    public static void main(String[] args) throws IOException {
        System.out.println("Dear Client, please enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String userName = "";
        do {
            userName = scanner.nextLine();
            if (userName.equals("")) {
                System.out.println("Invalid name, please enter normal name: ");
            }
        } while (userName.equals(""));

        Socket socket = new Socket("127.0.0.1", 8080);
        Client client = new Client(socket, userName);
        client.listenMessages();
        client.sendMessage();
    }
}
