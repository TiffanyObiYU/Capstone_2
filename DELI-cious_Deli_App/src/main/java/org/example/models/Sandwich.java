package org.example.models;

import org.example.utils.Item;
import org.example.utils.Size;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Sandwich implements Item {
    private List<String> regularToppings;
    private List<String> meats;
    private List<String> cheeses;
    private List<String> sauces = new ArrayList<>();
    private String breadType;
    boolean isToasted;
    Size size;
    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public Sandwich() {
    }

    public Sandwich(List<String> regularToppings, List<String> cheeses, List<String> meats, String breadType, boolean isToasted, Size size, List<String> sauces) {
        this.regularToppings = regularToppings;
        this.cheeses = cheeses;
        this.meats = meats;
        this.breadType = breadType;
        this.isToasted = isToasted;
        this.size = size;
        this.sauces = sauces;
    }

    public List<String> getRegularToppings() {
        return regularToppings;
    }

    public void setRegularToppings(List<String> regularToppings) {
        this.regularToppings = regularToppings;
    }

    public List<String> getMeats() {
        return meats;
    }

    public void setMeats(List<String> meats) {
        this.meats = meats;
    }

    public List<String> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<String> cheeses) {
        this.cheeses = cheeses;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<String> getSauces() {
        return sauces;
    }

    public void setSauces(List<String> sauces) {
        this.sauces = sauces;
    }

    @Override
    public double calculateTotal() {

        // check the length of the sandwich

        switch (this.size){
            case SMALL -> {

                return calculate4in();
            }

            case MEDIUM -> {
                return calculate8in();
            }

            case LARGE -> {
                return calculate12in();
            }

            default -> System.out.println("ERROR: Unable to calculate total");
        }


        //base on the length a different methods can be called to "calculate" the sandwich total

        return 0;
    }


    //helper methods
    private double calculate4in(){
        // calculate the meats and cheeses for a 4 in
        double total = 5.50; //starting total

        double firstMeatToppingsCost = 1;
        double additionalMeatToppingsCost = .50;
        double firstCheeseTopping = .75;
        double additionalCheeseToppings = .30;

        //meats
        if(!meats.isEmpty()) {
            //only if there is more than 1 meat
            total += firstMeatToppingsCost;

            for(int index = meats.size()-2; index>-1; index--){
                total += additionalMeatToppingsCost;

            }

        }

        // cheeses
        if (!cheeses.isEmpty()){
            //only if there is more than 1 cheese
            total += firstCheeseTopping;

            for(int index = cheeses.size()-2; index>-1; index--){
                total+=additionalCheeseToppings;
            }
        }

        return total;
    }

    private double calculate8in(){

        // calculate the meats and cheeses for an 8 in

        double total = 7; //starting total

        double firstMeatToppingsCost = 2;
        double additionalMeatToppingsCost = 1;
        double firstCheeseTopping = 1.50;
        double additionalCheeseToppings = .60;

        //meats
        if(!meats.isEmpty()) {
            //only if there is more than 1 meat
            total += firstMeatToppingsCost;

            for(int index = meats.size()-2; index>-1; index--){
                total += additionalMeatToppingsCost;
            }

        }

        // cheeses
        if (!cheeses.isEmpty()){
            //only if there is more than 1 cheese
            total += firstCheeseTopping;

            for(int index = cheeses.size()-2; index>-1; index--){
                total+=additionalCheeseToppings;
            }
        }

        return total;
    }

    private double calculate12in(){

        // calculate the meats and cheeses for a 12 in

        double total = 8.50; //starting total

        double firstMeatToppingsCost = 3;
        double additionalMeatToppingsCost = 1.50;
        double firstCheeseTopping = 2.25;
        double additionalCheeseToppings = .90;

        //meats
        if(!meats.isEmpty()) {
            //only if there is more than 1 meat
            total += firstMeatToppingsCost;
            for(int index = meats.size()-2; index>-1; index--){
                total += additionalMeatToppingsCost;
            }

        }

        // cheeses
        if (!cheeses.isEmpty()){
            //only if there is more than 1 cheese
            total += firstCheeseTopping;
            for(int index = cheeses.size()-2; index>-1; index--){
                total+=additionalCheeseToppings;
            }
        }

        return total;
    }

    @Override
    public String toString() {
        return " SANDWICH: " +
                "\n Size - " + size +
                "\n Bread - " + breadType  +
                "\n Meats - " + meats +
                "\n Cheeses - " + cheeses +
                "\n Regular Toppings - " + regularToppings +
                "\n Sauces - " + sauces +
                "\n Toasted - " + isToasted +
                "\n Price - " + currencyFormatter.format(calculateTotal()) ;
    }
}
