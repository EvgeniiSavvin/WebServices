package faults;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "exceptions.PersonServiceFault")
public class PersonServiceException extends Exception {

    private final PersonServiceFault fault;

    public PersonServiceException(String message, PersonServiceFault fault){
        super(message);
        this.fault = fault;
    }

    public PersonServiceException(String message, PersonServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public PersonServiceFault getFault(){
        return fault;
    }
}
