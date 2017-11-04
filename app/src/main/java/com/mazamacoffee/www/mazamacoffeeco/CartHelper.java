package com.mazamacoffee.www.mazamacoffeeco;

/**
 * Created by Connor Lewis on 10/30/2016.
 */
//class to retrieve static shopping cart. Call {getCart()} to get shopping cart before performing
    //operations
public class CartHelper {
    private static ShoppingCart cart = new ShoppingCart();

    public static ShoppingCart getCart(){
        if(cart == null){
            cart = new ShoppingCart();
        }
        return cart;
    }
}
