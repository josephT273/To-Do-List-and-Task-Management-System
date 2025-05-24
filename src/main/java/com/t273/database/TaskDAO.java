package com.t273.database;

import com.t273.modal.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public static void addTask(Task task) throws Exception {
        String sql = "INSERT INTO tasks(user_id, title, description, priority, deadline, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.userId);
            stmt.setString(2, task.title);
            stmt.setString(3, task.description);
            stmt.setInt(4, task.priority);
            stmt.setDate(5, Date.valueOf(task.deadline));
            stmt.setString(6, task.status);
            stmt.executeUpdate();
        }
    }

    public static List<Task> getAllTasks(int userId){
        String sql = "SELECT * FROM tasks WHERE user_id = ?";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        rs.getString("status")
                );
                task.id = rs.getInt("id");
                task.userId = userId;
                tasks.add(task);
            }
        }catch(Exception e){
            System.err.println("Error: " + e);
        }

        return tasks;
    }

    public static void updateTask(int taskId, int priority, LocalDate deadline) throws Exception {
        String sql = "UPDATE tasks SET priority = ?, deadline = ? WHERE id = ?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, priority);
            stmt.setDate(2, Date.valueOf(deadline));
            stmt.setInt(3, taskId);
            stmt.executeUpdate();
        }
    }


    public static void deleteTask(int taskId, int userId) throws Exception {
        String sql = "DELETE FROM tasks WHERE id=? AND user_id=?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

    public static void markAllTasksComplete(int userId) throws Exception {
        String sql = "UPDATE tasks SET status='completed' WHERE user_id=?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    public static List<Task> getTasksSortedByDeadline(int userId) throws Exception {
        String sql = "SELECT * FROM tasks WHERE user_id=? ORDER BY deadline ASC";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        rs.getString("status")
                );
                task.id = rs.getInt("id");
                task.userId = userId;
                tasks.add(task);
            }
        }

        return tasks;
    }
    public static int countTasksByStatus(String status, int userId) throws Exception {
        String sql = "SELECT COUNT(*) FROM tasks WHERE status = ? AND user_id = ?";
        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public static List<Task> getTasksByStatus(int userId, String status)  {
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND status = ?";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = Database.connection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getInt("priority"),
                    rs.getDate("deadline").toLocalDate(),
                    rs.getString("status")
                );
                task.id = rs.getInt("id");
                task.userId = userId;
                tasks.add(task);
            }
        }catch(Exception e){
            System.err.println("Error: " + e);
        }

        return tasks;
    }

}
