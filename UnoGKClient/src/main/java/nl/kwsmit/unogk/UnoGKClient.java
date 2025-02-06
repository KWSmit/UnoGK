package nl.kwsmit.unogk;

import java.net.*;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Client application.
 */
public class UnoGKClient {
    private Socket clientSocket;
    private String username;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String readMessage() throws IOException {
        String message = in.readLine();
        return message;
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.flush();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConfigUnoClient config = new ConfigUnoClient();
        UnoGKClient client = new UnoGKClient();
        HashMap<String, Player> players = new HashMap<>();
        ArrayList<String> cards = new ArrayList<>();
        String message;
        String[] data;
        boolean stayConnected = true;
        // read config from file
        config.readConfigFile();

        // get username
        System.out.println("Enter your name:");
        client.username = scanner.nextLine();
        scanner.close();

        try {
            client.startConnection(config.getIpServer(), config.getPort());
            while (stayConnected) {
                message = client.readMessage();
                data = message.split(";");
                switch (data[0]) {
                    case "name":
                        // Send username to server.
                        client.sendMessage(client.username);
                        break;
                    case "player":
                        // Add player to list of players.
                        for (int i = 1; i < data.length; i++) {
                            players.put(data[i], new Player(data[i]));
                            client.sendMessage("ok");
                            System.out.println("Player " + data[i]);
                        }
                        break;
                    case "addCard":
                        // Add card to the hand of this player.
                        cards.add(data[1]);
                        players.get(client.username).addToNumberOfCards(1);
                        client.sendMessage("ok");
                        break;
                    case "cards":
                        // Number of cards for player name.
                        players.get(data[1]).setNumberOfCards(Integer.parseInt(data[2]));
                        client.sendMessage("ok");
                        break;
                    case "action":
                        // Send username to server.
                        client.sendMessage("- action " + client.username + ": R3");
                        break;
                    case "bye":
                        // Close client.
                        client.sendMessage("- bye from " + client.username);
                        stayConnected = false;
                        break;
                }
                for (Player p : players.values()) {
                    System.out.println("Player " + p.getName() + " has " + p.getNumberOfCards() + " cards");
                }
                System.out.println("Cards of player " + client.username + ":");
                for (String card : cards) {
                    System.out.println(card);
                }
            }
            client.stopConnection();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
