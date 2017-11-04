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

public abstract class FlavoredDrinks implements Item{
    private final String sizes[] = {"Small", "Medium", "Large"};
    String name;
    String size;
    int quantity;
    String flavor;
    String notes;
    double basePrice;
    double subtotal;

    public String getName() {
        return name;
    }

    String getSize(){
        return this.size;
    }

    public void setSize(String size) {
        if(size.compareToIgnoreCase("Small") == 0) { this.size = "Small"; }
        else if(size.compareToIgnoreCase("Medium") == 0) { this.size = "Medium"; }
        else if(size.compareToIgnoreCase("Large") == 0) { this.size = "Large"; }
        else { this.size = "Small"; }
    }

    int sizePriceAdjustment(){
        switch(size){
            case "Small":
                return 0;
            case "Medium":
                return 1;
            case "Large":
                return 2;
        }
        return 100;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
        calculateSubtotal();
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getNotes(){
        return this.notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public double getBasePrice(){
        return basePrice;
    }

    public void setBasePrice(double basePrice){
        this.basePrice = basePrice;
        calculateSubtotal();
    }

    public double getUnitPrice(){
        calculateSubtotal();
        return subtotal / quantity;
    }

    public double getSubtotal(){
        calculateSubtotal();
        return subtotal;
    }

    void calculateSubtotal(){
        subtotal = ( basePrice + sizePriceAdjustment() * 0.5 ) * quantity;
    }


    /*
     * Takes a size (Small, Medium, Large) and outputs an
     * int that can be used to index into an array comprising of the
     * above options. Useful only to option displaying
     */
    private int sizeToInt(String size)
    {
        switch(size){
            case "Small":
                return 0;
            case "Medium":
                return 1;
            case "Large":
                return 2;
        }
        return 3; /* should never be reached */
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final FlavoredDrinks theOrder = this;
        OptionsDisplayer.displayName(context, optionsLayout, this.getName());

        OptionsDisplayer.displaySize(context, optionsLayout, sizes, this.sizeToInt(this.getSize()),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theOrder.setSize((String) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theOrder, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            }
        );

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
    }
}
