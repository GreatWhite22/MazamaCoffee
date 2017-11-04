package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;
import com.mazamacoffee.www.mazamacoffeeco.R;

/**
 * Created by connorlewis on 10/24/16.
 */

public class Smoothie extends FlavoredDrinks {

    private Boolean protein;
    private Boolean yogurt;

    private double smallPrice = 2.29;
    private double mediumPrice = 2.59;
    private double largePrice = 2.79;

    public Smoothie(){
        name = "Smoothie";
        size = "Small";
        quantity = 1;
        flavor = "Strawberry Banana";
        protein = false;
        yogurt = false;
        notes = null;
        basePrice = 3.69;
        subtotal = basePrice;
    }

    @Override
    public void calculateSubtotal() {
        if(size.compareToIgnoreCase("Small") == 0){
            subtotal = (smallPrice + isProteinYogurt() * 0.5) * quantity;
        }
        if(size.compareToIgnoreCase("Medium") == 0){
            subtotal = (mediumPrice + isProteinYogurt() * 0.5) * quantity;
        }
        else{
            subtotal = (largePrice + isProteinYogurt() * 0.5) * quantity;
        }
    }


    int isProteinYogurt(){
        int proteinYogurtPriceAdjustment = 0;
        if(protein.equals(true)){
            proteinYogurtPriceAdjustment++;
        }
        if(yogurt.equals(true)){
            proteinYogurtPriceAdjustment++;
        }
        return proteinYogurtPriceAdjustment;
    }

    public Boolean getProtein() {
        return protein;
    }

    public void setProtein(Boolean protein) {
        this.protein = protein;
        calculateSubtotal();
    }

    public Boolean getYogurt() {
        return yogurt;
    }

    public void setYogurt(Boolean yogurt) {
        this.yogurt = yogurt;
        calculateSubtotal();
    }

    private final String flavors[] = {"Strawberry Banana", "Pineapple Banana Coconut", "Mango Banana",
            "Boysenberry Blackberry", "Carrot Orange"};

    /*
     * Takes an String from an array with choices above
     * and returns an index representing the string from the array
    */
    private int flavorsToInt(String flavorChoice)
    {
        flavorChoice.trim(); /* just in case */
        if(flavorChoice.compareToIgnoreCase("Strawberry Banana") == 0) { return 0; }
        else if(flavorChoice.compareToIgnoreCase("Pineapple Banana Coconut") == 0) { return 1; }
        else if(flavorChoice.compareToIgnoreCase("Mango Banana") == 0) { return 2; }
        else if(flavorChoice.compareToIgnoreCase("Boysenberry Blackberry") == 0) { return 3; }
        else if(flavorChoice.compareToIgnoreCase("Carrot Orange") ==0) { return 4; }
        else { return -1; } // safety check;  will most likely crash program (out of bounds)
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final Smoothie theItem = this;
        super.displayOptions(context, optionsLayout, buttonLayout);

        OptionsDisplayer.displayFlavors(context, optionsLayout, flavors, this.flavorsToInt(this.getFlavor()),
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    theItem.setFlavor((String) parent.getItemAtPosition(position));
                    OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            }
        ); /* End of displayFlavors() */

        TextView heading = new TextView(context);
        heading.setText(R.string.options_addtionals);
        optionsLayout.addView(heading);
        OptionsDisplayer.displayBoolean(context, optionsLayout, "Add Yogurt", this.getYogurt(),
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    theItem.setYogurt(isChecked);
                    OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                }
            }
        ); /* End of displayBoolean() */

        OptionsDisplayer.displayBoolean(context, optionsLayout, "Add Protein", this.getProtein(),
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        theItem.setProtein(isChecked);
                        OptionsDisplayer.displaySubtotal(theItem, buttonLayout);
                    }
                }
        ); /* End of displayBoolean() */
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: Smoothie");
        order = order.append("\nSize: ").append(this.getSize());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        order = order.append("\nFlavor: ").append(this.getFlavor());
        order = order.append("\nAdd Protein: ").append(Boolean.toString(this.getProtein()));
        order = order.append("\nAdd Yogurt: ").append(Boolean.toString(this.getYogurt()));
        return order.append("\n");
    }
}
