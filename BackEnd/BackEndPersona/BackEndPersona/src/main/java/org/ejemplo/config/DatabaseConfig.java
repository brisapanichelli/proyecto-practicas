package org.ejemplo.config;

import java.sql.*;

public class DatabaseConfig {

    public Connection conectDatabase() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", null);
        // create a Statement
        Statement stmt = conn.createStatement();
        //execute query
        ResultSet rs = stmt.executeQuery("SELECT 'Hello World!'");
        //position result to first
        rs.first();
        System.out.println(rs.getString(1)); //result is "Hello World!"

        return conn;
}


}
