package com.t273.todoapp;

import com.t273.database.TaskDAO;
import com.t273.modal.Task;
import java.util.List;

public class ViewTasksHandler {
    public void handle() {
        try {
            List<Task> tasks = TaskDAO.getAllTasks(1); // Static user ID
            System.out.println("\n--- Task List ---");
            for (Task t : tasks) {
                System.out.println("ID: " + t.id + ", Title: " + t.title + ", Priority: " + t.priority + ", Deadline: " + t.deadline + ", Status: " + t.status);
            }
        } catch (Exception e) {
            System.err.println("❌ Error fetching tasks: " + e.getMessage());
        }
    }
}
