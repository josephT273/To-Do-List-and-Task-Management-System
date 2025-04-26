package com.t273.menu;

import java.util.Scanner;

import com.t273.abstracts.Menu;

public class MainMenu extends Menu{
    public static Scanner input = new Scanner(System.in);

    @Override
    public void displayMenu(Scanner input) {
        System.out.println("--------------------------------");
        System.out.println("\tMain menu\t");
        System.out.println("--------------------------------");
        System.out.println("[1] Tasks");
        System.out.println("[2] Todo List");
        System.out.println("[3] Account");
        System.out.println("[4] Exit");
        System.out.println("--------------------------------");
        handleInput(input);
    }

    @Override
    public void choiceOptionProcessing(Scanner input, int choice) {
        switch (choice) {
            case 1:
                
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
