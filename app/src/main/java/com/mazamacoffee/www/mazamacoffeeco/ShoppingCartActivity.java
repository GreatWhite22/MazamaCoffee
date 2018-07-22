package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;

public class ShoppingCartActivity extends AppCompatActivity{
    private static final String TAG = "ShoppingCartActivity";

    ListView cartItems;
    Button buttonClear;
    Button buttonCheckout;

    final ShoppingCart cart = CartHelper.getCart();
    final CartItemAdapter cartItemAdapter = new CartItemAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        cartItems = (ListView)findViewById(R.id.cartItems);
        LayoutInflater layoutInflater = getLayoutInflater();


        final TextView totalPrice = (TextView) findViewById(R.id.totalPrice);

        final NumberFormat convertToCurrency = NumberFormat.getCurrencyInstance();
        totalPrice.setText(String.valueOf(convertToCurrency.format(cart.calculateTotal())));

        cartItemAdapter.updateCartItems(cart.getCartItems());

        cartItems.setAdapter(cartItemAdapter);

        cartItemAdapter.setQuantityListener(new CartItemAdapter.QuantityListener(){
            @Override
            public void quantityUpdate(){
                totalPrice.setText(String.valueOf(convertToCurrency.format(cart.calculateTotal())));
            }
        });

        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonCheckout = (Button) findViewById(R.id.buttonCheckout);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.clearCart();
                cartItemAdapter.updateCartItems(cart.getCartItems());
                cartItemAdapter.notifyDataSetChanged();
                totalPrice.setText(String.valueOf(convertToCurrency.format(cart.calculateTotal())));
            }
        });

        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingCartActivity.this, PaymentActivity.class);
                intent.putExtra("Charge", cart.calculateTotal());
                startActivity(intent);
            }
        });

        /* User wants to update an item; give index of Item in shopping cart */
        cartItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent updateItem = new Intent(ShoppingCartActivity.this, ItemOptionsActivity.class);
                updateItem.putExtra("Item", position);
                updateItem.putExtra("Purpose", "Edit");
                startActivityForResult(updateItem, 0); // don't actually need request code
            }
        });
    }

    /* Updates the cart information, once user is finished editing */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cartItemAdapter.updateCartItems(cart.getCartItems());
        cartItemAdapter.notifyDataSetChanged();
    }
}
