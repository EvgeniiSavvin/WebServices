package service;

import db.DbFindPersonRequest;
import db.PersonDAO;
import model.FindPersonRequest;
import model.Person;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons(FindPersonRequest request) {
        PersonDAO dao = new PersonDAO();
        DbFindPersonRequest dbRequest = DbFindPersonRequest.parseRequest(request);
        return dao.getPersonsByRequest(dbRequest);
    }
}
