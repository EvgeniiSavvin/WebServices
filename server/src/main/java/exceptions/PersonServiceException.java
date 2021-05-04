package exceptions;

public class PersonServiceException extends Exception {
    public PersonServiceException(String message) {
        super(message);
    }
}
