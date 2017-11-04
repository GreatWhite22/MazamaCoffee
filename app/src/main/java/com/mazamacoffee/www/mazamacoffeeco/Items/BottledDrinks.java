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

public class BottledDrinks implements Item {

    String name;
    int quantity;
    double basePrice;
    double subtotal;

    public BottledDrinks(){
        quantity = 1;
        name = "Water";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        name = name.toLowerCase();
        switch (name){
            case "topo chico":
                basePrice = 1.85;
                calculateSubtotal();
                break;
            case "water":
                basePrice = .92;
                calculateSubtotal();
                break;
            case "juice box":
                basePrice = .92;
                calculateSubtotal();
                break;
            case "orange juice":
                basePrice = 1.75;
                calculateSubtotal();
                break;
            case "chocolate milk box":
                basePrice = 1.62;
                calculateSubtotal();
                break;
            case "plain milk box":
                basePrice = 1.62;
                calculateSubtotal();
                break;
            case "mexican coke":
                basePrice = 1.5;
                calculateSubtotal();
                break;
            case "izze":
                basePrice = 1.65;
                calculateSubtotal();
                break;
        }
    }
    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
        calculateSubtotal();
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
        subtotal =  basePrice  * quantity;
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final BottledDrinks theOrder = this;
        OptionsDisplayer.displayName(context, optionsLayout, "Bottled Drinks");

        OptionsDisplayer.displayBottledDrinks(context, optionsLayout, OptionsDisplayer.bottledDrinksToInt(this.getName()),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theOrder.setName((String) parent.getItemAtPosition(position));
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

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: ");
        order = order.append(this.getName());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        return order.append('\n');
    }
}
