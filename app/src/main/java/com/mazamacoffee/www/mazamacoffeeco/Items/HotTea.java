package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

/**
 * Created by connorlewis on 10/24/16.
 */

public class HotTea extends FlavoredDrinks{
    public HotTea(){
        name = "Tea";
        size = "Small";
        quantity = 1;
        flavor = this.flavors[0];
        notes = null;
        basePrice = 2.25;
        subtotal = basePrice;
    }

    @Override
    void calculateSubtotal() {
        subtotal = (basePrice + sizePriceAdjustment() * .25) * quantity;
    }

    final private String flavors[] = { "Green Tea Lemon Ginseng" , "Green Tea Moroccan Mint",
            "Green Tea Garden", "English Breakfast", "Earl Gray", "Herbal Cranberry Orange",
            "Herbal Chamomile and Fruit" };
    /*
     * Takes an String from an array with choices above
     * and returns an index representing the string from the array
    */
    private int flavorsToInt(String flavorChoice)
    {
        flavorChoice.trim(); /* just in case */
        if(flavorChoice.compareToIgnoreCase("Green Tea Lemon Ginseng") == 0) { return 0; }
        else if(flavorChoice.compareToIgnoreCase("Green Tea Moroccan Mint") == 0) { return 1; }
        else if(flavorChoice.compareToIgnoreCase("Green Tea Garden") == 0) { return 2; }
        else if(flavorChoice.compareToIgnoreCase("English Breakfast") == 0) { return 3; }
        else if(flavorChoice.compareToIgnoreCase("Earl Gray") == 0) { return 4; }
        else if(flavorChoice.compareToIgnoreCase("Herbal Cranberry Orange") == 0) { return 5; }
        else if(flavorChoice.compareToIgnoreCase("Herbal Chamomile and Fruit") == 0) { return 6; }
        else { return -1; } // safety check;  will most likely crash program (out of bounds)
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final HotTea theItem = this;
        super.displayOptions(context, optionsLayout, buttonLayout);
        OptionsDisplayer.displayFlavors(context, optionsLayout, flavors, this.flavorsToInt(this.getFlavor()),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theItem.setFlavor((String) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            }
        );
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Hot Tea");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nFlavor: ").append(this.getFlavor());
        return order.append("\n");
    }
}
