package nl.kwsmit.unogk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigUnoServer {

    private Properties properties = new Properties();
    private int port;
    private int nrOfPlayers;

    public void readConfigFile() {
        File configFile = new File("config.properties");
        FileReader reader = null;
        try {
            reader = new FileReader(configFile);
            reader.close();
        } catch (FileNotFoundException e) {
            // file not found, set defauilt values.
            port = 6666;
            nrOfPlayers = 2;
        } catch (IOException e) {
            System.out.println("Error when closing config file: " + e.getMessage());
        }

        // load the config file.
        try {
            properties.load(reader);
            port = Integer.parseInt(properties.getProperty("port", "6666"));
            nrOfPlayers = Integer.parseInt(properties.getProperty("nrOfPlayers", "2"));
        } catch (IOException e) {
            // file not found, set defauilt values.
            port = 6666;
            nrOfPlayers = 2;
        }
    }

    public void saveConfigFile() {
        File configFile = new File("config.properties");
        try {
            FileWriter writer = new FileWriter(configFile);
            properties.store(writer, "server settings");
        } catch (IOException e) {
            System.out.println("Error saving config file: " + e.getMessage());
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getNrOfPlayers() {
        return nrOfPlayers;
    }

    void setNrOfPlayers(int nrOfPlayers) {
        this.nrOfPlayers = nrOfPlayers;
    }

}
