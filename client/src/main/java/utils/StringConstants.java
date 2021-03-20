package utils;

public class StringConstants {
    public static final String FirstName = "firstname";
    public static final String LastName = "lastname";
    public static final String Age = "age";
    public static final String Height = "height";
    public static final String Gender = "gender";

    public static final String Male = "m";
    public static final String Female = "f";

    public static final String Find = "find";
    public static final String Exit = "exit";

    public static final String usage =
            "USAGE:\n" +
                    "Find persons by parameters\n" +
                    Find +
                    "[ firstName=<first name>]" +
                    "[ lastName=<last name>]" +
                    "[ age=<age>]" +
                    "[ height=<height>]" +
                    "[ gender=<M or F>]\n"+
                    "\n" +
                    "Exit program\n" +
                    Exit;

    public static final String unknownParameter = "Parameter %s is unknown";
    public static final String parShouldBeInteger = "%s should be integer";
    public static final String wrongGender = "Gender should be either M or F";
    public static final String wrongKvPairFormat = "%s is not a correct pair. Format should be key=value";
    public static final String unknownCommand = "Unknown command";

    public static final String prompt = "> ";
}
