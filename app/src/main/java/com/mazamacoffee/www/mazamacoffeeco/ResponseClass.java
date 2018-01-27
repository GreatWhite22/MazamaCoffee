package com.mazamacoffee.www.mazamacoffeeco;

/**
 * Created by conno on 1/26/2018.
 */

public class ResponseClass {
    String result;

    public String getResult(){
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ResponseClass(String result){
        this.result = result;
    }

    public ResponseClass(){}
}
