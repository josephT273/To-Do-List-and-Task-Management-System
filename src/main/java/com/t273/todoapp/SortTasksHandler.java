
package com.t273.todoapp;

import com.t273.database.TaskDAO;
import com.t273.modal.Task;
import java.util.List;
import java.util.Scanner;

public class SortTasksHandler {
    public void handle(Scanner input) {
        try {
            List<Task> sorted = TaskDAO.getTasksSortedByDeadline(1); // Static user ID
            System.out.println("\n--- Sorted Tasks by Deadline ---");
            for (Task t : sorted) {
                System.out.println("ID: " + t.id + ", Title: " + t.title + ", Deadline: " + t.deadline);
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to sort tasks: " + e.getMessage());
        }
    }
}