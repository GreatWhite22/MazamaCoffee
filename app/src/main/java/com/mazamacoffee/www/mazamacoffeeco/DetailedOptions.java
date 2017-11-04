package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mazamacoffee.www.mazamacoffeeco.Items.*;

import java.util.List;

public class DetailedOptions extends AppCompatActivity {

    private String purpose;
    private Item theItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_options);

        /* Set item specific options */
        Intent here = getIntent();
        purpose = here.getStringExtra("Purpose");
        if(purpose.compareToIgnoreCase("Edit") == 0) // Edit = Item contains index into shopping cart
        {
            int itemPosition = here.getIntExtra("Item", -1);
            ShoppingCart cart = CartHelper.getCart();
            List<Item> currentCart = cart.getCartItems();
            theItem = currentCart.get(itemPosition);
        }
        else { theItem = (Item) here.getSerializableExtra("Item"); } // AddtoCart = Item contains new Item to add for cart
        theItem.displayOptions(this, (ViewGroup) findViewById(R.id.options), (ViewGroup) findViewById(R.id.confirmButtonLayout));

        /* Set price */
        OptionsDisplayer.displaySubtotal(theItem, (ViewGroup) findViewById(R.id.confirmButtonLayout));

        /* Set Button Text */
        ViewGroup buttons = (ViewGroup) findViewById(R.id.confirmButtonLayout);
        TextView button = (TextView) buttons.findViewById(R.id.toCart);
        if(purpose.compareTo("Edit") == 0) { button.setText(R.string.options_finishEdit); }
        else { button.setText(R.string.options_addToCart); }
    }

    public void next(View view)
    {
        if(purpose.compareTo("AddToCart") == 0)
        {
            ShoppingCart cart = CartHelper.getCart();
            cart.addItem(theItem);
        }
        else
        {
            if(theItem.getQuantity() == 0)
            {
                ShoppingCart cart = CartHelper.getCart();
                cart.removeItem(theItem);
            }
        }
        this.finish(); /* Personal choice to delete this object when done with activity */
    }
}
