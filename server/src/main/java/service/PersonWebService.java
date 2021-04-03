package service;

import db.requests.DbCreatePersonRequest;
import db.requests.DbDeletePersonRequest;
import db.requests.DbFindPersonRequest;
import db.PersonDAO;
import db.requests.DbUpdatePersonRequest;
import model.PersonRequest;
import model.Person;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    private final PersonDAO dao;

    public PersonWebService(){
        dao = new PersonDAO();
    }

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons(PersonRequest request) {
        return dao.getPersonsByRequest(new DbFindPersonRequest(request));
    }

    @WebMethod(operationName = "createPerson")
    public int createPerson(PersonRequest request) {
        return dao.createPerson(new DbCreatePersonRequest(request));
    }

    @WebMethod(operationName = "deletePerson")
    public int deletePerson(int id) {
        return dao.deletePerson(new DbDeletePersonRequest(id));
    }

    @WebMethod(operationName = "updatePerson")
    public int updatePerson(PersonRequest request){
        return dao.updatePerson(new DbUpdatePersonRequest(request));
    }
}
