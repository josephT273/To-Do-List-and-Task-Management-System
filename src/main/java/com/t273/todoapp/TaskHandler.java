package com.t273.todoapp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.t273.database.TaskDAO;
import com.t273.modal.Task;

public class TaskHandler {
    public void addHandler(Scanner input){
        System.out.print("Title: ");
        String title = input.nextLine();
        System.out.print("Description: ");
        String desc = input.nextLine();
        System.out.print("Priority (1-5): ");
        int priority = Integer.parseInt(input.nextLine());
        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(input.nextLine());

        Task task = new Task(title, desc, priority, deadline, "pending");
        task.userId = App.currentUserId;

        try {
            TaskDAO.addTask(task);
            System.out.println("✅ Task added successfully.");
        } catch (Exception e) {
            System.out.println("❌ Error adding task: " + e.getMessage());
        }
    }

    public void completeHandler(){
        try {
            TaskDAO.markAllTasksComplete(App.currentUserId);
            System.out.println("✅ All tasks marked as completed.");
        } catch (Exception e) {
            System.err.println("❌ Failed to mark tasks: " + e.getMessage());
        }
    }

    public void updateHandle(Scanner input) {
        try {
            System.out.print("🔢 Enter Task ID to update: ");
            int taskId = Integer.parseInt(input.nextLine().trim());

            System.out.print("📊 New Priority (1-5): ");
            int priority = Integer.parseInt(input.nextLine().trim());
            if (priority < 1 || priority > 5) {
                System.err.println("❌ Priority must be between 1 and 5.");
                return;
            }

            System.out.print("📅 New Deadline (YYYY-MM-DD): ");
            String deadlineInput = input.nextLine().trim();
            LocalDate deadline = LocalDate.parse(deadlineInput);

            TaskDAO.updateTask(taskId, priority, deadline);
            System.out.println("✅ Task updated successfully.");
        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid number input: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("❌ Invalid date format. Use YYYY-MM-DD.");
        } catch (Exception e) {
            System.err.println("❌ Failed to update task: " + e.getMessage());
        }
    }

    public void viewHandle() {
        try {
            List<Task> tasks = TaskDAO.getAllTasks(App.currentUserId); // Static user ID
            System.out.println("\n--- Task List ---");
            for (Task t : tasks) {
                System.out.println("ID: " + t.id + ", Title:  " + t.title + ", Priority: " + t.priority + ", Deadline: " + t.deadline + ", Status: " + t.status);
            }
        } catch (Exception e) {
            System.err.println("❌ Error fetching tasks: " + e.getMessage());
        }
    }
    public void sortHandle(Scanner input) {
        try {
            List<Task> sorted = TaskDAO.getTasksSortedByDeadline(App.currentUserId); // Static user ID
            System.out.println("\n--- Sorted Tasks by Deadline ---");
            for (Task t : sorted) {
                System.out.println("ID: " + t.id + ", Title: " + t.title + ", Deadline: " + t.deadline);
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to sort tasks: " + e.getMessage());
        }
    }
}
