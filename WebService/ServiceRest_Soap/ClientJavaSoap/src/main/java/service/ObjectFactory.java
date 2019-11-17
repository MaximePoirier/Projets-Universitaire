
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
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

    private final static QName _SearchTelephoneResponse_QNAME = new QName("http://service/", "searchTelephoneResponse");
    private final static QName _AddPersonneList_QNAME = new QName("http://service/", "addPersonneList");
    private final static QName _GetAllNomResponse_QNAME = new QName("http://service/", "getAllNomResponse");
    private final static QName _SearchTelephone_QNAME = new QName("http://service/", "searchTelephone");
    private final static QName _AddPersonneListResponse_QNAME = new QName("http://service/", "addPersonneListResponse");
    private final static QName _GetAllNom_QNAME = new QName("http://service/", "getAllNom");
    private final static QName _AddPersonne_QNAME = new QName("http://service/", "addPersonne");
    private final static QName _AddPersonneResponse_QNAME = new QName("http://service/", "addPersonneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllNom }
     * 
     */
    public GetAllNom createGetAllNom() {
        return new GetAllNom();
    }

    /**
     * Create an instance of {@link AddPersonneListResponse }
     * 
     */
    public AddPersonneListResponse createAddPersonneListResponse() {
        return new AddPersonneListResponse();
    }

    /**
     * Create an instance of {@link AddPersonneList }
     * 
     */
    public AddPersonneList createAddPersonneList() {
        return new AddPersonneList();
    }

    /**
     * Create an instance of {@link GetAllNomResponse }
     * 
     */
    public GetAllNomResponse createGetAllNomResponse() {
        return new GetAllNomResponse();
    }

    /**
     * Create an instance of {@link SearchTelephone }
     * 
     */
    public SearchTelephone createSearchTelephone() {
        return new SearchTelephone();
    }

    /**
     * Create an instance of {@link SearchTelephoneResponse }
     * 
     */
    public SearchTelephoneResponse createSearchTelephoneResponse() {
        return new SearchTelephoneResponse();
    }

    /**
     * Create an instance of {@link AddPersonneResponse }
     * 
     */
    public AddPersonneResponse createAddPersonneResponse() {
        return new AddPersonneResponse();
    }

    /**
     * Create an instance of {@link AddPersonne }
     * 
     */
    public AddPersonne createAddPersonne() {
        return new AddPersonne();
    }

    /**
     * Create an instance of {@link Personne }
     * 
     */
    public Personne createPersonne() {
        return new Personne();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTelephoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "searchTelephoneResponse")
    public JAXBElement<SearchTelephoneResponse> createSearchTelephoneResponse(SearchTelephoneResponse value) {
        return new JAXBElement<SearchTelephoneResponse>(_SearchTelephoneResponse_QNAME, SearchTelephoneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonneList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addPersonneList")
    public JAXBElement<AddPersonneList> createAddPersonneList(AddPersonneList value) {
        return new JAXBElement<AddPersonneList>(_AddPersonneList_QNAME, AddPersonneList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllNomResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllNomResponse")
    public JAXBElement<GetAllNomResponse> createGetAllNomResponse(GetAllNomResponse value) {
        return new JAXBElement<GetAllNomResponse>(_GetAllNomResponse_QNAME, GetAllNomResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTelephone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "searchTelephone")
    public JAXBElement<SearchTelephone> createSearchTelephone(SearchTelephone value) {
        return new JAXBElement<SearchTelephone>(_SearchTelephone_QNAME, SearchTelephone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonneListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addPersonneListResponse")
    public JAXBElement<AddPersonneListResponse> createAddPersonneListResponse(AddPersonneListResponse value) {
        return new JAXBElement<AddPersonneListResponse>(_AddPersonneListResponse_QNAME, AddPersonneListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllNom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllNom")
    public JAXBElement<GetAllNom> createGetAllNom(GetAllNom value) {
        return new JAXBElement<GetAllNom>(_GetAllNom_QNAME, GetAllNom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonne }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addPersonne")
    public JAXBElement<AddPersonne> createAddPersonne(AddPersonne value) {
        return new JAXBElement<AddPersonne>(_AddPersonne_QNAME, AddPersonne.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addPersonneResponse")
    public JAXBElement<AddPersonneResponse> createAddPersonneResponse(AddPersonneResponse value) {
        return new JAXBElement<AddPersonneResponse>(_AddPersonneResponse_QNAME, AddPersonneResponse.class, null, value);
    }

}
