package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mazamacoffee.www.mazamacoffeeco.Items.Item;

public class OrderDump extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dump);

        /* Build list of items in the order */
        ShoppingCart cart = CartHelper.getCart();
        StringBuilder orderList = new StringBuilder("Order for: ");
        orderList = orderList.append(getIntent().getStringExtra("Name"));
        orderList = orderList.append('\n');
        for(Item theItem : cart.getCartItems()) {
            orderList = orderList.append(theItem.print());
        }

        /* Add list of items to view */
        ViewGroup layout = (ViewGroup) findViewById(R.id.list_items);
        TextView itemText = new TextView(this);
        itemText.setText(orderList.toString());
        layout.addView(itemText);
    }

    public void buttonPressed(View view)
    {
        ShoppingCart cart = CartHelper.getCart();
        cart.clearCart();
        Intent startover = new Intent(this, MainActivity.class);
        startActivity(startover);
    }
}
