package com.t273.menu;

import java.util.List;
import java.util.Scanner;

import com.t273.abstracts.Menu;
import com.t273.database.TaskDAO;
import com.t273.modal.Task;
import com.t273.todoapp.App;

public class TodoList extends Menu{

    @Override
    public void displayMenu(Scanner input) {
        System.out.println("---------------------------------------");
        System.out.println("\tTo-do List\t");
        System.out.println("---------------------------------------");
        System.out.println("[1] View todo list");
        System.out.println("[2] View completed todo list");
        System.out.println("[3] View all todo list");
        System.out.println("[4] Back to main menu");
        System.out.println("---------------------------------------");
        handleInput(input);
    }

    @Override
    public void choiceOptionProcessing(Scanner input, int choice) {
        switch (choice) {
            case 1:
                displayTasksByStatus("pending");
                break;
            case 2:
                displayTasksByStatus("completed");
                break;
            case 3:
                displayAllTasks();
                break;
            case 4:
                new MainMenu().displayMenu(input);
            default:
                break;
        }
        displayMenu(input);
    }
    
    private void displayTasksByStatus(String status){
        List<Task> tasks = TaskDAO.getTasksByStatus(App.currentUserId, status);
        System.out.println("\n--- " + status.toUpperCase() + " Tasks ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                printTask(task);
            }
        }
    }
    private void displayAllTasks() {
        List<Task> tasks = TaskDAO.getAllTasks(App.currentUserId);
        System.out.println("\n--- ALL TASKS ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                printTask(task);
            }
        }
    }

    private void printTask(Task task) {
        System.out.println("🆔 ID: " + task.id);
        System.out.println("📌 Title: " + task.title);
        System.out.println("📝 Description: " + task.description);
        System.out.println("⏳ Priority: " + task.priority);
        System.out.println("📅 Deadline: " + task.deadline);
        System.out.println("✅ Status: " + task.status);
        System.out.println("---------------------------------------");
    }
}
