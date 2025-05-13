package org.example.models;

import org.example.utils.Item;

import java.text.NumberFormat;
import java.util.Locale;

public class Chips implements Item {

    public static String[] chipsarr = {"Lays","Doritos","Kettle Cooked","Sun Chips"};
    private String selection;
    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public Chips(){

    }

    public Chips(String selection) {
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    @Override
    public double calculateTotal() {
        return 1.50;
    }

    @Override
    public String toString() {
        return "CHIPS:  " + selection + "\n Price - " + currencyFormatter.format(calculateTotal());
    }
}
