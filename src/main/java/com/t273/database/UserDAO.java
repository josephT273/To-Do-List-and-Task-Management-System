package com.t273.database;

import com.t273.modal.User;

import java.sql.*;

public class UserDAO {

    public static void registerUser(User user){
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.name);
            stmt.setString(2, user.email);
            stmt.setString(3, user.password);
            stmt.executeUpdate();
        }catch(Exception e){
            System.err.println("Error: " + e);
        }
    }

    public static User getUserByEmailAndPassword(String email, String password){
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        }catch(Exception e){
            System.err.println("Error: " + e);
        }
        return null;
    }

    public static User getUserById(int userId){
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        }catch(Exception e){
            System.err.println("Error: " + e);
        }
        return null;
    }

    // Returns true if update was successful
    public static boolean updateProfile(int userId, String name, String email){
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, userId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }catch(Exception e){
            System.err.println("Error: " + e);
            return false;
        }
    }

    // Returns true if password changed, and checks current password
    public static boolean changePassword(int userId, String currentPassword, String newPassword){
        String sqlCheck = "SELECT password FROM users WHERE id = ?";
        String sqlUpdate = "UPDATE users SET password = ? WHERE id = ?";
        try (Connection conn = Database.connection();
            PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
            stmtCheck.setInt(1, userId);
            ResultSet rs = stmtCheck.executeQuery();
            if (rs.next()) {
                String dbPass = rs.getString("password");
                if (!dbPass.equals(currentPassword)) {
                    return false;
                }
            } else {
                return false;
            }
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                stmtUpdate.setString(1, newPassword);
                stmtUpdate.setInt(2, userId);
                int rows = stmtUpdate.executeUpdate();
                return rows > 0;
            }
        }catch(Exception e){
            System.err.println("Error: " + e);
            return false;
        }
    }

    // Returns true if user deleted
    public static boolean deleteUser(int userId){
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }catch(Exception e){
            System.err.println("Error: " + e);
            return false;
        }
    }
}
