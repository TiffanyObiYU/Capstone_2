package org.example.views;

import org.example.App;
import org.example.models.Chips;
import org.example.models.Order;
import org.example.utils.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;

public class OrderView {

    //create a new Order
    private static Order newOrder = new Order();
    private static final Scanner scanner = new Scanner(System.in);
    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    // make the menu a static method
    public static void showOrderScreen(){

        boolean isOrdering = true;

        System.out.println("... Beginning a new order ...");
        System.out.println("-----------------------------");
        System.out.println("What is the name for the order?");

        String name = scanner.nextLine();
        newOrder.setNameOnOrder(name);

        while (isOrdering){


        System.out.println("=======Order Screen=======");
        System.out.println("---------------------------");
        System.out.println("""
                S: Add Sandwich
                D: Add Drink
                C: Add Chips
                O: Checkout
                X: Cancel Order
                """);

        String selection = scanner.nextLine().trim();

        switch (selection.toUpperCase()){
            case "S" ->{
                newOrder.addItemToOrder(AddSandwichView.addSandwichToOrder());
            }
            case "D" ->{

                newOrder.addItemToOrder(AddDrinkView.addDrinkToOrder());
            }
            case "C" ->{
                //this method was small so we did it right in the Order view
                addChipToOrder();

            }
            case "O" -> {
                checkout();
                isOrdering = false;

            }
            case "X" ->{
                System.out.println("Order Has Been Cancelled!");
                System.out.println("Returning to Home...");
                isOrdering = false;

            }

            }
        } //end of while loop
    }


    //add Chips to Order can be done here because it's such a small piece of logic
    static void addChipToOrder(){

        System.out.println("Please select chips to add:");

        for(int index = 0; index < Chips.chipsarr.length; index++){
            System.out.println(index+1 + ") " + Chips.chipsarr[index]);
        }


        String selection = Chips.chipsarr[scanner.nextInt()-1];
        scanner.nextLine();

        //This is making the assumption that the user will never make a mistake
        //TODO: Fix this logic that we check for mistakes

        //create Chip Item  from the section
        Chips chips = new Chips(selection);

        //add the selection to the order
        newOrder.addItemToOrder(chips);

        System.out.println(chips.getSelection() + " was added to order.");
        System.out.println("Returning to order menu....");

    }

    //checkout menu
    static void checkout(){

        System.out.println("---------------------------------------");
        System.out.println("             Order Summary             ");
        System.out.println("---------------------------------------");

        // first thing we want to do is display order "receipt"
        for (Item item: newOrder.getOrderItems()){
            System.out.println(item);
        }

        double subtotal = newOrder.calculateOrderTotal();

        System.out.println("===========================");
        System.out.println("Order Total: " + currencyFormatter.format(subtotal));
        System.out.println("----------------------------");
        System.out.println(" ");

        boolean isConfirming = true;

        while (isConfirming) {

            System.out.println("Confirm Order");
            System.out.println("Y - Yes, N - No");

            String selection = scanner.nextLine().trim();

            switch (selection.toUpperCase()) {
                case "Y" -> {

                    if(confirmOrder()){
                        System.out.println("Your order is confirmed and should be ready soon");
                        System.out.println("Thank you!");
                        //done with menu after confirming
                        isConfirming = false;
                    }else {
                        System.out.println("ERROR: Systems failure - Please try again later.");
                    }
                }

                case "N" -> {
                    System.out.println("Canceling Order...");
                    System.out.println("Returning to Home Screen....");
                    System.out.println("...................................");
                    System.out.println(".........................");
                    System.out.println(".............");
                    newOrder = null;
                    isConfirming = false;

                }
                default -> System.out.println("Please try again");
            }
        }
    }

    private static boolean confirmOrder(){
        //generate a receipt and add it to txt file
        /*
        * Each order should have its own receipt file, and it should be
            named by the date and time that the order was placed
        * (yyyyMMdd-hhmmss.txt - i.e.20230329-121523.txt)
        * */
        //create the file ame for this receipt. (yyyyMMdd-hhmmss.txt - i.e.20230329-121523.txt)
        LocalDateTime currentDate = LocalDateTime.now();
        StringBuilder fileName = new StringBuilder();

        for (String dateString: currentDate.toLocalDate().toString().split("-")){
        fileName.append(dateString);
        }

        fileName.append("-");

        for (String timeString: currentDate.toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString().split(":")){
            fileName.append(timeString);
        }
        fileName.append(".txt");

       // System.out.println(fileName);

       // System.out.println(newOrder);

        if(saveReceiptToFolder(fileName.toString(),newOrder)){
            System.out.println("Yum!! Your meal is in the works!");
        }else{
            System.out.println("Nope... SUM-TING-WONG");
        }
        return true;
    }
    //helper method
    //save receipt to file and folder

    private static boolean saveReceiptToFolder(String fileName, Order newOrder){
        //if the folder "receipts" doesn't exist, we need to create it
        String dirPath = "receipts/";

        File receiptFile = new File(dirPath,fileName);

        try (FileWriter fw = new FileWriter(receiptFile); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(newOrder.toString());
                    return true;

        } catch (IOException ioException){
            System.out.println(ioException.getLocalizedMessage());
        }

    return false;
    }

}
