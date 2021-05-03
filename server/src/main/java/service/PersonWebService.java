package service;

import db.requests.DbCreatePersonRequest;
import db.requests.DbDeletePersonRequest;
import db.requests.DbFindPersonRequest;
import db.PersonDAO;
import db.requests.DbUpdatePersonRequest;
import faults.PersonServiceException;
import faults.PersonServiceFault;
import model.PersonRequest;
import model.Person;
import utils.PersonUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@MTOM
@WebService(serviceName = "PersonService")
public class PersonWebService {

    private final PersonDAO dao;

    public PersonWebService(){
        dao = new PersonDAO();
    }

    @WebMethod(operationName = "getCatPhoto")
    public Image getCatPhoto(){
        try{
            return ImageIO.read(getClass().getResource("/images/cat.jpg"));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons(PersonRequest request) {
        return dao.getPersonsByRequest(new DbFindPersonRequest(request));
    }

    @WebMethod(operationName = "createPerson")
    public int createPerson(PersonRequest request) throws PersonServiceException {
        if(!PersonUtils.allFieldsSet(request))
            throw new PersonServiceException("Not all fields are set", PersonServiceFault.allFieldsRequired());
        if(PersonUtils.stringIsNullOrEmpty(request.getFirstName()))
            throw new PersonServiceException("Firstname is empty", PersonServiceFault.illegalName());
        if(PersonUtils.stringIsNullOrEmpty(request.getLastName()))
            throw new PersonServiceException("Lastname is empty", PersonServiceFault.illegalName());
        return dao.createPerson(new DbCreatePersonRequest(request));
    }

    @WebMethod(operationName = "deletePerson")
    public int deletePerson(int id) throws PersonServiceException {
        int result = dao.deletePerson(new DbDeletePersonRequest(id));
        if(result == 0) throw new PersonServiceException("Person with required id was not found", PersonServiceFault.unknownId());
        else return result;
    }

    @WebMethod(operationName = "updatePerson")
    public int updatePerson(PersonRequest request) throws PersonServiceException {
        if(PersonUtils.stringIsNullOrEmpty(request.getFirstName()) && request.isFirstNameSet())
            throw new PersonServiceException("Firstname is empty", PersonServiceFault.illegalName());
        if(PersonUtils.stringIsNullOrEmpty(request.getLastName()) && request.isLastNameSet())
            throw new PersonServiceException("Lastname is empty", PersonServiceFault.illegalName());
        int result = dao.updatePerson(new DbUpdatePersonRequest(request));
        if(result == 0) throw new PersonServiceException("Person with required id was not found", PersonServiceFault.unknownId());
        else return result;
    }
}
