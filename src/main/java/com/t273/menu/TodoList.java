package com.t273.menu;

import java.util.Scanner;

import com.t273.abstracts.Menu;

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
                // todo list
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                new MainMenu().displayMenu(input);
            default:
                break;
        }
    }
    
}
