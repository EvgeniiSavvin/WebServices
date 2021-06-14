package main;

import service.PersonWebService;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "false");
        String url = "http://0.0.0.0:8081/PersonService";
        Endpoint.publish(url, new PersonWebService());
    }
}
