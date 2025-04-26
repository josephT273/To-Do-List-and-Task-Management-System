package com.t273.menu;

import java.util.Scanner;

import com.t273.abstracts.Menu;

public class TaskMenu extends Menu{

    @Override
    public void displayMenu(Scanner input) {
        System.out.println("---------------------------------------");
        System.out.println("\tTasks Menu\t");
        System.out.println("---------------------------------------");
        System.out.println("[1] Add Task");
        System.out.println("[2] Update Task");
        System.out.println("[3] View All Task");
        System.out.println("[4] Mark All Task as Complete");
        System.out.println("[5] Sort Tasks by Deadline");
        System.out.println("[6] Generate Reports Task");
        System.out.println("[7] Back to main menu");
        System.out.println("---------------------------------------");
        handleInput(input);
    }

    @Override
    public void choiceOptionProcessing(Scanner input, int choice) {
        switch (choice) {
            case 1:
                // todo list
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                new MainMenu().displayMenu(input);
            default:
                break;
        }
    }
    
}
