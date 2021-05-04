package resources;

import db.PersonDAO;
import db.requests.DbDeletePersonRequest;
import db.requests.DbUpdatePersonRequest;

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
    ){
        return String.valueOf(dao.updatePerson(new DbUpdatePersonRequest(id, firstname, lastname, age, height, isMale)));
    }

    @DELETE
    public String deletePerson(@PathParam("id") Integer id){
        if (id == null) return "-1";
        return String.valueOf(dao.deletePerson(new DbDeletePersonRequest(id)));
    }
}
