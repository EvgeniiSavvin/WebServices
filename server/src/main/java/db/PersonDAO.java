package db;

import db.requests.SqlPersonRequest;
import model.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonDAO {

    public List<Person> getPersonsByRequest(SqlPersonRequest request) {
        return runDbOperation( stmt -> {
            List<Person> persons = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(request.getSqlRequest());
            while (rs.next()) {
                Person person = createPersonFromResultSet(rs);
                persons.add(person);
            }
            return persons;
        }, new ArrayList<>());
    }

    public int createPerson(SqlPersonRequest request){
        return runDbOperation(stmt -> {
            stmt.executeUpdate(request.getSqlRequest(), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : -1;
        }, -1);
    }

    public int deletePerson(SqlPersonRequest request){
        return runDbOperationReturningStatus(request);
    }

    public int updatePerson(SqlPersonRequest request){
        return runDbOperationReturningStatus(request);
    }

    private <T> T runDbOperation(DbFunction<T> operation, T fallbackValue){
        T value = fallbackValue;
        try(Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            value = operation.apply(stmt);
        } catch (SQLException ex){
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    private int runDbOperationReturningStatus(SqlPersonRequest request){
        return runDbOperation(stmt -> stmt.executeUpdate(request.getSqlRequest()), -1);
    }

    private Person createPersonFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String last_name = rs.getString("last_name");
        int age = rs.getInt("age");
        int height = rs.getInt("height");
        boolean isMale = rs.getBoolean("is_male");

        return new Person(id, firstName, last_name, age, height, isMale);
    }
}
