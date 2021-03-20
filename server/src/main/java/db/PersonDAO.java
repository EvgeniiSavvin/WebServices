package db;

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
    private final Connection connection;

    public PersonDAO(Connection connection){
        this.connection = connection;
    }

    public List<Person> getPersonsByRequest(DbFindPersonRequest request) {
        List<Person> persons = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(createQuery(request));

            while (rs.next()) {
                Person person = createPersonFromResultSet(rs);
                persons.add(person);
            }

        }catch (SQLException ex){
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }

    private Person createPersonFromResultSet(ResultSet rs) throws SQLException {
        String firstName = rs.getString("first_name");
        String last_name = rs.getString("last_name");
        int age = rs.getInt("age");
        int height = rs.getInt("height");
        boolean isMale = rs.getBoolean("is_male");

        return new Person(firstName, last_name, age, height, isMale);
    }

    private String createQuery(DbFindPersonRequest request) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM persons");
        if(!request.isEmpty()) query.append(" WHERE ");

        if(request.getFirstName() != null) {
            query.append(request.getFirstNameAsDbQuery());
            request.setFirstName(null);
            appendAndIfRequestIsNotEmpty(request, query);
        }

        if(request.getLastName() != null) {
            query.append(request.getLastNameAsDbQuery());
            request.setLastName(null);
            appendAndIfRequestIsNotEmpty(request, query);
        }

        if(request.getAge() != null) {
            query.append(request.getAgeAsDbQuery());
            request.setAge(null);
            appendAndIfRequestIsNotEmpty(request, query);
        }

        if(request.getHeight() != null) {
            query.append(request.getHeightAsDbQuery());
            request.setHeight(null);
            appendAndIfRequestIsNotEmpty(request, query);
        }

        if(request.getMale() != null) {
            query.append(request.getMaleAsDbQuery());
            request.setMale(null);
            appendAndIfRequestIsNotEmpty(request, query);
        }

        System.out.println(query.toString());

        return query.toString();
    }

    private void appendAndIfRequestIsNotEmpty(DbFindPersonRequest request, StringBuilder query){
        if(!request.isEmpty()) query.append(" AND ");
    }
}
