package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.ArrayList;

/**
 * Created by connorlewis on 10/24/16.
 */

public class Steamer extends CoffeeDrinks {
    private String milk;
    private ArrayList<OptionsDisplayer.Syrups> syrup;
    private int whip;

    public Steamer(){
        name = "Steamer";
        size = "Small";
        quantity = 1;
        notes = null;
        milk = "Whole";
        syrup = new ArrayList<>();
        whip = 0;
        basePrice = 2.5;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        subtotal = (basePrice + sizePriceAdjustment() * .75
                + isSpecialMilk() * 0.5 + whip * 0.25) * quantity;    }

    public String getMilk(){
        return milk;
    }

    public void setMilk(String milk){
        this.milk = milk;
        calculateSubtotal();
    }

    private int isSpecialMilk(){
        if(milk.equalsIgnoreCase("Whole")){
            return 0;
        }
        return 1;
    }


    public ArrayList<OptionsDisplayer.Syrups> getSyrups() { return syrup; }

    public Boolean getWhip() {
        if(whip == 0){
            return false;
        }
        else if(whip == 1){
            return true;
        }
        return null;
    }

    public void setWhip(Boolean whip) {
        if(whip == true){
            this.whip = 1;
        }
        else {
            this.whip = 0;
        }
        calculateSubtotal();
    }
    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final Steamer theItem = this;
        super.displayOptions(context, optionsLayout, buttonLayout);

        OptionsDisplayer.displaySyrup(context, optionsLayout, this.getSyrups());

        OptionsDisplayer.displayMilk(context, optionsLayout, OptionsDisplayer.milkToInt(this.getMilk()),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theItem.setMilk((String) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            }
        ); /* End of displayMilk() */

        OptionsDisplayer.displayBoolean(context, optionsLayout, "Add Whip", this.getWhip(),
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    theItem.setWhip(isChecked);
                    OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                }
            }
        ); /* End of displayBoolean() */
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Latte");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nMilk Choice: ").append(this.getMilk());
        order = order.append("\nSyrups:");
        for(OptionsDisplayer.Syrups syrupChoice : this.getSyrups()) {
            order = order.append("\n\t\t-- ").append(syrupChoice.toString());
        }
        order = order.append("\nWhip: ").append(Boolean.toString(this.getWhip()));
        return order.append("\n");
    }
}
