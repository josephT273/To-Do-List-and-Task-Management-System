package com.t273.todoapp;

import com.t273.abstracts.Menu;
import com.t273.database.UserDAO;
import com.t273.menu.MainMenu;
import com.t273.modal.User;
import java.util.Scanner;

public class App extends Menu{
    public static int currentUserId = -1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        do {
            new App().displayMenu(input);
        } while (true);
    }

    @Override
    public void displayMenu(Scanner input) {
        System.out.println("============================================");
        System.out.println("Welcome to To-do and Task management App\t");
        System.out.println("============================================");
        System.out.println("[1] Register");
        System.out.println("[2] Login");
        System.out.println("[3] Exit");
        handleInput(input);
    }
    @Override
    public void choiceOptionProcessing(Scanner input, int choice) {
        switch (choice) {
            case 1:
                registrationScreen(input);
                break;
            case 2:
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
        System.out.println("========================");
        System.out.println("Welcome to LoginScreen");
        System.out.println("========================");
        System.out.print("[Username]: ");
        String username = input.nextLine();
        System.out.print("[Password]: ");
        String password = input.nextLine();
        
        User user = new User(username, password);

        if (UserDAO.loginUser(user)) {
            new MainMenu().displayMenu(input);
        }else{
            System.out.println("Invalid username or password!");
        }
    }

    private static void registrationScreen(Scanner input){
        System.out.println("========================");
        System.out.println("Welcome to LoginScreen");
        System.out.println("========================");
        System.out.print("[Username]: ");
        String username = input.nextLine();
        System.out.print("[Password]: ");
        String password = input.nextLine();

        User user = new User(username, password);
        if(UserDAO.registerUser(user)){
            new MainMenu().displayMenu(input);
        }else{
            System.out.println("Please try again with a different username!");
        }
    }

}
