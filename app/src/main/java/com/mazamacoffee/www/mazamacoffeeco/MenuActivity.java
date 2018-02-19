package com.mazamacoffee.www.mazamacoffeeco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import com.mazamacoffee.www.mazamacoffeeco.Items.*;

import com.mazamacoffee.www.mazamacoffeeco.Items.Item;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.drink);
        spec.setIndicator("Drink");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.food);
        spec.setIndicator("Food");
        host.addTab(spec);


        populateListView();
        registerclick();
    }


    private void populateListView() {
        //create list of items
            String[] drinkItems = {
                    "Americano",
                    "Black Eye",
                    "Bottled Drinks",
                    "Brewed Coffee",
                    "Cafe Au Lait",
                    "Cappuccino",
                    "Chai",
                    "Cold Brew",
                    "Cortado",
                    "Espresso",
                    "Frappe",
                    "Hot Chocolate",
                    "Hot Tea",
                    "Iced Tea",
                    "Latte",
                    "Mocha",
                    "Smoothie",
                    "Spiced Cider",
                    "Steamer"
            };
            String[] foodItems = {
                    "Pumpkin Spice Bread",
                    "Banana Chocolate Chip Bread",
                    "Lemon Blueberry Tea Cake",
                    "Iced Goat Cookie",
                    "Morning Glory Muffin",
                    "Lemon Blueberry Scone",
                    "Peach Vanilla Scone"
            };

        //build adapter
        ArrayAdapter<String> dAdapter = new ArrayAdapter<String>(
                this,       //context for activity
                R.layout.menu_items, //layout to use
                drinkItems       //items to be displayed
        );
        ArrayAdapter<String> fAdapter = new ArrayAdapter<String>(
                this,
                R.layout.menu_items,
                foodItems
        );
        //configure list view
        ListView drinkList = (ListView) findViewById(R.id.drink_listview);
        drinkList.setAdapter(dAdapter);
        ListView foodList = (ListView) findViewById(R.id.food_listview);
        foodList.setAdapter(fAdapter);
    }

    private void registerclick() {
        ListView drinkList = (ListView) findViewById(R.id.drink_listview);
        ListView foodList = (ListView) findViewById(R.id.food_listview);
        drinkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textview = (TextView) viewClicked;
                String itemString = "com.mazamacoffee.www.mazamacoffeeco.Items."+textview.getText().toString().replaceAll("\\s+","");
                try {
                    Class drinkClass = Class.forName(itemString);
                    Item selection = (Item) drinkClass.newInstance();
                    Intent optionsPage = new Intent(MenuActivity.this, DetailedOptionsActivity.class);
                    optionsPage.putExtra("Item", selection);
                    optionsPage.putExtra("Purpose", "AddToCart");
                    startActivity(optionsPage);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textview = (TextView) viewClicked;
                BakedGoods selection = new BakedGoods(textview.getText().toString());
                Intent optionsPage = new Intent(MenuActivity.this, DetailedOptionsActivity.class);
                optionsPage.putExtra("Item", selection);
                optionsPage.putExtra("Purpose", "AddToCart");
                startActivity(optionsPage);
            }
        });
    }

    public void getShoppingCart (View view){
        Intent cart = new Intent(this, ShoppingCartActivity.class);
        startActivity(cart);
    }
}
