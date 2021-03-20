package main;

import client.PersonService;
import client.PersonWebService;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PersonService?wsdl");
        PersonWebService personService = new PersonService(url).getPersonWebServicePort();
        App app = new App(personService);
        app.start();
    }
}
