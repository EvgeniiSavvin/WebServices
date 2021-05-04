package db.requests;

import model.PersonRequest;

import static db.DbIdentifiers.*;

public class DbFindPersonRequest extends BaseDbPersonRequest implements SqlPersonRequest {

    public DbFindPersonRequest(PersonRequest request) {
        super(request);
    }

    public DbFindPersonRequest(Integer id, String firstName, String lastName, Integer age, Integer height, Boolean isMale) {
        super(id, firstName, lastName, age, height, isMale);
    }

    @Override
    public String getSqlRequest() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM " + TableName);
        if(!fieldsAreEmpty()) {
            query.append(" WHERE ");
            query.append(getFieldsAsDelimitedPairs(" AND "));
        }
        return query.toString();
    }
}
