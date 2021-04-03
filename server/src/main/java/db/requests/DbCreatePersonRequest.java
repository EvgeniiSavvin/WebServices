package db.requests;

import model.PersonRequest;

import static db.DbIdentifiers.*;

public class DbCreatePersonRequest extends BaseDbPersonRequest implements SqlPersonRequest{

    public DbCreatePersonRequest(PersonRequest request) {
        super(request);
    }

    @Override
    public String getSqlRequest() {
        if (atLeastOneFieldIsEmpty()) throw new IllegalArgumentException("All fields should be not null");
        return String.format("INSERT INTO %s (%s, %s, %s, %s, %s)\n", TableName, FirstName, LastName, Age, Height, IsMale) +
                String.format("VALUES ('%s', '%s', %d, %d, %s);", firstName, lastName, age, height, isMale.toString());
    }
}

