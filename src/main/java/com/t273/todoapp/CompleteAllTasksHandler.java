package com.t273.todoapp;

import com.t273.database.TaskDAO;

public class CompleteAllTasksHandler {
    public void handle() {
        try {
            TaskDAO.markAllTasksComplete(App.currentUserId);
            System.out.println("✅ All tasks marked as completed.");
        } catch (Exception e) {
            System.err.println("❌ Failed to mark tasks: " + e.getMessage());
        }
    }
}
