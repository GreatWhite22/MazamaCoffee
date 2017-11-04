package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.Stripe;
import com.stripe.android.model.Token;

//import static java.security.AccessController.getContext;


public class PaymentActivity extends AppCompatActivity {

    EditText cardName;
    EditText cardNumber;
    EditText cardCVC;
    EditText cardExpMonth;
    EditText cardExpYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        buildPrice();
        cardName = (EditText) findViewById(R.id.cardName);
        cardNumber = (EditText) findViewById(R.id.cardNumber);
        cardCVC = (EditText) findViewById(R.id.cardCVC);
        cardExpMonth = (EditText) findViewById(R.id.cardExpMonth);
        cardExpYear = (EditText) findViewById(R.id.cardExpYear);
    }



    public void paymentSubmit(View view) {
        Card card = new Card(
                cardNumber.getText().toString(),
                Integer.parseInt(cardExpMonth.getText().toString()),
                Integer.parseInt(cardExpYear.getText().toString()),
                cardCVC.getText().toString()
        );

        card.validateNumber();
        card.validateCVC();
        if(!card.validateCard()){
            //TODO: Add Error message for when card is not valid
        }

       Context mContext = view.getContext();

        Stripe stripe = new Stripe(mContext, "pk_test_irEAw0mlXyAIwo6XFQRlBpOi");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                    }
                    public void onError(Exception error) {
                        Toast.makeText(getContext(),
                                error.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    private void buildPrice(){
        Intent intent = getIntent();
        double price = intent.getDoubleExtra("Charge", 0.0);
        TextView priceField = (TextView) findViewById(R.id.textView7);
        StringBuilder subtotal = new StringBuilder(getResources().getString(R.string.payment_total));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.append(" ");
        subtotal.append(formatter.format(price));
        priceField.setText(subtotal);
    }
}