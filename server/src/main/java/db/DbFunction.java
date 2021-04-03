package db;

import java.sql.SQLException;
import java.sql.Statement;

@FunctionalInterface
interface DbFunction<T> {
    T apply(Statement stmt) throws SQLException;
}
