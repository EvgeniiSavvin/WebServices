package utils;

import client.PersonRequest;
import exceptions.WrongInputFormatException;

import java.util.Map;

import static utils.StringConstants.*;

public class PersonRequestUtils {

    public static PersonRequest createRequestFromUserInput(String input) throws WrongInputFormatException {
        String params;
        Map<String, String> paramMap;
        params = CliUtils.removeSpacesAroundEqualsSigns(input);
        paramMap = CliUtils.parseParams(params);
        return parseRequest(paramMap);
    }

    public static Boolean idIsSet(PersonRequest request){
        return request.getId() != -1;
    }

    public static Boolean allDataFieldsAreSet(PersonRequest request){
        return request.isFirstNameSet() &&
                request.isLastNameSet() &&
                request.isAgeSet() &&
                request.isHeightSet() &&
                request.isMaleSet();
    }

    public static Boolean atLeastOneDataFieldSet(PersonRequest request){
        return request.isFirstNameSet() ||
                request.isLastNameSet() ||
                request.isAgeSet() ||
                request.isHeightSet() ||
                request.isMaleSet();
    }

    private static PersonRequest parseRequest(Map<String, String> params) throws WrongInputFormatException {
        PersonRequest request = createEmptyRequest();
        String key, value;
        for (Map.Entry<String, String> entry : params.entrySet()){
            key = entry.getKey().toLowerCase();
            value = entry.getValue();
            switch (key){
                case Id:
                    try{
                        request.setId(Integer.parseInt(value));
                    } catch (NumberFormatException e){
                        throw new WrongInputFormatException(String.format(ParShouldBeInteger, Id));
                    }
                    break;
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
                        throw new WrongInputFormatException(String.format(ParShouldBeInteger, Age));
                    }
                    break;
                case Height:
                    try{
                        int height = Integer.parseInt(value);
                        request.setHeight(height);
                        request.setHeightSet(true);
                    } catch (NumberFormatException e){
                        throw new WrongInputFormatException(String.format(ParShouldBeInteger, Height));
                    }
                    break;
                case Gender:
                    switch (value.toLowerCase()){
                        case Male:
                            request.setMale(true);
                            request.setMaleSet(true);
                            break;
                        case Female:
                            request.setMale(false);
                            request.setMaleSet(true);
                            break;
                        default:
                            throw new WrongInputFormatException(WrongGender);
                    }
                    break;
                default:
                    throw new WrongInputFormatException(String.format(UnknownParameter, key));
            }
        }
        return request;
    }

    private static PersonRequest createEmptyRequest(){
        PersonRequest request = new PersonRequest();
        request.setId(-1);
        request.setFirstNameSet(false);
        request.setLastNameSet(false);
        request.setAgeSet(false);
        request.setHeightSet(false);
        request.setMaleSet(false);
        return request;
    }
}
