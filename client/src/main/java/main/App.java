package main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import model.FindPersonRequest;
import exceptions.WrongInputFormatException;
import model.Person;
import utils.CliUtils;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static utils.StringConstants.*;

public class App {

    private final Scanner scanner;
    private final Client client;
    private static final String url = "http://localhost:8080/Lab4_Server-1.0/rest/persons";

    public App(Client client) {
        this.client = client;
        scanner = new Scanner(System.in);
    }

    public void start() {
        CliUtils.printUsage();
        boolean run = true;
        String input;
        String command;
        String params;
        Map<String, String> paramMap;
        FindPersonRequest request;
        int commandEnd;
        while (run) {
            CliUtils.printPrompt();
            input = CliUtils.removeWhitespaces(scanner.nextLine());
            commandEnd = getCommandEndIndex(input);
            command = input.substring(0, commandEnd).toLowerCase();
            if (command.equals(Find)) {
                input = CliUtils.removeSpacesAroundEqualsSigns(input);
                params = input.substring(commandEnd).toLowerCase().trim();
                try{
                    paramMap = CliUtils.parseParams(params);
                    request = parseRequest(paramMap);
                    CliUtils.printPersons(getPersons(request));
                } catch (WrongInputFormatException e){
                    System.err.println(e.getMessage());
                    CliUtils.printUsage();
                }
            } else if (command.equals(Exit)) {
                run = false;
            } else {
                System.err.println(unknownCommand);
            }
        }
    }

    private List<Person> getPersons(FindPersonRequest request){
        WebResource resource = client.resource(url);
        resource = addParametersToResource(resource, request);
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Person>> type = new GenericType<List<Person>>() {};
        return response.getEntity(type);
    }

    private WebResource addParametersToResource(WebResource inputResource, FindPersonRequest request){
        WebResource resource = inputResource;
        if(request.isFirstNameSet()) resource = resource.queryParam("firstname", request.getFirstName());
        if(request.isLastNameSet()) resource = resource.queryParam("lastname", request.getLastName());
        if(request.isAgeSet()) resource = resource.queryParam("age", "" + request.getAge());
        if(request.isHeightSet()) resource = resource.queryParam("height", "" + request.getHeight());
        if(request.isMaleSet()) resource = resource.queryParam("isMale", "" + request.isMale());
        return resource;
    }

    private FindPersonRequest parseRequest(Map<String, String> params) throws WrongInputFormatException {
        FindPersonRequest request = createEmptyRequest();
        String key, value;
        for (Map.Entry<String, String> entry : params.entrySet()){
            key = entry.getKey();
            value = entry.getValue();
            switch (key){
                case FirstName:
                    request.setFirstName(value);
                    request.setFirstNameSet(true);
                    break;
                case LastName:
                    request.setLastName(value);
                    request.setLastNameSet(true);
                    break;
                case Age:
                    try{
                        int age = Integer.parseInt(value);
                        request.setAge(age);
                        request.setAgeSet(true);
                    } catch (NumberFormatException e){
                        throw new WrongInputFormatException(String.format(parShouldBeInteger, Age));
                    }
                    break;
                case Height:
                    try{
                        int height = Integer.parseInt(value);
                        request.setHeight(height);
                        request.setHeightSet(true);
                    } catch (NumberFormatException e){
                        throw new WrongInputFormatException(String.format(parShouldBeInteger, Height));
                    }
                    break;
                case Gender:
                    switch (value){
                        case Male:
                            request.setMale(true);
                            request.setMaleSet(true);
                            break;
                        case Female:
                            request.setMale(false);
                            request.setMaleSet(true);
                            break;
                        default:
                            throw new WrongInputFormatException(wrongGender);
                    }
                    break;
                default:
                    throw new WrongInputFormatException(String.format(unknownParameter, key));
            }
        }
        return request;
    }

    private FindPersonRequest createEmptyRequest(){
        FindPersonRequest request = new FindPersonRequest();
        request.setFirstNameSet(false);
        request.setLastNameSet(false);
        request.setAgeSet(false);
        request.setHeightSet(false);
        request.setMaleSet(false);
        return request;
    }

    private int getCommandEndIndex(String input){
        int firstSpace = input.indexOf(' ');
        return firstSpace < 0 ? input.length() : firstSpace;
    }

}
