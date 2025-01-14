package nl.kwsmit.unogk;

import java.net.*;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Client application.
 */
public class UnoGKClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String readMessage() throws IOException {
        String response = in.readLine();
        System.out.println(response);
        return response;
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConfigUnoClient config = new ConfigUnoClient();
        UnoGKClient client = new UnoGKClient();
        String username;
        String response;
        boolean stayConnected = true;
        // read config from file
        config.readConfigFile();

        // get username
        System.out.println("Enter your name:");
        username = scanner.nextLine();
        scanner.close();

        try {
            client.startConnection(config.getIpServer(), config.getPort());
            while (stayConnected) {
                response = client.readMessage();
                switch (response) {
                    case "id":
                        // Send username to server.
                        client.sendMessage(username);
                        break;
                    case "action":
                        // Send username to server.
                        client.sendMessage("- action " + username + ": R3");
                        break;
                    case "bye":
                        // Close client.
                        client.sendMessage("- bye from " + username);
                        stayConnected = false;
                        break;
                }
                System.out.println("..");
            }
            client.stopConnection();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
