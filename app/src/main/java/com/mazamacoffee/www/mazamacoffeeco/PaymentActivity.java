package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.*;
import com.amazonaws.regions.Regions;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import static java.lang.Integer.parseInt;

public class PaymentActivity extends AppCompatActivity {

    EditText cardName;
    CardInputWidget mCardInputWidget;
    TextView purchaseTotal;
    View payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        buildPrice();

        mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);

        cardName = (EditText) findViewById(R.id.card_name);

        payButton = findViewById(R.id.buttonPaymentPay);
        payButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                paymentSubmit();
            }
        });
    }

    private void buildPrice(){
        Intent intent = getIntent();
        double price = intent.getDoubleExtra("Charge", 0.0);
        purchaseTotal = (TextView) findViewById(R.id.total_price);
        StringBuilder subtotal = new StringBuilder(getResources().getString(R.string.payment_total));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.append(" ");
        subtotal.append(formatter.format(price));
        purchaseTotal.setText(subtotal);
    }


       public void paymentSubmit() {
        Card card = mCardInputWidget.getCard();
        if(card == null){
            throw new IllegalArgumentException("Card number is wrong");
            //TODO: Handle invalid card
        }
        card.setName(cardName.getText().toString());

        Stripe stripe = new Stripe(getApplicationContext(), "pk_test_irEAw0mlXyAIwo6XFQRlBpOi");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        String totalPriceString = purchaseTotal.getText().toString();
                        totalPriceString = totalPriceString.substring(8); //TODO: This is f****ing ugly turn into method
                        Double decimalPrice = Double.parseDouble(totalPriceString) * 100;
                        Integer totalPrice = decimalPrice.intValue();

                        // Create an instance of CognitoCachingCredentialsProvider
                        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
                                getApplicationContext(),
                                "us-east-1:128e557f-5d2d-4efd-a662-56fb492a513d",
                                Regions.US_EAST_1
                        );

                        // Create LambdaInvokerFactory, to be used to instantiate the Lambda proxy.
                        LambdaInvokerFactory factory = new LambdaInvokerFactory(getApplicationContext(),
                                Regions.US_EAST_1, cognitoProvider);

                        // Create the Lambda proxy object with a default Json data binder.
                        // You can provide your own data binder by implementing
                        // LambdaDataBinder.
                        final LambdaInterface lambdaInterface = factory.build(LambdaInterface.class);

                        RequestClass request = new RequestClass(totalPrice.toString(),
                                cardName.getText().toString(), token);
                        new AsyncTask<RequestClass, Void, ResponseClass>() {
                            @Override
                            protected ResponseClass doInBackground(RequestClass... params) {
                                try {
                                    return lambdaInterface.stripe(params[0]);
                                } catch (LambdaFunctionException lfe) {
                                    Log.e("Tag", "Failed to process charge", lfe);
                                    return null;
                                }
                            }

                            @Override
                            protected void onPostExecute(ResponseClass result) {
                                if (result == null) {
                                    return;
                                }

                                // Do a toast
                                Toast.makeText(getApplicationContext(), result.getResult(), Toast.LENGTH_LONG).show();
                            }
                        }.execute(request);
                    }

                    public void onError(Exception error) {
                        Toast.makeText(getApplicationContext(),
                                error.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );

    }


}