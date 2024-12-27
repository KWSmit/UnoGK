package nl.kwsmit.unogk;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigUnoClient {

    private Properties properties = new Properties();
    private String ipServer;
    private int port;

    public void readConfigFile() {
        File configFile = new File("config.properties");
        FileReader reader = null;
        try {
            reader = new FileReader(configFile);
            reader.close();
        } catch (FileNotFoundException e) {
            // file not found, set defauilt values.
            ipServer = "127.0.0.1";
            port = 6666;
        } catch (IOException e) {
            System.out.println("Error when closing config file: " + e.getMessage());
        }

        // load the config file.
        try {
            properties.load(reader);
            ipServer = properties.getProperty("ipServer", "127.0.0.1");
            port = Integer.parseInt(properties.getProperty("port", "6666"));
        } catch (IOException e) {
            // file not found, set defauilt values.
            ipServer = "127.0.0.1";
            port = 6666;
        }
    }

    public void saveConfigFile() {
        File configFile = new File("config.properties");
        try {
            FileWriter writer = new FileWriter(configFile);
            properties.store(writer, "client settings");
        } catch (IOException e) {
            System.out.println("Error saving config file: " + e.getMessage());
        }
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
