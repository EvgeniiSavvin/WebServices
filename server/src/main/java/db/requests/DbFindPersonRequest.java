package db.requests;

import model.PersonRequest;

import static db.DbIdentifiers.*;

public class DbFindPersonRequest extends BaseDbPersonRequest implements SqlPersonRequest {

    public DbFindPersonRequest(PersonRequest request) {
        super(request);
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
