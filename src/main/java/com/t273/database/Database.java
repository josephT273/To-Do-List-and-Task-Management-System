package com.t273.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/todoapp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private static Connection conn = null;

    static {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }

    public Database() {}

    public static Connection connection() {
        return conn;
    }
}
