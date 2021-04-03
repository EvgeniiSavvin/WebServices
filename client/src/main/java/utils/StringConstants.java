package utils;

public class StringConstants {
    public static final String Id = "id";
    public static final String FirstName = "firstname";
    public static final String LastName = "lastname";
    public static final String Age = "age";
    public static final String Height = "height";
    public static final String Gender = "gender";

    public static final String Male = "m";
    public static final String Female = "f";

    public static final String Find = "find";
    public static final String Create = "create";
    public static final String Delete = "delete";
    public static final String Update = "update";
    public static final String Exit = "exit";

    public static final String Usage =
            "USAGE:\n" +
                    "Find persons by parameters\n" +
                    Find +
                    "[ firstName=<first name>]" +
                    "[ lastName=<last name>]" +
                    "[ age=<age>]" +
                    "[ height=<height>]" +
                    "[ gender=<M or F>]\n"+
                    "\n" +
                    "Create person with given fields\n" +
                    Create +
                    " firstName=<first name>" +
                    " lastName=<last name>" +
                    " age=<age>" +
                    " height=<height>" +
                    " gender=<M or F>\n"+
                    "\n" +
                    "Update person with given id setting given values\n" +
                    Update +
                    " id = <id>" +
                    "[ firstName=<first name>]" +
                    "[ lastName=<last name>]" +
                    "[ age=<age>]" +
                    "[ height=<height>]" +
                    "[ gender=<M or F>]\n"+
                    "\n" +
                    "Delete person with given id\n" +
                    Delete +
                    " <id>\n" +
                    "\n" +
                    "Exit program\n" +
                    Exit;

    public static final String UnknownParameter = "Parameter %s is unknown";
    public static final String ParShouldBeInteger = "%s should be integer";
    public static final String WrongGender = "Gender should be either M or F";
    public static final String WrongKvPairFormat = "%s is not a correct pair. Format should be key=value";
    public static final String UnknownCommand = "Unknown command";
    public static final String ParametersRequired = "This operation requires parameters";
    public static final String AllDataFieldsRequired = "This operation requires all fields except id to be set";
    public static final String IdAndAtLeastOneDataFieldRequired = "This operation requires id and at least one data field to be set";

    public static final String CreationFailure = "Failed to create new person";
    public static final String CreationSuccess = "New person created. Its id is %d\n";

    public static final String OperationSuccess =  "Operation succeeded: %s";
    public static final String OperationFailure =  "Operation failed: %s";
    public static final String OperationNoSuchId =  "Provided id doesn't exist";

    public static final String Prompt = "> ";
}
