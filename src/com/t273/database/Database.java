package com.t273.database;

import java.sql.*;
public class Database {
    private static String URL = "jdbc:postgresql://localhost:5432/todoapp";
    private static String username = "postgres";
    private static String password = "root";
    private static Connection conn;
    
    static {
        try {

            Class.forName("org.postgresql.Driver");
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
