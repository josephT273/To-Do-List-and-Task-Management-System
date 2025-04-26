package com.t273.database;

import java.sql.*;
public class Database {
    private static String URL = "jdbc:mysql://localhost:3306/todoapp";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;
    
    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, username, password);

        } catch (ClassNotFoundException e) {
            System.err.println("JDBS drive not found: " + e.getMessage());
        }catch(SQLException e){
            System.err.println("SQL ErrorL " + e.getMessage());
        }
    }
    public Database() {}
    public static Connection connection() {
        return conn;
    }
}
