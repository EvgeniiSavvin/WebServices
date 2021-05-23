package faults;

public class PersonServiceFault {
    private static final String DEFAULT_MESSAGE = "Internal server error occurred";
    private static final String IdNotFoundMessage = "Existing Person Id is required for this operation. Try to use Find command to get list of existing persons";
    private static final String IllegalNameMessage = "Name should not be empty or null";
    private static final String AllFieldsRequiredMessage = "All fields should be set for Create operation. Fields list: Firstname, Lastname, Age, Height, Gender";
    private static final String UnauthorisedMessage = "This method requires basic authentication. Please provide valid login and password";

    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static PersonServiceFault defaultInstance() {
        PersonServiceFault fault = new PersonServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }

    public static PersonServiceFault allFieldsRequired() {
        PersonServiceFault fault = new PersonServiceFault();
        fault.message = AllFieldsRequiredMessage;
        return fault;
    }

    public static PersonServiceFault illegalName() {
        PersonServiceFault fault = new PersonServiceFault();
        fault.message = IllegalNameMessage;
        return fault;
    }

    public static PersonServiceFault unknownId() {
        PersonServiceFault fault = new PersonServiceFault();
        fault.message = IdNotFoundMessage;
        return fault;
    }

    public static PersonServiceFault unauthorised() {
        PersonServiceFault fault = new PersonServiceFault();
        fault.message = UnauthorisedMessage;
        return fault;
    }
}
