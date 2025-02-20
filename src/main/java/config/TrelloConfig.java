package config;

public class TrelloConfig {
    private static final String BASE_URL = "https://api.trello.com";

    public static String getApiKey() {
        return TrelloCredentials.getApiKey();
    }

    public static String getToken() {
        return TrelloCredentials.getToken();
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}