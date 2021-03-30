package db.requests;

import model.PersonRequest;

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
}
