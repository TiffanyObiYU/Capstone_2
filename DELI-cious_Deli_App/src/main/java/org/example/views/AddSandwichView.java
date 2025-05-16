package org.example.views;

import org.example.models.Sandwich;
import org.example.utils.SandwichHelper;
import org.example.utils.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddSandwichView {
    static Scanner scanner = new Scanner(System.in);


    public static Sandwich addSandwichToOrder(){
        Sandwich sandwich = new Sandwich();
        // Select Bread Type
        sandwich.setBreadType(selectBread());

        // Select Sandwich Size
        sandwich.setSize(selectSize());

        //Select Toppings
        System.out.println("Awesome! Please select the toppings you want on your sandwich");
        System.out.println("-------------------------------------------------------------");
            //meats
                sandwich.setMeats(addMeatToOrder());
            //cheeses
                sandwich.setCheeses(addCheeseToOrder());
            //regular toppings
                sandwich.setRegularToppings(addRegToppingsToOrder());

        //check if bread will be toasted
        sandwich.setToasted(toastBread());

        //add sauces to order
        sandwich.setSauces(addSauceToOrder());

        return sandwich;
    }

    //helper methods
    //selectSandwichSize
    private static Size selectSize(){
        System.out.println("Choose your sandwich size");
        System.out.println(""" 
                1) 4in =  $5.50
                2) 8in =  $7.00
                3) 12in = $8.50
               """);

        String size = scanner.nextLine().trim();

        switch (size){
            case "1" -> {
                return Size.SMALL;
            }
            case "2" -> {
                return Size.MEDIUM;
            }
            case "3" -> {
                return Size.LARGE;
            }

            default -> {
                System.out.println(" Defaulting to 8in sandwich");
                return Size.MEDIUM;
            }

        }
    }

    private static String selectBread(){
        System.out.println("Choose your bread");
        System.out.println(""" 
                1) White
                2) Wheat
                3) Rye
                4) Wrap
               """);
        String bread = scanner.nextLine().trim();

        switch (bread) {

            case "1" -> {
                return "White";
            }
            case "2" -> {
                return "Wheat";
            }
            case "3" -> {
                return "Rye";
            }
            case "4" -> {
                return "Wrap";
            }

            default -> {
                System.out.println(" Defaulting to White Bread");
                return "White";
            }

        }
    }

    //toastBread
    private static boolean toastBread(){

        System.out.println("Do you want your bread toasted?");
        System.out.println("Y - Yes / N - No");

        String selection = scanner.nextLine().trim();

        switch (selection.toUpperCase()){
            case "Y" -> {
                System.out.println("Toasted");
                return true;
            }
            default -> {

                System.out.println("Defaulting to Not Toasted");

                return false;
            }
        }
    }

    private static List<String> addMeatToOrder() {

        List<String> meatToppings = new ArrayList<>();
        //display options
        for (int i = 0; i < SandwichHelper.meats.length; i++) {
            System.out.println(i + 1 + ") " + SandwichHelper.meats[i]);
        }

        System.out.println("Notice: Please separate toppings by commas!");
        System.out.println("To indicate extra of a topping, select it twice");
        System.out.println("  EX: 5,5,6 is - 2x chicken with 1x bacon  ");
        System.out.println("========== Meat Topping Prices ============");
        System.out.println("""
                               4"     8"       12"
                              1.00   2.00     3.00
                Extra Meat:   .50    1.00     1.50
                """);
        System.out.println("Select X to skip meat toppings");

        String[] meats = scanner.nextLine().trim().split(",");


        if (meats.length == 0 || meats[0].equalsIgnoreCase("X")) {
            return meatToppings;
        } else{
            for (String meatIndex : meats) {
                String meatString = SandwichHelper.meats[Integer.parseInt(meatIndex) - 1];
                meatToppings.add(meatString);
            }
            return meatToppings;
        }
    }

    private static List<String> addCheeseToOrder(){
        List<String> cheeseToppings = new ArrayList<>();
        //display options
        for (int i = 0; i < SandwichHelper.cheese.length; i++) {
            System.out.println(i + 1 + ") " + SandwichHelper.cheese[i]);
        }

        System.out.println("Notice: Please separate toppings by commas!");
        System.out.println("To indicate extra of a topping, select it twice");
        System.out.println("  EX: 1,1,3 is - 2x american with 1x cheddar  ");
        System.out.println("========== Cheese Topping Prices ============");
        System.out.println("""
                                 4"      8"      12"
                                .75     1.50    2.25
                Extra Cheese:   .30     .60     .90
                """);
        System.out.println("Select X to skip cheese toppings");

        String[] cheeses = scanner.nextLine().trim().split(",");

        if (cheeses.length == 0 || cheeses[0].equalsIgnoreCase("X")) {
            return cheeseToppings;
        } else{
            for (String cheeseIndex : cheeses) {
                String cheeseString = SandwichHelper.cheese[Integer.parseInt(cheeseIndex) - 1];
                cheeseToppings.add(cheeseString);
            }
            return cheeseToppings;
        }
    }

    //add regular toppings
    private static List<String> addRegToppingsToOrder() {
            List<String> regToppings = new ArrayList<>();
            //display options
            for (int i = 0; i < SandwichHelper.regularToppings.length; i++) {

                System.out.println(i + 1 + ") " + SandwichHelper.regularToppings[i]);
            }

            System.out.println("Notice: Please separate toppings by commas!");
            System.out.println("To indicate extra of a topping, select it twice");
            System.out.println("  EX: 1,4,6,6 is - 1x lettuce, 1x tomatoes, 2x pickles ");
            System.out.println("Select X to skip regular toppings");

            String[] regToppingsArr = scanner.nextLine().trim().split(",");


            if (regToppingsArr.length == 0 || regToppingsArr[0].equalsIgnoreCase("X")) {
                return regToppings;
            } else {
                for (String regIndex : regToppingsArr) {
                    String regString = SandwichHelper.regularToppings[Integer.parseInt(regIndex) - 1];
                    regToppings.add(regString);
                }
                return regToppings;
            }
        }

        private static List<String> addSauceToOrder() {

            List<String> sauces = new ArrayList<>();

            System.out.println("""
                    ==== Add Sauce To Your Order ====
                    1) mayo
                    2) mustard
                    3) ketchup
                    4) ranch
                    5) thousand islands
                    6) vinaigrette
                    """);
            System.out.println("Notice: Please separate causes by commas!");
            System.out.println("To indicate extra of a topping, select it twice");
            System.out.println("  EX: 1,3,3 is - 1x mayo, 2x ketchup ");
            System.out.println(" Select X to skip adding sauce to order ");

            String[] sauceArr = scanner.nextLine().trim().split(",");

            if (sauceArr.length == 0 || sauceArr[0].equalsIgnoreCase("X")) {
                return sauces;
            } else {

                for(int index = 0; index<sauceArr.length;index++){
                  String sauceString = sauceArr[index];
                  sauces.add(SandwichHelper.sauces[Integer.parseInt(sauceString)-1]);
                }
                    return sauces;
            }
        }
    }
