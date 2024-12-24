package nl.kwsmit.unogk;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

/**
 * Server
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    /**
     * Starts the server.
     *
     * @param port the port which the server listens on.
     */
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    /**
     * Connects the players for the game.
     * The number of players to connect is given in gameState.nrOfPlayers.
     *
     * @return the amount of connected clients.
     */
    public Client connectClient() throws IOException {
        // Wait for new client.
        clientSocket = serverSocket.accept();
        Client client = new Client();
        client.out = new PrintWriter(clientSocket.getOutputStream(), true);
        client.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        client.id = requestString(client, "id");
        return client;
    }

    /**
     * Sends message to all clients to disconnect and closes server.
     */
    public void close() throws IOException {
        for (Client client : clients) {
            client.out.println("bye");
        }
        serverSocket.close();
    }

    /**
     * Sends request to the client and returns the response of the client.
     *
     * @param client  the client for the request.
     * @param request the request.
     * @return the response of the client.
     * @throws IOException
     */
    public String requestString(Client client, String request) throws IOException {
        client.out.println(request);
        return client.in.readLine();
    }

    /**
     * Start the game.
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        String response;
        int nrOfGames = 2;
        try {
            server.start(6666);
            for (int i = 0; i < nrOfGames; i++) {
                System.out.println("\n=== Game " + (i + 1) + " ===");
                System.out.println("Waiting for clients to connect...");
                server.connectClients();
                System.out.println(server.clients.size() + " connected:");
                for (Client client : server.clients) {
                    System.out.println(client.id);
                }
                Thread.sleep(2000);
                System.out.println("Requesting action from clients");
                for (Client client : server.clients) {
                    try {
                        response = server.requestString(client, "action");
                        System.out.println(response);
                    } catch (IOException e) {
                        System.out.println("Error requesting action to client: " + client.id);
                    }
                }
                Thread.sleep(2000);
                System.out.println("Sending bye to clients");
                for (Client client : server.clients) {
                    try {
                        response = server.requestString(client, "bye");
                        System.out.println(response);
                    } catch (IOException e) {
                        System.out.println("Error requesting bye to client: " + client.id);
                    }
                }
            }
            server.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
