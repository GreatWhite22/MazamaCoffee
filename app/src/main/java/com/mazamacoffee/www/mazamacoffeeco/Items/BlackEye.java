package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.Arrays;

/**
 * Created by connorlewis on 10/19/16.
 */
public class BlackEye extends CoffeeDrinks {
    private int numberOfShots;

    public BlackEye(){
        name = "Black Eye";
        size = "Small";
        quantity = 1;
        notes = null;
        numberOfShots = 2;
        basePrice = 2.93;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        switch (size){
            case "Small":
                subtotal = (2.93 + shotsCharged() * 0.5 ) * getQuantity();
                break;
            case "Medium":
                subtotal = (3.25 + shotsCharged() * 0.5 ) * getQuantity();
                break;
            case "Large":
                subtotal = (3.75 + shotsCharged() * 0.5 ) * getQuantity();
        }
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
        final BlackEye theOrder = this;
        super.displayOptions(context, optionsLayout, buttonLayout);
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
        StringBuilder order = new StringBuilder("\nName: Black Eye");
        order = order.append("\nSize: ").append(this.getName());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nNumber of Shots: ").append(Integer.toString(this.getNumberOfShots()));
        return order.append('\n');
    }
}
