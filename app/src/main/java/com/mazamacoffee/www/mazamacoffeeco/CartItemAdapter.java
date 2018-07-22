package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.mazamacoffee.www.mazamacoffeeco.Items.Item;
import com.mazamacoffee.www.mazamacoffeeco.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.NumberFormat;


/**
 * Created by Connor Lewis on 10/30/2016.
 */

public class CartItemAdapter extends BaseAdapter{
    private static final String TAG = "CartItemAdapter";

    private QuantityListener quantityListener;

    public interface QuantityListener{
        public void quantityUpdate();
    }

    public void setQuantityListener(QuantityListener quantityListener){
        this.quantityListener = quantityListener;
    }

    private List<Item> cartItems = Collections.emptyList();

    private final Context context;

    public CartItemAdapter(Context context){
        this.context = context;
    }

    public void updateCartItems(ArrayList<Item> cartItems){
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Item getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView name;
        TextView unitPrice;
        TextView totalItemPrice;
        TextView totalPrice;
        final Spinner quantity;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_cart_item, parent, false);
            name = (TextView) convertView.findViewById(R.id.itemName);
            unitPrice = (TextView) convertView.findViewById(R.id.itemUnitPrice);
            quantity = (Spinner) convertView.findViewById(R.id.itemQuantity);
            totalItemPrice = (TextView) convertView.findViewById(R.id.itemTotalPrice);

            convertView.setTag(new ViewHolder(name, unitPrice, quantity, totalItemPrice));
        }
        else{
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            name = viewHolder.itemName;
            unitPrice = viewHolder.itemUnitPrice;
            quantity = viewHolder.itemQuantity;
            totalItemPrice = viewHolder.itemTotalPrice;
        }

        final ShoppingCart cart = ShoppingCart.getCart();
        final Item cartItem = getItem(position);

        name.setText(cartItem.getName());
        NumberFormat convertToCurrency = NumberFormat.getCurrencyInstance();
        unitPrice.setText(String.valueOf(convertToCurrency.format(cartItem.getUnitPrice())));
        totalItemPrice.setText(String.valueOf(convertToCurrency.format(cartItem.getSubtotal())));

        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(context,
                R.array.quantity_spinner, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        quantity.setPrompt("Quantity");
        quantity.setAdapter(spinnerAdapter);

        int spinnerPosition = spinnerAdapter.getPosition(String.valueOf(cartItem.getQuantity()));
        spinnerAdapter.notifyDataSetChanged();
        quantity.setSelection(spinnerPosition);

        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != cartItem.getQuantity()){
                    if (position == 0) {
                        cart.removeItem(cartItem);
                        updateCartItems(cart.getCartItems());
                    }
                    else{
                        cartItem.setQuantity(position);
                        updateCartItems(cart.getCartItems());
                    }
                    quantityListener.quantityUpdate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return convertView;
    }

    private static class ViewHolder{
        public final TextView itemName;
        public final TextView itemUnitPrice;
        public final Spinner itemQuantity;
        public final TextView itemTotalPrice;

        public ViewHolder(TextView itemName, TextView itemUnitPrice, Spinner itemQuantity, TextView itemTotalPrice){
            this.itemName = itemName;
            this.itemUnitPrice = itemUnitPrice;
            this.itemQuantity = itemQuantity;
            this.itemTotalPrice = itemTotalPrice;
        }
    }
}
