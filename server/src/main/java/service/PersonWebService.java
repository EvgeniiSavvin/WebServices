package service;

import db.requests.DbFindPersonRequest;
import db.PersonDAO;
import model.PersonRequest;
import model.Person;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons(PersonRequest request) {
        PersonDAO dao = new PersonDAO();
        DbFindPersonRequest dbRequest = new DbFindPersonRequest(request);
        return dao.getPersonsByRequest(dbRequest);
    }
}
