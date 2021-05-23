package main;

import client.PersonService;
import client.PersonWebService;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:8080/PersonService?wsdl");

        PersonService service = new PersonService(url);
        PersonWebService personService = service.getPersonWebServicePort();

        BindingProvider bp = (BindingProvider) personService;
        Map<String, Object> map = bp.getRequestContext();
        map.put(BindingProvider.USERNAME_PROPERTY, "admin");
        map.put(BindingProvider.PASSWORD_PROPERTY, "admin");

        App app = new App(personService);
        app.start();
    }
}
