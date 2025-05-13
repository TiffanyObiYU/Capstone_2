package org.example.views;

import org.example.models.Drink;
import org.example.utils.Size;

import java.util.Scanner;

public class AddDrinkView {

    private static Scanner scanner = new Scanner(System.in);

    public static Drink addDrinkToOrder(){

        //select drink size
        Size size = selectSize();

        //select drink flavor
        String flavor = selectFlavor();

        System.out.println(size.name() + " " + flavor + " was added to order.");
        System.out.println("Returning to order menu....");

        return new Drink(size,flavor);

    }


    //helper methods
    //select size
    private static Size selectSize(){
        System.out.println("Select a size:");
        System.out.println("""
                 Small     Medium     Large
                  2.00      2.50       3.00
                """);
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");

        String sizeString = scanner.nextLine().trim();

        switch (sizeString){
            case "1" -> {
                return Size.SMALL;
            }
            case "2" -> {
                return Size.MEDIUM;
            }
            case "3"-> {
                return Size.LARGE;
            }
            default -> {
                System.out.println("Defaulting to Medium");
                return Size.MEDIUM;
            }

        }
    }

    //select drink flavor
    private static String selectFlavor(){
        System.out.println("Select drink: ");

        for(int i = 0; i<Drink.flavors.length; i++){
            System.out.println(i+1 + ") " + Drink.flavors[i]);
        }

        String selection = scanner.nextLine().trim();

        return Drink.flavors[Integer.parseInt(selection)-1];
    }
}
