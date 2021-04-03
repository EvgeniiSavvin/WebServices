package main;

import client.PersonRequest;
import client.PersonWebService;
import exceptions.WrongInputFormatException;
import utils.CliUtils;

import java.util.Map;
import java.util.Scanner;

import static utils.PersonRequestUtils.*;
import static utils.StringConstants.*;

public class App {
    private final Scanner scanner;
    private final PersonWebService personService;

    public App(PersonWebService personWebService) {
        personService = personWebService;
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
                        CliUtils.printPersons(personService.getPersons(request));
                        break;

                    case Create:
                        ensureParamsAreNotEmptyOrThrow(input);
                        request = createRequestFromUserInput(getParams(input));
                        ensureAllFieldsAreSetOrFail(request);
                        CliUtils.printId(personService.createPerson(request));
                        break;

                    case Update:
                        ensureParamsAreNotEmptyOrThrow(input);
                        request = createRequestFromUserInput(getParams(input));
                        ensureIdAndAtLeastOneFieldIsSetOrFail(request);
                        CliUtils.printOperationResult(personService.updatePerson(request), Update);
                        break;

                    case Delete:
                        ensureParamsAreNotEmptyOrThrow(input);
                        int id = parseDeleteRequest(getParams(input));
                        CliUtils.printOperationResult(personService.deletePerson(id), Delete);
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
            }
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
