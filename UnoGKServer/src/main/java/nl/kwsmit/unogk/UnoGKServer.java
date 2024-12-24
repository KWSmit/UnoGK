package nl.kwsmit.unogk;

import java.io.IOException;
import java.util.ArrayList;

class ConfigUnoGK {
    int port = 6666;
    int nrOfPlayers = 2;
}

/**
 * This class contains all the logic of the game.
 */
public class UnoGKServer {
    public static void main(String[] args) {
        ConfigUnoGK config = new ConfigUnoGK();
        GameState gameState = new GameState();
        Server server = new Server();
        ArrayList<Client> clients = new ArrayList<>();

        // Start server.
        try {
            server.start(config.port);
            System.out.println("UnoGK Server starting...");
        } catch (IOException e) {
            System.out.println("Error on starting server:");
            System.out.println(e.getMessage());
            System.exit(0);
        }

        // Set number of players.
        gameState.setNrOfPlayers(config.nrOfPlayers);

        // Connect players.
        try {
            for (int i = 0; i < config.nrOfPlayers; i++) {
                clients.add(server.connectClient());
            }
        } catch (IOException e) {
            System.out.println("Error on connecting clients:");
            System.out.println(e.getMessage());
        }
        if (clients.size() < config.nrOfPlayers) {
            System.out.println("Not enough players are connected, closing program.");
            try {
                server.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.exit(0);
        }
        System.out.println("\n" + clients.size() + " are connected.");

        for (Client client : clients) {
            try {
                client.out.println("id");
                client.id = client.in.readLine();
            } catch (IOException e) {
                System.err.println("Error on request player's name:");
                System.err.println(e.getMessage());
            }
        }
        for (Client client : clients) {
            try {
                client.out.println("action");
                System.out.println(client.in.readLine());
            } catch (IOException e) {
                System.err.println("Error on request player's action:");
                System.err.println(e.getMessage());
            }
        }
        for (Client client : clients) {
            try {
                client.out.println("bye");
                System.out.println(client.in.readLine());
            } catch (IOException e) {
                System.err.println("Error on request player's bye:");
                System.err.println(e.getMessage());
            }
        }
    }
}
