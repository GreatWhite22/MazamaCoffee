package com.mazamacoffee.www.mazamacoffeeco;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.mazamacoffee.www.mazamacoffeeco.Items.Item;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

/**
 * Created by darkaether3 on 11/3/16.
 */

/*
 * A collection of helper static methods, designed to output the options of the various
 * items and components of DetailOptions activity.
 */
public class OptionsDisplayer {

/*********************************************************************************************/
/*                                                                                           */
/*                       Choices for the various widgets to display                          */
/*                                                                                           */
/*********************************************************************************************/

    final static private String milks[] = {"Whole", "Low-Fat", "Non-Fat", "Soy", "Almond"};
    /*
     * Takes an String from an array of
     * {"Whole", "Low-fat", "Non-Fat", "Soy", "Almond"}
     *  and returns an index representing the string from the array
     */
    public static int milkToInt(String milkChoice)
    {
        milkChoice = milkChoice.trim(); /* Just in case */
        if(milkChoice.compareToIgnoreCase("Whole") == 0) { return 0; }
        else if(milkChoice.compareToIgnoreCase("Low-Fat") == 0) { return 1; }
        else if(milkChoice.compareToIgnoreCase("Non-Fat") == 0) { return 2; }
        else if(milkChoice.compareToIgnoreCase("Soy") == 0) { return 3; }
        else if(milkChoice.compareToIgnoreCase("Almond") ==0) { return 4; }
        else { return -1; } // safety check; will most likely crash program (out of bounds)
    }

    final static private String typeOfBottledDrinks[] = {"Topo Chico", "Water", "Juice Box", "Orange Juice",
            "Chocolate Milk Box", "Plain Milk Box", "Mexican Coke", "Izze"};
    /*
     * Takes an String from an array with choices above
     * and returns an index representing the string from the array
     */
    public static int bottledDrinksToInt(String bottledDrinkChoice)
    {
        bottledDrinkChoice = bottledDrinkChoice.trim(); /* just in case */
        if(bottledDrinkChoice.compareToIgnoreCase("Topo Chico") == 0) { return 0; }
        else if(bottledDrinkChoice.compareToIgnoreCase("Water") == 0) { return 1; }
        else if(bottledDrinkChoice.compareToIgnoreCase("Juice Box") == 0) { return 2; }
        else if(bottledDrinkChoice.compareToIgnoreCase("Orange Juice") == 0) { return 3; }
        else if(bottledDrinkChoice.compareToIgnoreCase("Chocolate Milk Box") ==0) { return 4; }
        else if(bottledDrinkChoice.compareToIgnoreCase("Plain Milk Box") == 0) { return 5; }
        else if(bottledDrinkChoice.compareToIgnoreCase("Mexican Coke") == 0) { return 6; }
        else if(bottledDrinkChoice.compareToIgnoreCase("Izze") == 0) { return 7; }
        else { return -1; } // safety check;  will most likely crash program (out of bounds)
    }

    public enum Syrups {
        Vanilla, Caramel, Hazelnut, SF_Vanilla, SF_Caramel, SF_Hazelnut, Chocolate, White_Chocolate,
        Toffee_Nut, Lavender, Peppermint, Coconut
    }
    private final static Syrups masterSyrupList[] = {
        Syrups.Vanilla, Syrups.Caramel, Syrups.Hazelnut, Syrups.SF_Vanilla, Syrups.SF_Caramel, Syrups.SF_Hazelnut,
            Syrups.Chocolate, Syrups.White_Chocolate, Syrups.Toffee_Nut, Syrups.Lavender, Syrups.Peppermint,
            Syrups.Coconut
    };


/*********************************************************************************************/
/*                                                                                           */
/*                          Displaying the widgets (UI components)                           */
/*                                                                                           */
/*********************************************************************************************/



    public static void displayBoolean(Context context, ViewGroup layout, String label, boolean choice, CompoundButton.OnCheckedChangeListener listener)
    {
        CheckBox checkbox = new CheckBox(context);
        checkbox.setText(label);
        checkbox.setChecked(choice);
        checkbox.setOnCheckedChangeListener(listener);
        layout.addView(checkbox);
    }

