package com.t273.todoapp;

import java.util.Scanner;
import com.t273.database.TaskDAO;
import com.t273.modal.Task;
import java.time.LocalDate;

public class AddTaskHandler {
    public void handle(Scanner input) {
        System.out.print("Title: ");
        String title = input.nextLine();
        System.out.print("Description: ");
        String desc = input.nextLine();
        System.out.print("Priority (1-5): ");
        int priority = Integer.parseInt(input.nextLine());
        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(input.nextLine());

        Task task = new Task(title, desc, priority, deadline, "pending");
        task.userId = 1; // Replace with logged-in user ID if login is added

        try {
            TaskDAO.addTask(task);
            System.out.println("✅ Task added successfully.");
        } catch (Exception e) {
            System.out.println("❌ Error adding task: " + e.getMessage());
        }
    }
}
