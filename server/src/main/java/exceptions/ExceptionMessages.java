package exceptions;

public class ExceptionMessages {
    public static final String IdNotFound = "Existing Person Id is required for this operation. Try to use Find command to get list of existing persons";
    public static final String IllegalName = "Name should not be empty or null";
    public static final String AllFieldsRequired = "All fields should be set for Create operation. Fields list: Firstname, Lastname, Age, Height, Gender";
}
