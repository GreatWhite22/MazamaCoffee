package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mazamaLogo = (ImageView) findViewById(R.id.MazamaLogo);
        mazamaLogo.setImageResource(R.drawable.mazama_logo);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);
        setSupportActionBar(myToolbar);
    }
    /* called when Menu button is pressed */
    public void getMenu (View view){
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
    }
    /* called when Contact info button is pressed */
    public void getContactInfo (View view){
        Intent info = new Intent(this, ContactInfoActivity.class);
        startActivity(info);
    }
}
