package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

/**
 * Created by connorlewis on 10/24/16.
 */

public class IcedTea extends FlavoredDrinks {
    private double smallPrice = 2.29;
    private double mediumPrice = 2.59;
    private double largePrice = 2.79;
    public IcedTea(){
        name = "Iced Tea";
        size = "Small";
        quantity = 1;
        flavor = this.icedTeaFlavors[0];
        notes = null;
        basePrice = smallPrice;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        String teaSize = size.toLowerCase();
        if(teaSize.compareToIgnoreCase("small") == 0){
            subtotal = smallPrice * quantity;
        }
        else if(teaSize.compareToIgnoreCase("medium") == 0){
            subtotal = mediumPrice * quantity;
        }
        else {
            subtotal = largePrice * quantity;
        }
    }
    final String icedTeaFlavors[] = {"Hibiscus Orange", "Green Tea Mango", "Ceylon Black"};
    /*
     * Takes an String from an array with choices above
     * and returns an index representing the string from the array
    */
    private int flavorsToInt(String flavorChoice)
    {
        flavorChoice.trim(); /* just in case */
        if(flavorChoice.compareToIgnoreCase("Hibiscus Orange") == 0) { return 0; }
        else if(flavorChoice.compareToIgnoreCase("Green Tea Mango") == 0) { return 1; }
        else if(flavorChoice.compareToIgnoreCase("Ceylon Black") == 0) { return 2; }
        else { return -1; } // safety check;  will most likely crash program (out of bounds)
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final IcedTea theItem = this;
        super.displayOptions(context, optionsLayout, buttonLayout);
        OptionsDisplayer.displayFlavors(context, optionsLayout, icedTeaFlavors, this.flavorsToInt(this.getFlavor()),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theItem.setFlavor((String) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            }
        );
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Iced Tea");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nFlavor: ").append(this.getFlavor());
        return order.append("\n");
    }
}
