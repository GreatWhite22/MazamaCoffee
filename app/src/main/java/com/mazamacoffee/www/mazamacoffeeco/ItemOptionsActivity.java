package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mazamacoffee.www.mazamacoffeeco.Items.*;

import java.util.List;

public class ItemOptionsActivity extends AppCompatActivity {

    private String purpose;
    private Item itemToChange;
    ShoppingCart cart = ShoppingCart.getCart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_options);
        updateCart();
        itemToChange.displayOptions(this, (ViewGroup) findViewById(R.id.options), (ViewGroup) findViewById(R.id.confirmButtonLayout));

        OptionsDisplayer.displaySubtotal(itemToChange, (ViewGroup) findViewById(R.id.confirmButtonLayout));

        ViewGroup buttons = (ViewGroup) findViewById(R.id.confirmButtonLayout);
        TextView button = (TextView) buttons.findViewById(R.id.toCart);
        if(purpose.compareTo("Edit") == 0) { button.setText(R.string.options_finishEdit); }
        else { button.setText(R.string.options_addToCart); }
    }

    private void updateCart() {
        Intent reasonToChangeCart = getIntent();
        purpose = reasonToChangeCart.getStringExtra("Purpose");
        if(purpose.equalsIgnoreCase("Edit")) {
            editItemInCart(reasonToChangeCart);
        }
        else {
            addItemtoCart(reasonToChangeCart);
        }
    }

    private void editItemInCart(Intent reasonToChangeCart) {
        int newItem = reasonToChangeCart.getIntExtra("Item", 0);
        List<Item> currentCartItemsList = cart.getCartItems();
        itemToChange = currentCartItemsList.get(newItem);
    }

    private void addItemtoCart(Intent reasonToChangeCart) {
        itemToChange = (Item) reasonToChangeCart.getSerializableExtra("Item");
    }

    public void next(View view)
    {
        if(purpose.compareTo("AddToCart") == 0)
        {
            ShoppingCart cart = ShoppingCart.getCart();
            cart.addItem(itemToChange);
        }
        else
        {
            if(itemToChange.getQuantity() == 0)
            {
                ShoppingCart cart = ShoppingCart.getCart();
                cart.removeItem(itemToChange);
            }
        }
        this.finish(); /* Personal choice to delete this object when done with activity */
    }
}
