package com.mazamacoffee.www.mazamacoffeeco.Items;

/**
 * Created by connorlewis on 10/24/16.
 */

public class ColdBrew extends CoffeeDrinks {
    public ColdBrew(){
        name = "Cold Brew";
        size = "Small";
        quantity = 1;
        notes = null;
        basePrice = 2.75;
        subtotal = basePrice;
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Cold Brew");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        return order.append('\n');
    }
}
