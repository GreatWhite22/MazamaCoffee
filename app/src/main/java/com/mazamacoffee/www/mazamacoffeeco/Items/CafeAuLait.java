package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.Arrays;

/**
 * Created by connorlewis on 10/21/16.
 */
public class CafeAuLait extends CoffeeDrinks {
    private final String[] cafeAuLaitOptions = {"milk"};

    private String milk;

    public CafeAuLait(){
        name = "Cafe Au Lait";
        size = "Small";
        quantity = 1;
        notes = null;
        milk = "Whole";
        basePrice = 2.85;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        subtotal = (basePrice + sizePriceAdjustment() * .45
        + isSpecialMilk() * 0.5 ) * quantity;
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
        final CafeAuLait theOrder = this;
        super.displayOptions(context, optionsLayout, buttonLayout);
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
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Cafe Au Lait");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nMilk Choice: ").append(this.getMilk());
        return order.append('\n');
    }
}
