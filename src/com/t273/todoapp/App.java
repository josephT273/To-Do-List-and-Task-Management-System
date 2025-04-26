package com.t273.todoapp;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

import com.t273.abstracts.Menu;

public class App extends Menu{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            new App().displayMenu(input);
        }
    }

    @Override
    public void displayMenu(Scanner input) {
        System.out.println("============================================");
        System.out.println("Welcome to To-do and Task managemen App\t");
        System.out.println("============================================");
        System.out.println("[1] Register");
        System.out.println("[2] Login");
        System.out.println("[3] Exit");
        hundleInput(input);
    }
    @Override
    public void choiceOptionProcessing(Scanner input, int choice) {
        switch (choice) {
            case 1:
                // Regster
                registerationScreen(input);
                break;
            case 2:
                // Login
                loginScreen(input);
                break;
            case 3:
                System.out.println("Exiting...... The app!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice, please enter 1-3 only");
                break;
        }
    }

    private static void loginScreen(Scanner input){
        Console console = System.console();
        String password = null;
        System.out.print("\n\t ========================");
        System.out.println("\n---====<| Welcome to LoginScreen |>====---");
        System.out.println("\t ========================\n");
        System.out.print("[Username]: ");
        String username = input.next();
        char[] passwordArray = console.readPassword("[Password]: ");
        if (passwordArray != null) {
            password = new String(passwordArray);
            Arrays.fill(passwordArray, ' ');
        }

        System.out.println(username + " - " + password);
    }

    private static void registerationScreen(Scanner input){
        Console console = System.console();
        String password = null;
        String confirmPassword = null;

        System.out.print("\n\t ========================");
        System.out.println("\n---====<| Welcome to LoginScreen |>====---");
        System.out.println("\t ========================\n");
        System.out.print("[Username]: ");
        String username = input.next();
        char[] passwordArray = console.readPassword("[Password]: ");
        char[] confirmPasswordArray = console.readPassword("[Confirm Password]: ");
        if (passwordArray != null && confirmPasswordArray != null) {
            password = new String(passwordArray);
            confirmPassword = new String(confirmPasswordArray);

            if (password != confirmPassword) {
                System.out.println("The password is not same");
            }
            Arrays.fill(passwordArray, ' ');
            Arrays.fill(confirmPasswordArray, ' ');
        }

        System.out.println(username + " - " + password);
    }
}
