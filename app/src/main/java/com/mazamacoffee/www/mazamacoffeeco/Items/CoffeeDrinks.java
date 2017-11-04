package com.mazamacoffee.www.mazamacoffeeco.Items;

/**
 * Created by connorlewis on 10/13/16.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class CoffeeDrinks implements Item {
    private final String sizes[] = {"Kids", "Small", "Medium", "Large"};
    String name;
    String size;
    int quantity;
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
        if(size.compareToIgnoreCase("Kids") == 0) { this.size = "Kids"; }
        else if(size.compareToIgnoreCase("Small") == 0) { this.size = "Small"; }
        else if(size.compareToIgnoreCase("Medium") == 0) { this.size = "Medium"; }
        else if(size.compareToIgnoreCase("Large") == 0) { this.size = "Large"; }
        else { this.size = "Kids"; }
    }

    int sizePriceAdjustment(){
        switch(size){
            case "Kids":
                return -1;
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
     * Takes a size (Kids, Small, Medium, Large) and outputs an
     * int that can be used to index into an array comprising of the
     * above options. Useful only to option displaying
     */
    private int sizeToInt(String size)
    {
        size.trim(); /* Just in case */
        if(size.compareToIgnoreCase("Kids") == 0) { return 0; }
        else if(size.compareToIgnoreCase("Small") == 0) { return 1; }
        else if(size.compareToIgnoreCase("Medium") == 0) { return 2; }
        else if(size.compareToIgnoreCase("Large") == 0) { return 3; }
        else { return -1; } // safety check; will most likely crash the program
    }

    /*
     * Displays some basic options. However, I fully expect subclasses
     * override this method (but still call) and add their specialized
     * options.
     */
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final CoffeeDrinks theOrder = this;
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
        );
    }
}
