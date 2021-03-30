package db.requests;

import model.PersonRequest;

public class DbFindPersonRequest extends BaseDbPersonRequest implements SqlPersonRequest {

    public DbFindPersonRequest(PersonRequest request) {
        super(request);
    }

    @Override
    public String getSqlRequest() {
        DbFindPersonRequest request = copy();
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM persons");
        if(!isEmpty()) query.append(" WHERE ");

        if(request.firstName != null) {
            query.append(request.getFirstNameAsDbQuery());
            request.firstName = null;
            appendAndIfRequestIsNotEmpty(request, query);
        }

        if(request.lastName != null) {
            query.append(request.getLastNameAsDbQuery());
            request.lastName = null;
            appendAndIfRequestIsNotEmpty(request, query);
        }

        if(request.age != null) {
            query.append(request.getAgeAsDbQuery());
            request.age = null;
            appendAndIfRequestIsNotEmpty(request, query);
        }

        if(request.height != null) {
            query.append(request.getHeightAsDbQuery());
            request.height = null;
            appendAndIfRequestIsNotEmpty(request, query);
        }

        if(request.isMale != null) {
            query.append(request.getMaleAsDbQuery());
            request.isMale = null;
            appendAndIfRequestIsNotEmpty(request, query);
        }

        return query.toString();
    }

    private void appendAndIfRequestIsNotEmpty(DbFindPersonRequest request, StringBuilder query){
        if(!request.isEmpty()) query.append(" AND ");
    }

    private DbFindPersonRequest(Integer id, String firstName, String lastName, Integer age, Integer height, Boolean isMale) {
        super(id, firstName, lastName, age, height, isMale);
    }

    private DbFindPersonRequest copy(){
        return new DbFindPersonRequest(id, firstName, lastName, age, height, isMale);
    }

    private Boolean isEmpty(){
        return firstName == null &&
                lastName == null &&
                age == null &&
                height == null &&
                isMale == null;
    }

    private String getFirstNameAsDbQuery(){
        if(firstName == null) return "";
        else return String.format("LOWER(first_name) = LOWER('%s')", firstName);
    }

    private String getLastNameAsDbQuery(){
        if(lastName == null) return "";
        else return String.format("LOWER(last_name) = LOWER('%s')", lastName);
    }

    private String getAgeAsDbQuery(){
        if(age == null) return "";
        else return String.format("age = %d", age);
    }

    private String getHeightAsDbQuery(){
        if(height == null) return "";
        else return String.format("height = %d", height);
    }

    private String getMaleAsDbQuery(){
        if(isMale == null) return "";
        else return String.format("is_male = %s", isMale.toString());
    }
}
