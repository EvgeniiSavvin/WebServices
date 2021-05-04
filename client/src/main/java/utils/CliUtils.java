package utils;

import model.Person;
import exceptions.WrongInputFormatException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.StringConstants.*;

public class CliUtils {

    public static void printPrompt(){System.out.print(Prompt);}

    public static void printUsage(){System.out.println(Usage);}

    public static Map<String, String> parseParams(String input) throws WrongInputFormatException {
        Map<String, String> params = new HashMap<>();
        if(input.length() > 0) {
            String[] pairs = input.split(" ");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length != 2)
                    throw new WrongInputFormatException(String.format(WrongKvPairFormat, pair));
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }

    public static void printId(int id){
        if(id < 0) System.out.println(CreationFailure);
        else System.out.printf(CreationSuccess, id);
    }

    public static void printPersons(List<Person> persons){
        for(Person person: persons){
            printPerson(person);
        }
    }

    public static void printOperationResult(int result, String operationName){
        String message;
        switch (result){
            case 1:
                message = String.format(OperationSuccess, operationName);
                break;
            case 0:
                message = OperationNoSuchId;
                break;
            default:
                message = String.format(OperationFailure, operationName);
        }
        System.out.println(message);
    }

    private static void printPerson(Person person){
        System.out.println("Person{" +
                "id=" + person.getId() +
                ", firstName='" + person.getFirstName() + '\'' +
                ", lastName='" + person.getLastName() + '\'' +
                ", age=" + person.getAge() +
                ", height=" + person.getHeight() +
                ", gender=" + isMaleToGender(person.isMale()) +
                '}');
    }

    private static String isMaleToGender(Boolean isMale){
        return isMale ? "Male" : "Female";
    }

    public static String removeWhitespaces(String input){
        return input.trim().replaceAll("\\s+", " ");
    }

    public static String removeSpacesAroundEqualsSigns(String input){
        return input.replaceAll(" *= *", "=");
    }
}
