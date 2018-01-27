package com.mazamacoffee.www.mazamacoffeeco;

import com.stripe.android.model.Token;

/**
 * Created by conno on 1/26/2018.
 */

public class RequestClass {
    String amount;
    String card_name;
    Token token;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
        //this.token = token;
    }

    public RequestClass(String amount, String card_name, Token token){
        this.amount = amount;
        this.card_name = card_name;
        this.token = token;
    }

    public RequestClass(){}
}
