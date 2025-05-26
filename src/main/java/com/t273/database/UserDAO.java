package com.t273.database;

import com.t273.modal.User;
import com.t273.todoapp.App;
import com.t273.utils.HashUtil;

import java.sql.*;

public class UserDAO {

    public static boolean registerUser(User user){
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        
        try(Connection conn = Database.connection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, user.name);
            statement.setString(2, HashUtil.hashPassword(user.password));

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
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
                        rs.getString("password")
                );
            }
        }catch(Exception e){
            System.err.println("Error: " + e);
        }
        return null;
    }

    public static boolean loginUser(User user){
        String query = "SELECT * FROM users WHERE username=?";
        try(Connection conn = Database.connection();
            PreparedStatement statement = conn.prepareStatement(query)){
            
            statement.setString(1, user.name);

            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (HashUtil.checkPassword(user.password, hashedPassword)) {
                        App.currentUserId = rs.getInt("id");
                        System.out.println(App.currentUserId);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static boolean updateProfile(int userId, String name){
        String sql = "UPDATE users SET name = ? WHERE id = ?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(3, userId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }catch(Exception e){
            System.err.println("Error: " + e);
            return false;
        }
    }

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
