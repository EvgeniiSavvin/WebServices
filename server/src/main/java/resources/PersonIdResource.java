package resources;

import db.PersonDAO;
import db.requests.DbDeletePersonRequest;
import db.requests.DbUpdatePersonRequest;
import exceptions.ExceptionMessages;
import exceptions.PersonServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons/{id}")
@Produces({MediaType.TEXT_PLAIN})
public class PersonIdResource {
    private final PersonDAO dao;

    public PersonIdResource(){
        dao = new PersonDAO();
    }

    @PUT
    public String updatePerson(
            @PathParam("id") Integer id,
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("age") Integer age,
            @FormParam("height") Integer height,
            @FormParam("isMale") Boolean isMale
    ) throws PersonServiceException {
        DataValidators.throwIfIdIsNull(id);
        if(firstname != null) DataValidators.ensureNameIsNotEmptyOrThrow(firstname);
        if(lastname != null) DataValidators.ensureNameIsNotEmptyOrThrow(lastname);
        int result = dao.updatePerson(new DbUpdatePersonRequest(id, firstname, lastname, age, height, isMale));
        if(result == 0) throw new PersonServiceException(ExceptionMessages.IdNotFound);
        return String.valueOf(result);
    }

    @DELETE
    public String deletePerson(@PathParam("id") Integer id) throws PersonServiceException{
        DataValidators.throwIfIdIsNull(id);
        int result = dao.deletePerson(new DbDeletePersonRequest(id));
        if(result == 0) throw new PersonServiceException(ExceptionMessages.IdNotFound);
        return String.valueOf(result);
    }
}
