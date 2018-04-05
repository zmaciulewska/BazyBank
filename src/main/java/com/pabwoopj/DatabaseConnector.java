package com.pabwoopj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String jdbcDriver="oracle.jdbc.driver.OracleDriver";
    private static final String jdbcUrl="jdbc:oracle:thin:@localhost:1521:xe";
    private static final String jdbcUser="HR";
    private static final String jdbcPass="dupa1";

    public DatabaseConnector() {}
    public static Connection getConnection() {

        try {
            Class.forName(jdbcDriver);
            System.out.println("Driver ok.");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            return null;
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
            System.out.println("connection ok.");
       } catch (SQLException e) {
            System.out.println("Connection failed.");
            return null;
        }

        if (connection != null) {
            return connection;

        } else {
            return null;
        }
    }
}
