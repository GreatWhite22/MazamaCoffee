package com.mazamacoffee.www.mazamacoffeeco.Items;

import android.content.Context;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by connorlewis on 10/24/16.
 */

public interface Item extends Serializable{
    public String getName();
    public double getUnitPrice();
    public double getSubtotal();
    public int getQuantity();
    public void setQuantity(int quantity);
    public void displayOptions(Context context, ViewGroup optionsLayout, ViewGroup buttonLayout);
    public StringBuilder print();
}
