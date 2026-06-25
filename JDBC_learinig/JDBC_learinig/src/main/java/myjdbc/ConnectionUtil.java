package myjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil  {
    private static  final String url = "jdbc:mysql://localhost:3306/jdbc";
    private  static  final  String username = "root";
    private  static  final  String password = "Gate@2026";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Loading driver error "+ e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

}
