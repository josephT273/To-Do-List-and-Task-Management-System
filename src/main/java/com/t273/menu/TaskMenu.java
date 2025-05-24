package com.t273.menu;

import java.util.Scanner;
import com.t273.abstracts.Menu;
import com.t273.todoapp.*;

public class TaskMenu extends Menu {

    @Override
    public void displayMenu(Scanner input) {
        System.out.println("\n---------------------------------------");
        System.out.println("\t\t📋 Tasks Menu");
        System.out.println("---------------------------------------");
        System.out.println("[1] Add Task");
        System.out.println("[2] Update Task");
        System.out.println("[3] View All Tasks");
        System.out.println("[4] Mark All Tasks as Complete");
        System.out.println("[5] Sort Tasks by Deadline");
        System.out.println("[6] Generate Task Reports");
        System.out.println("[7] Back to Main Menu");
        System.out.println("---------------------------------------");
        handleInput(input);
    }

    @Override
    public void choiceOptionProcessing(Scanner input, int choice) {
        switch (choice) {
            case 1:
                new AddTaskHandler().handle(input);
                break;
            case 2:
                new UpdateTaskHandler().handle(input);
                break;
            case 3:
                new ViewTasksHandler().handle();
                break;
            case 4:
                new CompleteAllTasksHandler().handle();
                break;
            case 5:
                new SortTasksHandler().handle(input);
                break;
            case 6:
                new ReportGenerator(App.currentUserId).generate();
                break;
            case 7:
                new MainMenu().displayMenu(input);
                break;
            default:
                System.out.println("❌ Invalid option. Please try again.");
                displayMenu(input);
        }
    }
}
