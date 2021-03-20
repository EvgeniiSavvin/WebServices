package utils;

import client.Person;
import exceptions.WrongInputFormatException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CliUtils {

    public static void printPrompt(){System.out.print(StringConstants.prompt);}

    public static void printUsage(){System.out.println(StringConstants.usage);}

    public static Map<String, String> parseParams(String input) throws WrongInputFormatException {
        Map<String, String> params = new HashMap<>();
        if(input.length() > 0) {
            String[] pairs = input.split(" ");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length != 2)
                    throw new WrongInputFormatException(String.format(StringConstants.wrongKvPairFormat, pair));
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }

    public static void printPersons(List<Person> persons){
        for(Person person: persons){
            printPerson(person);
        }
    }

    private static void printPerson(Person person){
        System.out.println("Person{" +
                "firstName='" + person.getFirstName() + '\'' +
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
