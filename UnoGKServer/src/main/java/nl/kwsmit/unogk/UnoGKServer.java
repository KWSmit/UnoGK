package nl.kwsmit.unogk;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains all the logic of the game.
 */
public class UnoGKServer {
    public static void main(String[] args) {
        ConfigUnoServer config = new ConfigUnoServer();
        GameState gameState = new GameState();
        Server server = new Server();
        ArrayList<Client> clients = new ArrayList<>();
        UnoDeck deck = new UnoDeck();
        UnoDeck stack = new UnoDeck();
        String response;

        // read configuration from file
        config.readConfigFile();

        // start server
        try {
            System.out.println("UnoGK Server starting...");
            server.start(config.getPort());
        } catch (IOException e) {
            System.out.println("Error on starting server:");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println("UnoGK Server started");

        // set number of players
        gameState.setNrOfPlayers(config.getNrOfPlayers());

        // connect players
        System.out.println("Connecting players...");
        try {
            for (int i = 0; i < config.getNrOfPlayers(); i++) {
                clients.add(server.connectClient());
            }
        } catch (IOException e) {
            System.out.println("Error on connecting clients:");
            System.out.println(e.getMessage());
        }
        if (clients.size() < config.getNrOfPlayers()) {
            System.out.println("Not enough players are connected, closing program.");
            for (Client client : clients) {
                client.out.println("bye");
            }
            System.exit(0);
        }
        System.out.println(clients.size() + " players are connected.");

        // Get name from all clients.
        System.out.println("Asking all clients for their name...");
        for (Client client : clients) {
            try {
                client.name = server.requestString(client, "name");
            } catch (IOException e) {
                System.err.println("Error on request player's name:");
                System.err.println(e.getMessage());
            }
        }

        // Send all player info to de clients.
        System.out.println("Sending player names to all clients...");
        String msg = "player";
        for (Client client : clients) {
            msg += ";" + client.name;
        }
        for (Client client : clients) {
            try {
                response = server.requestString(client, msg);
            } catch (IOException e) {
                System.err.println("Error on request player's name:");
                System.err.println(e.getMessage());
            }
        }

        // Create start deck and shuffle it.
        deck.newStartDeck();
        deck.shuffle();

        // Deal 7 cards to each player.
        for (int i = 0; i < 7; i++) {
            for (Client client : clients) {
                String card = deck.getCardByIndex(0);
                System.out.println("card to deal: " + card);
                try {
                    response = server.requestString(client, "addCard;" + card);
                } catch (IOException e) {
                    System.err.println("Error on dealing cards to player " + client.name);
                    System.err.println(e.getMessage());
                }
                client.numberOfCards += 1;
                deck.removeCardByIndex(0);
            }
        }
        // Send number of cards each players has to all clients.
        for (Client client : clients) {
            for (Client c : clients) {
                System.out.println(">" + client.name + "," + c.name);
                try {
                    response = server.requestString(client, "cards;" + c.name + ";" + c.numberOfCards);
                } catch (IOException e) {
                    System.err.println("Error on sendig number of cards to " + client.name);
                    System.err.println(e.getMessage());
                }
            }
        }

        // Test sending action to all clients.
        System.out.println("Sending action to all clients...");
        for (Client client : clients) {
            try {
                System.out.println(server.requestString(client, "action"));
            } catch (IOException e) {
                System.err.println("Error on request player's action:");
                System.err.println(e.getMessage());
            }
        }
        for (Client client : clients) {
            try {
                System.out.println(server.requestString(client, "bye"));
            } catch (IOException e) {
                System.err.println("Error on request player's bye:");
                System.err.println(e.getMessage());
            }
        }

        // Close connections.
        for (Client client : clients) {
            try {
                client.in.close();
                client.out.close();
            } catch (IOException e) {
                System.err.println("Error on closing connections:");
                System.err.println(e.getMessage());
            }
        }
    }
}
