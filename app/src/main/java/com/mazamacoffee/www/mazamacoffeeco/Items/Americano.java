package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by connorlewis on 10/19/16.
 */
public class Americano extends CoffeeDrinks {

    private Boolean hot;
    private int numberOfShots;
    private ArrayList<OptionsDisplayer.Syrups> syrup;

    public Americano(){
        name = "Americano";
        size = "Small";
        quantity = 1;
        notes = null;
        hot = true;
        numberOfShots = 2;
        syrup = new ArrayList<>();
        basePrice = 2.5;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        subtotal = (basePrice + shotsCharged() * 0.5) * quantity;
    }


    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public int getNumberOfShots(){
        return numberOfShots;
    }

    public void setNumberOfShots(int numberOfShots){
        this.numberOfShots = numberOfShots;
        calculateSubtotal();
    }

    public void setSyrup(ArrayList<OptionsDisplayer.Syrups> newSyrups){
        this.syrup = newSyrups;
        calculateSubtotal();
    }

    public ArrayList<OptionsDisplayer.Syrups> getSyrups() { return syrup; }

    private int shotsCharged(){
        if(numberOfShots <= 2){
            return 0;
        }
        return numberOfShots - 2;
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout)
    {
        final Americano theItem = this;
        super.displayOptions(context, optionsLayout, buttonLayout);
        OptionsDisplayer.displayNumber(context, optionsLayout, "Number of Shots", this.getNumberOfShots(),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theItem.setNumberOfShots((Integer) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            }
        ); /* End of displayNumber */

        OptionsDisplayer.displayHot(context, optionsLayout, this.getHot(), new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == 1) { theItem.setHot(true); }
                else { theItem.setHot(false); }
                OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
            }
        });/* End of displayHot */

        OptionsDisplayer.displaySyrup(context, optionsLayout, this.getSyrups());
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Americano");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(this.getQuantity());
        order = order.append("\nHot: ").append(this.getHot());
        order = order.append("\nNumber of Shots: ").append(this.getNumberOfShots());
        order = order.append("\nSyrups:");
        for(OptionsDisplayer.Syrups syrupChoice : this.getSyrups()) {
            order = order.append("\n\t\t-- ").append(syrupChoice.toString());
        }
        return order.append("\n");
    }
}
