
package model;

public class FindPersonRequest {

    protected int age;
    protected boolean ageSet;
    protected String firstName;
    protected boolean firstNameSet;
    protected int height;
    protected boolean heightSet;
    protected String lastName;
    protected boolean lastNameSet;
    protected boolean male;
    protected boolean maleSet;

    public int getAge() {
        return age;
    }

    public void setAge(int value) {
        this.age = value;
    }

    public boolean isAgeSet() {
        return ageSet;
    }

    public void setAgeSet(boolean value) {
        this.ageSet = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public boolean isFirstNameSet() {
        return firstNameSet;
    }

    public void setFirstNameSet(boolean value) {
        this.firstNameSet = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int value) {
        this.height = value;
    }

    public boolean isHeightSet() {
        return heightSet;
    }

    public void setHeightSet(boolean value) {
        this.heightSet = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public boolean isLastNameSet() {
        return lastNameSet;
    }

    public void setLastNameSet(boolean value) {
        this.lastNameSet = value;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean value) {
        this.male = value;
    }

    public boolean isMaleSet() {
        return maleSet;
    }

    public void setMaleSet(boolean value) {
        this.maleSet = value;
    }

}
