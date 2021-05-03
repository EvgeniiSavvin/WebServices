package main;

import client.PersonRequest;
import client.PersonServiceException_Exception;
import client.PersonWebService;
import exceptions.WrongInputFormatException;
import utils.CliUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
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
        getCatImage();
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
            } catch (PersonServiceException_Exception e) {
                System.err.println(e.getMessage() + ": " + e.getFaultInfo().getMessage());
            }
        }
    }

    private void getCatImage(){
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(personService.getCatPhoto()));
            ImageIO.write(image, "jpg", new File("cat2.jpg"));
        } catch (IOException e){
            System.err.println("Failed to get cat photo =( " + e.getMessage());
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
