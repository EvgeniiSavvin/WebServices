package db.requests;

import model.PersonRequest;

import static db.DbIdentifiers.*;

public class DbUpdatePersonRequest extends BaseDbPersonRequest implements SqlPersonRequest{
    public DbUpdatePersonRequest(PersonRequest request) {
        super(request);
    }

    public DbUpdatePersonRequest(Integer id, String firstName, String lastName, Integer age, Integer height, Boolean isMale) {
        super(id, firstName, lastName, age, height, isMale);
    }

    @Override
    public String getSqlRequest() {
        return String.format("UPDATE %s\n", TableName) +
                String.format("SET %s\n", getFieldsAsDelimitedPairs(", ")) +
                String.format("WHERE %s = %d;", Id, id);
    }
}
