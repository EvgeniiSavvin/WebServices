package main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import exceptions.WrongInputFormatException;
import model.Person;
import model.PersonRequest;
import utils.CliUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Scanner;

import static utils.PersonRequestUtils.*;
import static utils.StringConstants.*;

public class App {

    private final Scanner scanner;
    private final Client client;
    private static final String url = "http://localhost:8080/rest/persons";

    public App(Client client) {
        this.client = client;
        scanner = new Scanner(System.in);
    }

    public void start() {
        CliUtils.printUsage();
        boolean run = true;
        String input;
        String command;
        PersonRequest request;
        while (run) {
            CliUtils.printPrompt();
            input = CliUtils.removeWhitespaces(scanner.nextLine());
            command = getCommand(input).toLowerCase();
            try {
                switch (command){
                    case Find:
                        request = createRequestFromUserInput(getParams(input));
                        CliUtils.printPersons(getPersons(request));
                        break;

                    case Create:
                        ensureParamsAreNotEmptyOrThrow(input);
                        request = createRequestFromUserInput(getParams(input));
                        ensureAllFieldsAreSetOrFail(request);
                        CliUtils.printId(createPerson(request));
                        break;

                    case Update:
                        ensureParamsAreNotEmptyOrThrow(input);
                        request = createRequestFromUserInput(getParams(input));
                        ensureIdAndAtLeastOneFieldIsSetOrFail(request);
                        CliUtils.printOperationResult(updatePerson(request), Update);
                        break;

                    case Delete:
                        ensureParamsAreNotEmptyOrThrow(input);
                        int id = parseDeleteRequest(getParams(input));
                        CliUtils.printOperationResult(deletePerson(id), Delete);
                        break;

                    case Exit:
                        run = false;
                        break;

                    default:
                        System.err.println(UnknownCommand);
                }
            } catch (WrongInputFormatException e) {
                System.err.println(e.getMessage());
                CliUtils.printUsage();
            } catch (IllegalArgumentException e){
                System.err.println(e.getMessage());
            }
        }
    }

    private int deletePerson(int id){
        WebResource resource = client.resource(url + "/" + id);
        ClientResponse response = resource.accept(MediaType.TEXT_PLAIN).delete(ClientResponse.class);
        throwIfResponseIsInvalid(response);
        GenericType<String> type = new GenericType<String>() {};
        return Integer.parseInt(response.getEntity(type));
    }

    private int updatePerson(PersonRequest request){
        WebResource resource = client.resource(url + "/" + request.getId());
        ClientResponse response = resource
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .put(ClientResponse.class, generateFromData(request));
        throwIfResponseIsInvalid(response);
        GenericType<String> type = new GenericType<String>() {};
        return Integer.parseInt(response.getEntity(type));
    }

    private int createPerson(PersonRequest request){
        WebResource resource = client.resource(url);
        ClientResponse response = resource
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, generateFromData(request));
        throwIfResponseIsInvalid(response);
        GenericType<String> type = new GenericType<String>() {};
        return Integer.parseInt(response.getEntity(type));
    }

    private List<Person> getPersons(PersonRequest request){
        WebResource resource = client.resource(url);
        resource = addQueryParametersToResource(resource, request);
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        throwIfResponseIsInvalid(response);
        GenericType<List<Person>> type = new GenericType<List<Person>>() {};
        return response.getEntity(type);
    }

    private WebResource addQueryParametersToResource(WebResource inputResource, PersonRequest request){
        WebResource resource = inputResource;
        if(request.isFirstNameSet()) resource = resource.queryParam("firstname", request.getFirstName());
        if(request.isLastNameSet()) resource = resource.queryParam("lastname", request.getLastName());
        if(request.isAgeSet()) resource = resource.queryParam("age", "" + request.getAge());
        if(request.isHeightSet()) resource = resource.queryParam("height", "" + request.getHeight());
        if(request.isMaleSet()) resource = resource.queryParam("isMale", "" + request.isMale());
        return resource;
    }

    private MultivaluedMap<String, String> generateFromData(PersonRequest request){
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        if(request.isFirstNameSet()) formData.add("firstname", request.getFirstName());
        if(request.isLastNameSet()) formData.add("lastname", request.getLastName());
        if(request.isAgeSet()) formData.add("age", "" + request.getAge());
        if(request.isHeightSet()) formData.add("height", "" + request.getHeight());
        if(request.isMaleSet()) formData.add("isMale", "" + request.isMale());
        return formData;
    }

    private void throwIfResponseIsInvalid(ClientResponse response){
        if(response.getStatus() == ClientResponse.Status.BAD_REQUEST.getStatusCode()){
            GenericType<String> type = new GenericType<String>() {};
            String entity = response.getEntity(type);
            throw new IllegalArgumentException(entity);
        }
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            GenericType<String> type = new GenericType<String>() {};
            String entity = response.getEntity(type);
            throw new IllegalStateException("Request failed. Status " + response.getStatus() + " Entity: " + entity);
        }
    }

    private void ensureParamsAreNotEmptyOrThrow(String input) throws WrongInputFormatException {
        if(input.trim().isEmpty()) throw new WrongInputFormatException(ParametersRequired);
    }

    private void ensureAllFieldsAreSetOrFail(PersonRequest request) throws WrongInputFormatException {
        if(!allDataFieldsAreSet(request)) throw new WrongInputFormatException(AllDataFieldsRequired);
    }

    private void ensureIdAndAtLeastOneFieldIsSetOrFail(PersonRequest request) throws WrongInputFormatException {
        if(!(idIsSet(request) && atLeastOneDataFieldSet(request))) throw new WrongInputFormatException(IdAndAtLeastOneDataFieldRequired);
    }

    private int parseDeleteRequest(String input) throws WrongInputFormatException{
        try{
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e){
            throw new WrongInputFormatException(String.format(ParShouldBeInteger, Id));
        }
    }

    private String getCommand(String input){
        return input.substring(0, getCommandEndIndex(input)).trim();
    }

    private String getParams(String input){
        return input.substring(getCommandEndIndex(input)).trim();
    }

    private int getCommandEndIndex(String input){
        int firstSpace = input.indexOf(' ');
        return firstSpace < 0 ? input.length() : firstSpace;
    }

}
