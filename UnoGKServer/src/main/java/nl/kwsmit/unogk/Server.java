package nl.kwsmit.unogk;

import java.net.*;
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
        client.name = requestString(client, "name");
        return client;
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
}
