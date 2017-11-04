package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.Arrays;

/**
 * Created by connorlewis on 10/19/16.
 */
public class Chai extends CoffeeDrinks {
    private int numberOfShots;
    private String milk;
    private Boolean hot;

    public Chai(){
        name = "Chai";
        size = "Small";
        quantity = 1;
        notes = null;
        hot = true;
        numberOfShots = 0;
        milk = "Whole";
        basePrice = 3.5;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        if(sizePriceAdjustment() >= 0) {
            subtotal = (basePrice + sizePriceAdjustment() * .25
                    + shotsCharged() * 0.5
                    + isSpecialMilk() * 0.5) * getQuantity();
        }
        subtotal = (basePrice + sizePriceAdjustment() * 0.5
                + shotsCharged() * 0.5
                + isSpecialMilk() * 0.5) * getQuantity();
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

    private int shotsCharged(){
        return numberOfShots;
    }

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
        final Chai theItem = this;
        super.displayOptions(context, optionsLayout, buttonLayout);

        OptionsDisplayer.displayHot(context, optionsLayout, this.getHot(), new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == 1) { theItem.setHot(true); }
                else { theItem.setHot(false); }
                OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
            }
        });/* End of displayHot */

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
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Chai");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nHot: ").append(Boolean.toString(this.getHot()));
        order = order.append("\nNumber of Shots: ").append(Integer.toString(this.getNumberOfShots()));
        order = order.append("\nMilk Choice: ").append(this.getMilk());
        return order.append('\n');
    }
}
