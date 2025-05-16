package org.example.models;

import org.example.utils.Item;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Order {
    String nameOnOrder;
    List<Item> orderItems;
    double orderTotal;
    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public Order() {
        nameOnOrder = "";
        orderItems = new ArrayList<>();
    }

    public Order(String nameOnOrder, List<Item> orderItems, double orderTotal) {
        this.nameOnOrder = nameOnOrder;
        this.orderItems = orderItems;
        this.orderTotal = orderTotal;
    }

    public String getNameOnOrder() {
        return nameOnOrder;
    }

    public void setNameOnOrder(String nameOnOrder) {
        this.nameOnOrder = nameOnOrder;
    }

    public List<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Item> orderItems) {
        this.orderItems = orderItems;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    //addItemToOrder
    public void addItemToOrder(Item item){
        this.orderItems.add(item);
    }

    public double calculateOrderTotal(){

       for(Item item: orderItems){
           orderTotal+=item.calculateTotal();
       }

        return orderTotal;
    }

    @Override
    public String toString() {
        //TODO: Add a date a time on the order
        return "======== Order Summary ========" +
                "\nCustomer Name: " + nameOnOrder.toUpperCase() +
                "\nItems: \n" + orderItems +
                "\nOrder Total: " + currencyFormatter.format(orderTotal);
    }
}
