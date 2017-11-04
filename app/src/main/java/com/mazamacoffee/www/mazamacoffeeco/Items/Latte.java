package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by connorlewis on 10/19/16.
 */
public class Latte extends CoffeeDrinks {
    private int numberOfShots;
    private Boolean hot;
    private ArrayList<OptionsDisplayer.Syrups> syrup;
    private String milk;
    private int whip;


    public Latte(){
        name = "Latte";
        size = "Small";
        quantity = 1;
        notes = null;
        hot = true;
        numberOfShots = 2;
        milk = "Whole";
        syrup = new ArrayList<>();
        whip = 0;
        basePrice = 3.25;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        subtotal = (basePrice + sizePriceAdjustment() * 0.5
                + isSpecialMilk() * 0.5
                + isSyrup() * 0.5 + shotsCharged() * 0.5) * quantity;
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
        if(numberOfShots <= 2){
            return 0;
        }
        return numberOfShots - 2;
    }

    public String getMilk(){
        return this.milk;
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

    public void setSyrup(ArrayList<OptionsDisplayer.Syrups> newSyrups){
        this.syrup = newSyrups;
        calculateSubtotal();
    }

    public ArrayList<OptionsDisplayer.Syrups> getSyrups() { return syrup; }

    private int isSyrup(){
        if(syrup.isEmpty()){
            return 0;
        }
        return 1;
    }

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
        if(whip){
            this.whip = 1;
        }
        else {
            this.whip = 0;
        }
        calculateSubtotal();
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final Latte theItem = this;
        super.displayOptions(context, optionsLayout, buttonLayout);

        OptionsDisplayer.displayHot(context, optionsLayout, this.getHot(), new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == 1) { theItem.setHot(true); }
                else { theItem.setHot(false); }
                OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
            }
        }); /* End of displayHot() */

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
        ); /* End of displayNumber() */

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
        order = order.append("\nHot: ").append(Boolean.toString(this.getHot()));
        order = order.append("\nNumber of Shots: ").append(Integer.toString(this.getNumberOfShots()));
        order = order.append("\nMilk Choice: ").append(this.getMilk());
        order = order.append("\nSyrups:");
        for(OptionsDisplayer.Syrups syrupChoice : this.getSyrups()) {
            order = order.append("\n\t\t-- ").append(syrupChoice.toString());
        }
        order = order.append("\nWhip: ").append(Boolean.toString(this.getWhip()));
        return order.append("\n");
    }
}
