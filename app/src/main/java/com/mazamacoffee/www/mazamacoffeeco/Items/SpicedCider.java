package com.mazamacoffee.www.mazamacoffeeco.Items;

/**
 * Created by connorlewis on 10/24/16.
 */

public class SpicedCider extends CoffeeDrinks {
    public SpicedCider(){
        name = "Spiced Cider";
        size = "Small";
        quantity = 1;
        notes = null;
        basePrice = 2.85;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        if(sizePriceAdjustment() > 0){
            subtotal = ( 3 + sizePriceAdjustment() * 0.5 ) * quantity;
        }
        else {
            subtotal = basePrice * quantity;
        }
    }
    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Spiced Cider");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        return order.append("\n");
    }
}
