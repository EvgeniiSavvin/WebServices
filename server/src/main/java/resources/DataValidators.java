package resources;

import exceptions.ExceptionMessages;
import exceptions.PersonServiceException;

public class DataValidators {
    public static void ensureNameIsNotEmptyOrThrow(String string) throws PersonServiceException{
        if(string == null || string.trim().isEmpty()) throw new PersonServiceException(ExceptionMessages.IllegalName);
    }

    public static void throwIfIdIsNull(Integer id) throws PersonServiceException{
        if(id == null) throw new PersonServiceException(ExceptionMessages.IdNotFound);
    }
}
