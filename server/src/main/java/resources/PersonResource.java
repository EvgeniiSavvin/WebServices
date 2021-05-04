package resources;

import db.requests.DbCreatePersonRequest;
import db.requests.DbFindPersonRequest;
import db.PersonDAO;
import model.Person;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {
    private final PersonDAO dao;

    public PersonResource(){
        dao = new PersonDAO();
    }

    @GET
    public List<Person> getPersons(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("age") Integer age,
            @QueryParam("height") Integer height,
            @QueryParam("isMale") Boolean isMale
    ) {
        return dao.getPersonsByRequest(new DbFindPersonRequest(null, firstname, lastname, age, height, isMale));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createPerson(
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("age") Integer age,
            @FormParam("height") Integer height,
            @FormParam("isMale") Boolean isMale
    ){
        return String.valueOf(dao.createPerson(new DbCreatePersonRequest(null, firstname, lastname, age, height, isMale)));
    }
}
