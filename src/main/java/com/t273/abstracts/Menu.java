package com.t273.abstracts;

import java.util.Scanner;

public abstract class Menu {
    public abstract void displayMenu(Scanner input);


    public void handleInput(Scanner input){
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(input.nextLine());
        choiceOptionProcessing(input, choice);
    }

    public abstract void choiceOptionProcessing(Scanner input, int choice);
}
