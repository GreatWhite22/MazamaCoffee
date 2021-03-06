package com.mazamacoffee.www.mazamacoffeeco;


import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

/*
 * A holder for lambda functions
 */
public interface LambdaInterface {

    /**
     * Invoke lambda function "echo". The function name is the method name
     */
    @LambdaFunction(functionName = "stripe")
    ResponseClass stripe(RequestClass request);

    /**
     * Invoke lambda function "echo". The functionName in the annotation
     * overrides the default which is the method name
     */
}