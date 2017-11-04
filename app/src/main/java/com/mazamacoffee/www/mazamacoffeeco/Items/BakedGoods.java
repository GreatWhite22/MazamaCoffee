package com.mazamacoffee.www.mazamacoffeeco.Items;
//Pumpkin Spice Bread
//Banana Chocolate Chip Bread
//Lemon Blueberry Tea Cake
//Iced Goat Cookie
//Morning Glory Muffin
//Scone (lemon blueberry, peach vanilla)
//With a disclaimer that “In the event we have sold out of their selection, you will be asked to make a substitution at pickup or a cash refund will be given”


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mazamacoffee.www.mazamacoffeeco.OptionsDisplayer;

import java.util.ArrayList;
import java.util.Arrays;

public class BakedGoods implements Item {
    ArrayList<String> bakedOptions = new ArrayList<String>();

    String name;
    protected String disclaimer = "In the event we have sold out of their selection, you will be asked to make a substitution at pickup or a cash refund will be given";
    protected int quantity;
    protected double basePrice;
    protected double subtotal;

    private double scone = 2.25;
    private double bread = 2;
    private double muffin = 2.25;
    private double cookie = 1.25;
    private double cake = 2.5;

    public BakedGoods(String name){
        setName(name);
        quantity = 1;
    }

    public String getDisclaimer(){
        return disclaimer;
    }

    public void setName(String name){
        this.name = name;
        name = name.toLowerCase();
        switch (name){
            case "pumpkin spice bread":
                basePrice = bread;
                calculateSubtotal();
                break;
            case "banana chocolate chip bread":
                basePrice = bread;
                calculateSubtotal();
                break;
            case "lemon blueberry tea cake":
                basePrice = cake;
                calculateSubtotal();
                break;
            case "iced goat cookie":
                basePrice = cookie;
                calculateSubtotal();
                break;
            case "morning glory muffin":
                basePrice = muffin;
                calculateSubtotal();
                break;
            case "lemon blueberry scone":
                basePrice = scone;
                calculateSubtotal();
                break;
            case "peach vanilla scone":
                basePrice = scone;
                calculateSubtotal();
        }
    }

    public String getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
        calculateSubtotal();
    }

    public double getBasePrice (){
        return basePrice;
    }

    public void setBasePrice( double basePrice){
        this.basePrice = basePrice;
        calculateSubtotal();
    }

    @Override
    public double getUnitPrice() {
        calculateSubtotal();
        return subtotal / quantity;
    }

    public double getSubtotal(){
        calculateSubtotal();
        return subtotal;
    }

    public void calculateSubtotal(){
        subtotal = basePrice * quantity;
    }

    @Override
    public void displayOptions(Context context, ViewGroup optionsLayout, final ViewGroup buttonLayout) {
        final BakedGoods theOrder = this;
        OptionsDisplayer.displayName(context, optionsLayout, this.getName());
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
        TextView theDisclaimer = new TextView(context);
        theDisclaimer.setText(this.disclaimer);
        optionsLayout.addView(theDisclaimer);
    }

    @Override
    public StringBuilder print() {
        StringBuilder order = new StringBuilder("\nName: ");
        order = order.append(this.getName());
        order = order.append("\nQuantity: ").append(Integer.toString(this.getQuantity()));
        return order.append('\n');
    }
}
