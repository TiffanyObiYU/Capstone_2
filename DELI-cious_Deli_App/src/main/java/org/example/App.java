package org.example;

import org.example.views.OrderView;

import java.util.Scanner;

public class App {

    static Scanner scanner;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        //Let's assume the App Class is also our "Home Screen!!!
        //This will be the start of the spp
        boolean isRunning = true;

        // while the app is running we want to continue to show the Home Screen
        while (isRunning){

            System.out.println(" ------------------------------- ");
            System.out.println(" ");
            System.out.println(" +  Welcome to DELIcious Deli  + ");
            System.out.println(" ");
            System.out.println(" -------------------------------- ");
            System.out.println("S: Start New Order");
            System.out.println("E: Exit Application");

            String selection = scanner.nextLine().trim();

            switch (selection.toUpperCase()){
                case "S" -> {
                    // change to order view
                    OrderView.showOrderScreen();
                }
                case "E" -> {
                    System.out.println("Thank you for stopping by!");
                    System.out.println("Have a DELI-cious day!");
                    System.out.println("-----------------------------");
                    System.out.println(" ");
                    isRunning = false;
                }
                default -> System.out.println("PLease try again...");
            }//end of switch statement

        } //end of while loop

    } //end of main method and essentially the end of the application





}