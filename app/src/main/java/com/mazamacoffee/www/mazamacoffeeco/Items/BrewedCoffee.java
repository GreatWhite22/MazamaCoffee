package com.mazamacoffee.www.mazamacoffeeco.Items;

import java.util.Arrays;

/**
 * Created by connorlewis on 10/19/16.
 */
public class BrewedCoffee extends CoffeeDrinks {
    public BrewedCoffee(){
        name = "Brewed Coffee";
        size = "Small";
        quantity = 1;
        notes = null;
        basePrice = 1.85;
        subtotal = basePrice;
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Brewed Coffee");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        return order.append('\n');
    }
}
