package backend.jdbc_utils;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {

    private static final String URL =  "jdbc:mysql://localhost:3306/jdbc";

    private static final String USERNAME =
            "root";

    private static final String PASSWORD =
            "Gate@2026";


    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

        } catch (ClassNotFoundException | SQLException e) {
        	System.out.println("Error while connecting to database : " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }
}