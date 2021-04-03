package db.requests;

import model.PersonRequest;

import static db.DbIdentifiers.*;

public class DbUpdatePersonRequest extends BaseDbPersonRequest implements SqlPersonRequest{
    public DbUpdatePersonRequest(PersonRequest request) {
        super(request);
    }

    @Override
    public String getSqlRequest() {
        return String.format("UPDATE %s\n", TableName) +
                String.format("SET %s\n", getFieldsAsDelimitedPairs(", ")) +
                String.format("WHERE %s = %d;", Id, id);
    }
}
