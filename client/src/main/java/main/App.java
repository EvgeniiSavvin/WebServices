package main;

import client.FindPersonRequest;
import client.PersonWebService;
import exceptions.WrongInputFormatException;
import utils.CliUtils;

import java.util.Map;
import java.util.Scanner;

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
                    CliUtils.printPersons(personService.getPersons(request));
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
