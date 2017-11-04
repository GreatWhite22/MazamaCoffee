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
    ArrayList<Item> items;
    double total;


    ShoppingCart(){
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getCartItems(){
        return items;
    }

    public double calculateTotal(){
        double subtotal = 0;
        if(items.isEmpty()){
            return subtotal;
        }
        for(Item item: items){
            subtotal += item.getSubtotal();
        }
        total = (subtotal + subtotal * .0825);
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

}
