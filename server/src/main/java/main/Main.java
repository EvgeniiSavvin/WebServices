package main;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import exceptions.PersonServiceExceptionMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import resources.PersonIdResource;
import resources.PersonResource;

import java.io.IOException;
import java.net.URI;

public class Main {

    private static final URI BASE_URI = URI.create("http://localhost:8080/rest/");

    public static void main(String[] args) {
        HttpServer server;
        try{
            ResourceConfig config = new ClassNamesResourceConfig(PersonResource.class, PersonIdResource.class, PersonServiceExceptionMapper.class);
            config.getProperties().put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, "resources.auth.AuthenticationFilter");
            server = GrizzlyServerFactory.createHttpServer(BASE_URI, config);
            server.start();
            System.in.read();
            stopServer(server);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void stopServer(HttpServer server) {
        if (server != null)
            server.stop();
    }
}
