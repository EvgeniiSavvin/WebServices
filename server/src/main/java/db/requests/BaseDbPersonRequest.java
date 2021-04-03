package db.requests;

import model.PersonRequest;

import java.util.StringJoiner;

import static db.DbIdentifiers.*;

class BaseDbPersonRequest {
    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected Integer age;
    protected Integer height;
    protected Boolean isMale;

    BaseDbPersonRequest(PersonRequest request){
        id = request.getId();
        firstName = request.isFirstNameSet() ? request.getFirstName() : null;
        lastName = request.isLastNameSet() ? request.getLastName() : null;
        age = request.isAgeSet() ? request.getAge() : null;
        height = request.isHeightSet() ? request.getHeight() : null;
        isMale  = request.isMaleSet() ? request.isMale() : null;
    }

    public BaseDbPersonRequest(Integer id, String firstName, String lastName, Integer age, Integer height, Boolean isMale) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.isMale = isMale;
    }

    protected String getFieldsAsDelimitedPairs(String delimiter){
        StringJoiner fields = new StringJoiner(delimiter);
        if(firstName != null)  fields.add(formatKeyValuePair(FirstName, firstName));
        if(lastName != null)  fields.add(formatKeyValuePair(LastName, lastName));
        if(age != null)  fields.add(formatKeyValuePair(Age, age));
        if(height != null)  fields.add(formatKeyValuePair(Height, height));
        if(isMale != null)  fields.add(formatKeyValuePair(IsMale, isMale));
        return fields.toString();
    }

    private String formatKeyValuePair(String key, String value){
        return String.format("%s = '%s'", key, value);
    }

    private String formatKeyValuePair(String key, Integer value){
        return String.format("%s = %d", key, value);
    }

    private String formatKeyValuePair(String key, Boolean value){
        return String.format("%s = %s", key, value.toString());
    }

    protected Boolean fieldsAreEmpty(){
        return firstName == null &&
                lastName == null &&
                age == null &&
                height == null &&
                isMale == null;
    }

    protected Boolean atLeastOneFieldIsEmpty(){
        return firstName == null ||
                lastName == null ||
                age == null ||
                height == null ||
                isMale == null;
    }
}
