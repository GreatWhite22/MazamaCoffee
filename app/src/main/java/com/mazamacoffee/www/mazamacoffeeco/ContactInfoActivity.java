package com.mazamacoffee.www.mazamacoffeeco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;


public class ContactInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Intent info = getIntent();
    }
}
