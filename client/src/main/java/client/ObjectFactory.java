
package client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreatePerson_QNAME = new QName("http://service/", "createPerson");
    private final static QName _CreatePersonResponse_QNAME = new QName("http://service/", "createPersonResponse");
    private final static QName _DeletePerson_QNAME = new QName("http://service/", "deletePerson");
    private final static QName _DeletePersonResponse_QNAME = new QName("http://service/", "deletePersonResponse");
    private final static QName _GetPersons_QNAME = new QName("http://service/", "getPersons");
    private final static QName _GetPersonsResponse_QNAME = new QName("http://service/", "getPersonsResponse");
    private final static QName _UpdatePerson_QNAME = new QName("http://service/", "updatePerson");
    private final static QName _UpdatePersonResponse_QNAME = new QName("http://service/", "updatePersonResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreatePerson }
     * 
     */
    public CreatePerson createCreatePerson() {
        return new CreatePerson();
    }

    /**
     * Create an instance of {@link CreatePersonResponse }
     * 
     */
    public CreatePersonResponse createCreatePersonResponse() {
        return new CreatePersonResponse();
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link GetPersons }
     * 
     */
    public GetPersons createGetPersons() {
        return new GetPersons();
    }

    /**
     * Create an instance of {@link GetPersonsResponse }
     * 
     */
    public GetPersonsResponse createGetPersonsResponse() {
        return new GetPersonsResponse();
    }

    /**
     * Create an instance of {@link UpdatePerson }
     * 
     */
    public UpdatePerson createUpdatePerson() {
        return new UpdatePerson();
    }

    /**
     * Create an instance of {@link UpdatePersonResponse }
     * 
     */
    public UpdatePersonResponse createUpdatePersonResponse() {
        return new UpdatePersonResponse();
    }

    /**
     * Create an instance of {@link PersonRequest }
     * 
     */
    public PersonRequest createPersonRequest() {
        return new PersonRequest();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreatePerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "createPerson")
    public JAXBElement<CreatePerson> createCreatePerson(CreatePerson value) {
        return new JAXBElement<CreatePerson>(_CreatePerson_QNAME, CreatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreatePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "createPersonResponse")
    public JAXBElement<CreatePersonResponse> createCreatePersonResponse(CreatePersonResponse value) {
        return new JAXBElement<CreatePersonResponse>(_CreatePersonResponse_QNAME, CreatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersons }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPersons }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "getPersons")
    public JAXBElement<GetPersons> createGetPersons(GetPersons value) {
        return new JAXBElement<GetPersons>(_GetPersons_QNAME, GetPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPersonsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "getPersonsResponse")
    public JAXBElement<GetPersonsResponse> createGetPersonsResponse(GetPersonsResponse value) {
        return new JAXBElement<GetPersonsResponse>(_GetPersonsResponse_QNAME, GetPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "updatePerson")
    public JAXBElement<UpdatePerson> createUpdatePerson(UpdatePerson value) {
        return new JAXBElement<UpdatePerson>(_UpdatePerson_QNAME, UpdatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "updatePersonResponse")
    public JAXBElement<UpdatePersonResponse> createUpdatePersonResponse(UpdatePersonResponse value) {
        return new JAXBElement<UpdatePersonResponse>(_UpdatePersonResponse_QNAME, UpdatePersonResponse.class, null, value);
    }

}
