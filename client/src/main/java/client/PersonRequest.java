
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for personRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="personRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ageSet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstNameSet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="heightSet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastNameSet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="male" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="maleSet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personRequest", propOrder = {
    "age",
    "ageSet",
    "firstName",
    "firstNameSet",
    "height",
    "heightSet",
    "id",
    "lastName",
    "lastNameSet",
    "male",
    "maleSet"
})
public class PersonRequest {

    protected int age;
    protected boolean ageSet;
    protected String firstName;
    protected boolean firstNameSet;
    protected int height;
    protected boolean heightSet;
    protected int id;
    protected String lastName;
    protected boolean lastNameSet;
    protected boolean male;
    protected boolean maleSet;

    /**
     * Gets the value of the age property.
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }

    /**
     * Gets the value of the ageSet property.
     * 
     */
    public boolean isAgeSet() {
        return ageSet;
    }

    /**
     * Sets the value of the ageSet property.
     * 
     */
    public void setAgeSet(boolean value) {
        this.ageSet = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the firstNameSet property.
     * 
     */
    public boolean isFirstNameSet() {
        return firstNameSet;
    }

    /**
     * Sets the value of the firstNameSet property.
     * 
     */
    public void setFirstNameSet(boolean value) {
        this.firstNameSet = value;
    }

    /**
     * Gets the value of the height property.
     * 
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     */
    public void setHeight(int value) {
        this.height = value;
    }

    /**
     * Gets the value of the heightSet property.
     * 
     */
    public boolean isHeightSet() {
        return heightSet;
    }

    /**
     * Sets the value of the heightSet property.
     * 
     */
    public void setHeightSet(boolean value) {
        this.heightSet = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the lastNameSet property.
     * 
     */
    public boolean isLastNameSet() {
        return lastNameSet;
    }

    /**
     * Sets the value of the lastNameSet property.
     * 
     */
    public void setLastNameSet(boolean value) {
        this.lastNameSet = value;
    }

    /**
     * Gets the value of the male property.
     * 
     */
    public boolean isMale() {
        return male;
    }

    /**
     * Sets the value of the male property.
     * 
     */
    public void setMale(boolean value) {
        this.male = value;
    }

    /**
     * Gets the value of the maleSet property.
     * 
     */
    public boolean isMaleSet() {
        return maleSet;
    }

    /**
     * Sets the value of the maleSet property.
     * 
     */
    public void setMaleSet(boolean value) {
        this.maleSet = value;
    }

}
