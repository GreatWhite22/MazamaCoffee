package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by connorlewis on 10/24/16.
 */

public class Cortado extends CoffeeDrinks {

    private int numberOfShots;
    private String milk;
    private ArrayList<OptionsDisplayer.Syrups> syrup;

    public Cortado(){
        name = "Cortado";
        size = "cortado";
        quantity = 1;
        notes = null;
        numberOfShots = 2;
        syrup = new ArrayList<>();
        milk = "Whole";
        basePrice = 2.85;
        subtotal = basePrice;
    }

    public void calculateSubtotal(){
        int isSyrup;
        if(this.syrup.isEmpty()) { isSyrup = 0; }
        else { isSyrup = 1; }
        subtotal = ( basePrice + shotsCharged() * 0.5
        + isSpecialMilk() * 0.5 + isSyrup * 0.5 ) * quantity;
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

    public void setSyrup(ArrayList<OptionsDisplayer.Syrups> newSyrups){
        this.syrup = newSyrups;
        calculateSubtotal();
    }

    public ArrayList<OptionsDisplayer.Syrups> getSyrups() { return syrup; }

    public String getMilk(){
        return this.milk;
    }

    public void setMilk(String milk){
        this.milk = milk;
        calculateSubtotal();
    }

    private int isSpecialMilk(){
        if(milk.equalsIgnoreCase("whole")){
            return 0;
        }
        return 1;
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final Cortado theOrder = this;
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

        OptionsDisplayer.displayMilk(context, optionsLayout, OptionsDisplayer.milkToInt(this.getMilk()),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theOrder.setMilk((String) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theOrder, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            }
        ); /* End of displayMilk() */

        OptionsDisplayer.displaySyrup(context, optionsLayout, this.getSyrups());
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Cortado");
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nNumber of Shots: ").append(Integer.toString(this.getNumberOfShots()));
        order = order.append("\nMilk Choice: ").append(this.getMilk());
        order = order.append("\nSyrups:");
        for(OptionsDisplayer.Syrups syrupChoice : this.getSyrups()) {
            order = order.append("\n\t\t-- ").append(syrupChoice.toString());
        }
        return order.append("\n");
    }
}
