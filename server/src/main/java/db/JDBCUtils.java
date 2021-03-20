package db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCUtils {

    private static final Logger log = Logger.getLogger(JDBCUtils.class.getName());
    private DataSource dataSource;

    public JDBCUtils() {
    }

    public void init(String dataSourceName) {
        try {
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(dataSourceName);
        } catch (NamingException e) {
            log.log(Level.SEVERE, "JNDIException: " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource is null.");
        }

        return dataSource.getConnection();
    }

}
