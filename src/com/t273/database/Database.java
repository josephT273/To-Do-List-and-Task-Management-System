package com.t273.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static String connection_string = "jdbc:mysql://localhost:3306/todoapp";
    private static String username = "root";
    private static String password = "";

    public void connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(connection_string, username, password);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
