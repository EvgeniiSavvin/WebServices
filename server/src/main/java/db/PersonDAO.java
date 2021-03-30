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
        List<Person> persons = new ArrayList<>();
        try(Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request.getSqlRequest());

            while (rs.next()) {
                Person person = createPersonFromResultSet(rs);
                persons.add(person);
            }

        } catch (SQLException ex){
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
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
