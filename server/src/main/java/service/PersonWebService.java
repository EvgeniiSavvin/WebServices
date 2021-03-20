package service;

import db.DbFindPersonRequest;
import db.JDBCUtils;
import db.PersonDAO;
import model.FindPersonRequest;
import model.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@WebService(serviceName = "PersonService")
public class PersonWebService {
    private final JDBCUtils utils;

    public PersonWebService(){
        utils = new JDBCUtils();
        utils.init("java:comp/env/jdbc/persons");
    }

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons(FindPersonRequest request) {
        PersonDAO dao = new PersonDAO(getConnection());
        DbFindPersonRequest dbRequest = DbFindPersonRequest.parseRequest(request);
        return dao.getPersonsByRequest(dbRequest);
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = utils.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
