package config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TrelloCredentials {
    private static final Properties props = new Properties();

    static {
        try {
            // Try loading from .env file first
            if (Files.exists(Paths.get(".env"))) {
                props.load(Files.newBufferedReader(Paths.get(".env")));
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not load .env file");
        }
           System.out.println("Properties have been loaded"); // 'props' from TrelloCredentials
    File envFile = new File(".env");
if (!envFile.exists()) {
    System.err.println(".env file not found at: " + envFile.getAbsolutePath());
} else {
    System.out.println(".env file found at: " + envFile.getAbsolutePath());

    /*try {
        props.load(Files.newBufferedReader(Paths.get(".env")));
        System.out.println("Loaded Properties: " + props);
    } catch (IOException e) {
        System.err.println("Error loading .env file: " + e.getMessage());
    }*/
}
    }


    public static String getApiKey() {
        // Try environment variables first, then system properties, then .env file
        String apiKey = System.getenv("TRELLO_API_KEY");
        if (apiKey == null) {
            apiKey = System.getProperty("TRELLO_API_KEY");
        }
        if (apiKey == null) {
            apiKey = props.getProperty("TRELLO_API_KEY");
        }
        if (apiKey == null) {
            throw new IllegalStateException("TRELLO_API_KEY not found in environment variables, system properties, or .env file");
        }
        return apiKey;
    }

    public static String getToken() {
        String token = System.getenv("TRELLO_TOKEN");
        if (token == null) {
            token = System.getProperty("TRELLO_TOKEN");
        }
        if (token == null) {
            token = props.getProperty("TRELLO_TOKEN");
        }
        if (token == null) {
            throw new IllegalStateException("TRELLO_TOKEN not found in environment variables, system properties, or .env file");
        }
        return token;
    }
}