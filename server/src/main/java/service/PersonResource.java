package service;

import db.DbFindPersonRequest;
import db.JDBCUtils;
import db.PersonDAO;
import model.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    private final JDBCUtils utils;

    public PersonResource(){
        utils = new JDBCUtils();
        utils.init("java:comp/env/jdbc/persons");
    }

    @GET
    public List<Person> getPersons(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("age") Integer age,
            @QueryParam("height") Integer height,
            @QueryParam("isMale") Boolean isMale
    ) {
        PersonDAO dao = new PersonDAO(getConnection());
        DbFindPersonRequest dbRequest = new DbFindPersonRequest(firstname, lastname, age, height, isMale);
        return dao.getPersonsByRequest(dbRequest);
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = utils.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
