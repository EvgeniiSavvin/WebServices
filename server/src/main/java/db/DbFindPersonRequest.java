package db;

public class DbFindPersonRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer height;
    private Boolean isMale;

    public Boolean isEmpty(){
        return firstName == null &&
                lastName == null &&
                age == null &&
                height == null &&
                isMale == null;
    }

    public String getFirstNameAsDbQuery(){
        if(firstName == null) return "";
        else return String.format("LOWER(first_name) = LOWER('%s')", firstName);
    }

    public String getLastNameAsDbQuery(){
        if(lastName == null) return "";
        else return String.format("LOWER(last_name) = LOWER('%s')", lastName);
    }

    public String getAgeAsDbQuery(){
        if(age == null) return "";
        else return String.format("age = %d", age);
    }

    public String getHeightAsDbQuery(){
        if(height == null) return "";
        else return String.format("height = %d", height);
    }

    public String getMaleAsDbQuery(){
        if(isMale == null) return "";
        else return String.format("is_male = %s", isMale);
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public DbFindPersonRequest(String firstName, String lastName, Integer age, Integer height, Boolean isMale) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.isMale = isMale;
    }
}
