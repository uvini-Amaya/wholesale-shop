package lk.ijse.cmjd.db;

/*
    @author DanujaV
    @created 5/28/23 - 11:17 AM   
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }

    private static DbConnection dbConnection;
    private static Connection connection;

    private DbConnection () throws SQLException {
        connection = DriverManager.getConnection(URL, props);
    }

    public static DbConnection getInstance() throws SQLException {
        if(dbConnection == null) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }
    public Connection getConnection() {
        return connection;
    }
}
