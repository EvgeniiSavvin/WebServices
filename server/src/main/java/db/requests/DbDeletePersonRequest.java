package db.requests;

import model.PersonRequest;

import static db.DbIdentifiers.*;

public class DbDeletePersonRequest implements SqlPersonRequest{
    private final int id;

    public DbDeletePersonRequest(int id) {
        this.id = id;
    }

    @Override
    public String getSqlRequest() {
        return String.format("DELETE FROM %s WHERE %s = %d", TableName, Id, id);
    }
}
