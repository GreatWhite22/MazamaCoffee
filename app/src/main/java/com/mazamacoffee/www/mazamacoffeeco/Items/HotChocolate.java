package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.Arrays;

/**
 * Created by connorlewis on 10/24/16.
 */

public class HotChocolate extends CoffeeDrinks {
    private String milk;
    private int whip;

    public HotChocolate(){
        name = "Hot Chocolate";
        size = "Small";
        quantity = 1;
        notes = null;
        milk = "Whole";
        basePrice = 2.5;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        subtotal = (basePrice + sizePriceAdjustment() * .75
                + isSpecialMilk() * 0.5) * quantity;
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
        final HotChocolate theItem = this;
        super.displayOptions(context, optionsLayout, buttonLayout);

        OptionsDisplayer.displayBoolean(context, optionsLayout, "Add Whip", this.getWhip(),
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    theItem.setWhip(isChecked);
                    OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                }
            }
        ); /* End of displayBoolean() */

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
        StringBuilder order = new StringBuilder("\nName: Hot Chocolate");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nMilk Choice: ").append(this.getMilk());
        order = order.append("\nWhip: ").append(Boolean.toString(this.getWhip()));
        return order.append('\n');
    }
}
