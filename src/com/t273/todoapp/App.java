package com.t273.todoapp;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import com.t273.abstracts.Menu;
import com.t273.database.Database;
import com.t273.menu.MainMenu;

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
        handleInput(input);
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

        if (loginUser(username, password)) {
            new MainMenu().displayMenu(input);
        }else{
            System.out.println("Invalid username or password!");
        }
    }

    private static void registerationScreen(Scanner input){
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

        if(registerUser(username, password)){
            new MainMenu().displayMenu(input);
        }else{
            System.out.println("Please try again with a different username!");
        }
    }

    private static boolean registerUser(String username, String password){
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        Connection conn = Database.connection();
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, username);
            statement.setString(2, password);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loginUser(String username, String password){
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try(Connection conn = Database.connection()){
            PreparedStatement statement = conn.prepareStatement(query);
            
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
