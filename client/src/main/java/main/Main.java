package main;

import client.PersonService;
import client.PersonWebService;
import uddi.SimpleBrowse;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        SimpleBrowse browser = new SimpleBrowse();
        String wsdlUrl = browser.findWsdl();
        if(wsdlUrl != null) {
            URL url = new URL("http://localhost:8081/PersonService?wsdl");
            PersonWebService personService = new PersonService(url).getPersonWebServicePort();
            App app = new App(personService);
            app.start();
        } else System.err.println("Cannot find wsdl url");
    }
}
