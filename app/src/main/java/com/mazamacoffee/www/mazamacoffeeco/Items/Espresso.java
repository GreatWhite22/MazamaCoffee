package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.Arrays;

/**
 * Created by connorlewis on 10/24/16.
 */

public class Espresso extends CoffeeDrinks {

    private int numberOfShots = 2;

    public Espresso(){
        name = "Espresso";
        size = "espresso"; //one size no option
        quantity = 1;
        numberOfShots = 2;
        notes = null;
        basePrice = 2.5;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        subtotal = ( basePrice + shotsCharged() * 0.5 ) * quantity;
    }


    public int getNumberOfShots(){
        return numberOfShots;
    }

    public void setNumberOfShots(int numberOfShots){
        this.numberOfShots = numberOfShots;
        calculateSubtotal();
    }

    private int shotsCharged(){
        if(numberOfShots <= 2){
            return 0;
        }
        return numberOfShots - 2;
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final Espresso theOrder = this;
        // Normally the name and quantity is taken care of by the super class
        // However, since size is fixed, I am manually calling displayName
        // and displayQuantity here.
        OptionsDisplayer.displayName(context, optionsLayout, this.getName());
        OptionsDisplayer.displayNumber(context, optionsLayout, "Quantity", this.getQuantity(),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theOrder.setQuantity((Integer) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theOrder, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            }
        ); /* End of displayNumber */
        OptionsDisplayer.displayNumber(context, optionsLayout, "Number of Shots", this.getNumberOfShots(),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theOrder.setNumberOfShots((Integer) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theOrder, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            }
        ); /* End of displayNumber */
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Expresso");
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nNumber of Shots: ").append(Integer.toString(this.getNumberOfShots()));
        return order.append("\n");
    }
}
