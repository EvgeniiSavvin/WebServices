package main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Main {
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(LOGIN, PASSWORD));
        App app = new App(client);
        app.start();
    }
}
