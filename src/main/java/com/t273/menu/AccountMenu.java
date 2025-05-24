package com.t273.menu;

import java.util.Scanner;

import com.t273.abstracts.Menu;
import com.t273.database.UserDAO;
import com.t273.modal.User;
import com.t273.todoapp.App;

public class AccountMenu extends Menu {

    @Override
    public void displayMenu(Scanner input) {
        System.out.println("---------------------------------------");
        System.out.println("\tAccount Menu\t");
        System.out.println("---------------------------------------");
        System.out.println("[1] View Profile");
        System.out.println("[2] Update Profile");
        System.out.println("[3] Change password");
        System.out.println("[4] Delete account");
        System.out.println("[5] Back to main menu");
        System.out.println("---------------------------------------");
        handleInput(input);
    }

    @Override
    public void choiceOptionProcessing(Scanner input, int choice) {
        switch (choice) {
            case 1:
                viewProfile();
                break;
            case 2:
                updateProfile(input);
                break;
            case 3:
                changePassword(input);
                break;
            case 4:
                deleteAccount(input);
                return;
            case 5:
                new MainMenu().displayMenu(input);
                return;
            default:
                System.out.println("❌ Invalid option. Try again.");
        }

        displayMenu(input);
    }

    private void viewProfile(){
        User user = UserDAO.getUserById(App.currentUserId);
        if (user == null) {
            System.out.println("❌ Unable to retrieve user profile.");
            return;
        }
        System.out.println("\n--- Your Profile ---");
        System.out.println("👤 Username: " + user.getName());
        System.out.println("📧 Email: " + user.email);
        System.out.println("----------------------");
    }

    private void updateProfile(Scanner input){
        System.out.print("Enter new username: ");
        String username = input.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("❌ Username cannot be empty.");
            return;
        }

        System.out.print("Enter new email: ");
        String email = input.nextLine().trim();
        if (email.isEmpty()) {
            System.out.println("❌ Email cannot be empty.");
            return;
        }

        boolean updated = UserDAO.updateProfile(App.currentUserId, username, email);
        if (updated) {
            System.out.println("✅ Profile updated.");
        } else {
            System.out.println("❌ Failed to update profile.");
        }
    }

    private void changePassword(Scanner input){
        System.out.print("Enter current password: ");
        String current = input.nextLine().trim();
        if (current.isEmpty()) {
            System.out.println("❌ Current password cannot be empty.");
            return;
        }

        System.out.print("Enter new password: ");
        String newPass = input.nextLine().trim();
        if (newPass.isEmpty()) {
            System.out.println("❌ New password cannot be empty.");
            return;
        }

        boolean success = UserDAO.changePassword(App.currentUserId, current, newPass);
        if (success) {
            System.out.println("✅ Password changed successfully.");
        } else {
            System.out.println("❌ Current password incorrect.");
        }
    }

    private void deleteAccount(Scanner input){
        System.out.print("⚠ Are you sure you want to delete your account? (yes/no): ");
        String confirm = input.nextLine().trim().toLowerCase();

        if (confirm.equals("yes")) {
            boolean deleted = UserDAO.deleteUser(App.currentUserId);
            if (deleted) {
                System.out.println("✅ Your account has been deleted.");
                System.exit(0);
            } else {
                System.out.println("❌ Failed to delete account.");
            }
        } else {
            System.out.println("❎ Account deletion cancelled.");
        }
    }

}
