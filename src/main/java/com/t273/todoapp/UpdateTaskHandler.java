package com.t273.todoapp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.t273.database.TaskDAO;

public class UpdateTaskHandler {
    public void handle(Scanner input) {
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
}
