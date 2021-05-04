package service;

import db.DbFindPersonRequest;
import db.PersonDAO;
import model.Person;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPersons(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("age") Integer age,
            @QueryParam("height") Integer height,
            @QueryParam("isMale") Boolean isMale
    ) {
        PersonDAO dao = new PersonDAO();
        DbFindPersonRequest dbRequest = new DbFindPersonRequest(firstname, lastname, age, height, isMale);
        return dao.getPersonsByRequest(dbRequest);
    }
}
