package main;

import com.sun.jersey.api.client.Client;

public class Main {
    public static void main(String[] args) {
        Client client = Client.create();
        App app = new App(client);
        app.start();
    }
}