    public static void displayMilk(Context context, ViewGroup layout, int choice, AdapterView.OnItemSelectedListener listener)
    {
        TextView heading = new TextView(context);
        heading.setText(R.string.options_milk);
        layout.addView(heading);
        Spinner dropDown = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, milks);
        dropDown.setAdapter(adapter);
        dropDown.setSelection(choice);
        dropDown.setOnItemSelectedListener(listener);
        layout.addView(dropDown);
    }

    public static void displayName(Context context, ViewGroup layout, String name)
    {
        TextView item = new TextView(context);
        item.setText(name);
        item.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        item.setTypeface(null, Typeface.BOLD);
        item.setTextSize(24);
        layout.addView(item);
    }

    public static void displaySize(Context context, ViewGroup layout, final String sizes[], int choice, AdapterView.OnItemSelectedListener listener)
    {
        TextView heading = new TextView(context);
        heading.setText(R.string.options_size);
        layout.addView(heading);
        Spinner dropDown = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, sizes);
        dropDown.setAdapter(adapter);
        dropDown.setSelection(choice);
        dropDown.setOnItemSelectedListener(listener);
        layout.addView(dropDown);
    }

    public static void displayNumber(Context context, ViewGroup layout, String title, int choice, AdapterView.OnItemSelectedListener listener)
    {

        final Integer numbers[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TextView heading = new TextView(context);
        heading.setText(title);
        layout.addView(heading);
        Spinner dropDown = new Spinner(context);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, numbers);
        dropDown.setAdapter(adapter);
        dropDown.setSelection(choice);
        dropDown.setOnItemSelectedListener(listener);
        layout.addView(dropDown);
    }

    public static void displayFlavors(Context context, ViewGroup layout, final String flavors[], int choice, AdapterView.OnItemSelectedListener listener)
    {
        TextView heading = new TextView(context);
        heading.setText(R.string.options_flavors);
        layout.addView(heading);
        Spinner dropDown = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, flavors);
        dropDown.setAdapter(adapter);
        dropDown.setSelection(choice);
        dropDown.setOnItemSelectedListener(listener);
        layout.addView(dropDown);
    }

    public static void displayBottledDrinks(Context context, ViewGroup layout, int choice, AdapterView.OnItemSelectedListener listener)
    {
        TextView heading = new TextView(context);
        heading.setText(R.string.options_bottledDrinks);
        layout.addView(heading);
        Spinner dropDown = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, typeOfBottledDrinks);
        dropDown.setAdapter(adapter);
        dropDown.setSelection(choice);
        dropDown.setOnItemSelectedListener(listener);
        layout.addView(dropDown);
    }

    public static void displayHot(Context context, ViewGroup layout, boolean choice, RadioGroup.OnCheckedChangeListener listener)
    {
        TextView message = new TextView(context);
        message.setText(R.string.options_hotness);
        layout.addView(message);
        RadioGroup buttonGroup = new RadioGroup(context);
        RadioButton hot = new RadioButton(context);
        RadioButton notHot = new RadioButton(context);
        hot.setText(R.string.options_hot);
        notHot.setText(R.string.options_notHot);
        buttonGroup.addView(notHot);
        buttonGroup.addView(hot);   /* this way if checkid = 1, users chose hot */
        if(choice) { hot.toggle(); } /* i.e hot = true */
        else { notHot.toggle(); }
        buttonGroup.setOnCheckedChangeListener(listener);
        layout.addView(buttonGroup);
    }

    /*
     * Displays the subtotal on the DetailedOptions activity.
     * To only be used with the layout that has the TextView
     * with android:id subtotal. Undefined behavior otherwise
     * (will most likely cause null pointer exception).
     */
    public static void displaySubtotal(Item item, ViewGroup layout)
    {
        TextView price = (TextView) layout.findViewById(R.id.subtotal);
        price.setTextSize(20);
        StringBuilder subtotal = new StringBuilder(layout.getResources().getString(R.string.options_subtotal));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.append(" ");
        subtotal.append(formatter.format(item.getSubtotal()));
        price.setText(subtotal);
    }

    /* Checks if the target syrup is already chosen, and removes it */
    private static boolean isSyrupThere(ArrayList<OptionsDisplayer.Syrups> syrupList, OptionsDisplayer.Syrups target)
    {
        if(syrupList.isEmpty()) { return false; }
        for(OptionsDisplayer.Syrups elem : syrupList)
        {
            if(elem == target)
            {
                syrupList.remove(elem);
                return true;
            }
        }
        return false;
    }

    private static String buildCurrentSyrupString(ArrayList<OptionsDisplayer.Syrups> currentSyrups)
    {
        if(currentSyrups.isEmpty()) { return "[None]"; }
        StringBuilder string = new StringBuilder();
        String intermediateStep;
        for(int i=0; i<currentSyrups.size(); i++)
        {
            OptionsDisplayer.Syrups elem = currentSyrups.get(i);
            intermediateStep = elem.toString().replaceAll("_", " ");
            if(i < currentSyrups.size()-1) { string.append(intermediateStep).append(", "); }
            else { string.append(intermediateStep); }
        }
        return string.toString();
    }

    public static void displaySyrup(final Context context, final ViewGroup layout,
                                    final ArrayList<OptionsDisplayer.Syrups> currentSyrups)
    {
        TextView heading = new TextView(context);
        heading.setText(R.string.options_syrups);
        final Button button = new Button(context);
        button.setText(buildCurrentSyrupString(currentSyrups));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syrupPopup(context, currentSyrups, button);
            }
        });
        layout.addView(heading);
        layout.addView(button);
    }

    private static void syrupPopup(Context context, final ArrayList<Syrups> currentSyrups, final Button button)
    {
        /* First, determine what syrups did the user already add (and remove it from currentSyrups) */
        ArrayList<OptionsDisplayer.Syrups> allSyrups = new ArrayList<>();
        allSyrups.addAll(Arrays.asList(masterSyrupList));
        final EnumMap<OptionsDisplayer.Syrups, Boolean> syrupMap = new EnumMap<>(OptionsDisplayer.Syrups.class);
        for(int i=0; i<allSyrups.size(); i++)
        {
            OptionsDisplayer.Syrups syrupChoice = allSyrups.get(i);
            if(isSyrupThere(currentSyrups, syrupChoice)) { syrupMap.put(syrupChoice, true); }
            else { syrupMap.put(syrupChoice, false); }
        }

        /* By now, the currentSyrups should be empty and the map show tell us what syrups are chosen */
        /* Now we need to build the arrays needed for AlertDialog.setMultiChoiceItems */
        final OptionsDisplayer.Syrups syrupArray[] = syrupMap.keySet().toArray(new OptionsDisplayer.Syrups[0]);
        String[] syrupStringArray = new String[syrupArray.length];
        boolean[] isSyrupChosen = new boolean[syrupArray.length];
        for(int i=0; i<syrupArray.length; i++) {
            syrupStringArray[i] = syrupArray[i].toString().replaceAll("_", " "); // no underscores
            if(syrupMap.get(syrupArray[i])) { isSyrupChosen[i] = true; }
            else {isSyrupChosen[i] = false; }
        }

        /* Build the Alert Dialog */
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(R.string.options_syrupEdit);
        alertBuilder.setMultiChoiceItems(syrupStringArray, isSyrupChosen,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        syrupMap.put(syrupArray[which], isChecked);
                    }
                }
        ); /* End of setMultiChoiceItems() */
        alertBuilder.setPositiveButton(R.string.options_done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(OptionsDisplayer.Syrups elem : syrupMap.keySet()) {
                    if(syrupMap.get(elem)) { currentSyrups.add(elem); }
                }
                button.setText(buildCurrentSyrupString(currentSyrups));
            }
        }); /* End of setPositiveButton() */
        alertBuilder.show();
    }
}
