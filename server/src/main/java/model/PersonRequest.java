package model;

public class PersonRequest {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private int height;
    private boolean isMale;

    private boolean isFirstNameSet;
    private boolean isLastNameSet;
    private boolean isAgeSet;
    private boolean isHeightSet;
    private boolean isMaleSet;

    public PersonRequest(){}

    public PersonRequest(int id, String firstName, String lastName, int age, int height, boolean isMale, boolean isFirstNameSet, boolean isLastNameSet, boolean isAgeSet, boolean isHeightSet, boolean isMaleSet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.isMale = isMale;
        this.isFirstNameSet = isFirstNameSet;
        this.isLastNameSet = isLastNameSet;
        this.isAgeSet = isAgeSet;
        this.isHeightSet = isHeightSet;
        this.isMaleSet = isMaleSet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isFirstNameSet() {
        return isFirstNameSet;
    }

    public void setFirstNameSet(boolean firstNameSet) {
        isFirstNameSet = firstNameSet;
    }

    public boolean isLastNameSet() {
        return isLastNameSet;
    }

    public void setLastNameSet(boolean lastNameSet) {
        isLastNameSet = lastNameSet;
    }

    public boolean isAgeSet() {
        return isAgeSet;
    }

    public void setAgeSet(boolean ageSet) {
        isAgeSet = ageSet;
    }

    public boolean isHeightSet() {
        return isHeightSet;
    }

    public void setHeightSet(boolean heightSet) {
        isHeightSet = heightSet;
    }

    public boolean isMaleSet() {
        return isMaleSet;
    }

    public void setMaleSet(boolean maleSet) {
        isMaleSet = maleSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
