package com.t273.menu;

import java.util.Scanner;

import com.t273.abstracts.Menu;

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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                new MainMenu().displayMenu(input);
                break;
            default:
                break;
        }
    }
    
}
