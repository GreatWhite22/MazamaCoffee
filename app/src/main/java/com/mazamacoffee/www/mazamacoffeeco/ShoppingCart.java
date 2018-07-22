package com.mazamacoffee.www.mazamacoffeeco;

import com.mazamacoffee.www.mazamacoffeeco.Items.BottledDrinks;
import com.mazamacoffee.www.mazamacoffeeco.Items.CoffeeDrinks;
import com.mazamacoffee.www.mazamacoffeeco.Items.FlavoredDrinks;
import com.mazamacoffee.www.mazamacoffeeco.Items.Item;
import com.mazamacoffee.www.mazamacoffeeco.Items.Latte;
import com.mazamacoffee.www.mazamacoffeeco.Items.Mocha;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by connorlewis on 10/24/16.
 */

public class ShoppingCart {
    ArrayList<Item> items = new ArrayList<Item>();
    double total;
    final static double TAX = .0825;
    private static ShoppingCart cart = new ShoppingCart();

    public ArrayList<Item> getCartItems(){
        return items;
    }

    public double calculateTotal(){
        double subtotal = 0;
        for(Item item: items){
            subtotal += item.getSubtotal();
        }
        total = (subtotal + subtotal * TAX);
        return total;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        int itemLocation = items.indexOf(item);
        items.remove(itemLocation);
    }

    public void clearCart(){
        items.clear();
    }

    public static ShoppingCart getCart() {
        return cart;
    }
}
