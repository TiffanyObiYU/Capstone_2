package org.example.models;

import org.example.utils.Item;
import org.example.utils.Size;

import java.text.NumberFormat;
import java.util.Locale;

public class Drink implements Item {

    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    public static String[] flavors = {"Pepsi","Coke","Ginger-Ale","Lemonade","Apple Juice"};
    private Size size;
    private String flavor;

    public Drink(Size size, String flavor){
        this.size = size;
        this.flavor = flavor;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double calculateTotal() {

        switch (this.size) {
            case Size.SMALL:
                return 2;

            case Size.MEDIUM:
                return 2.50;

            case Size.LARGE:
                return 3;

        }
        return 0;
    }

    @Override
    public String toString() {
        return "DRINK: " + "\n Size - "+ size +
                "\n Flavor - " + flavor + "\n Price - " + currencyFormatter.format(calculateTotal());
    }
}
