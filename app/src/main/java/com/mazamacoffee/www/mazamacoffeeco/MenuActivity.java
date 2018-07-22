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

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {

    final static String [] drinkItems;
    final static String[] foodItems;
    ListView drinkList = (ListView) findViewById(R.id.drink_listview);
    ListView foodList = (ListView) findViewById(R.id.food_listview);

    static {
        foodItems = new String[]{"Pumpkin Spice Bread", "Banana Chocolate Chip Bread",
                "Lemon Blueberry Tea Cake", "Iced Goat Cookie", "Morning Glory Muffin",
                "Lemon Blueberry Scone", "Peach Vanilla Scone"
        };
        drinkItems = new String[]{"Americano", "Black Eye", "Bottled Drinks", "Brewed Coffee",
                "Cafe Au Lait", "Cappuccino", "Chai", "Cold Brew", "Cortado", "Espresso", "Frappe",
                "Hot Chocolate", "Hot Tea", "Iced Tea", "Latte", "Mocha", "Smoothie", "Spiced Cider", "Steamer"
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        createTabs(host);
        populateListView();
        registerclick();
    }

    private void createTabs(TabHost host) {
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.drink);
        spec.setIndicator("Drink");
        host.addTab(spec);

        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.food);
        spec.setIndicator("Food");
        host.addTab(spec);
    }


    private void populateListView() {
        ArrayAdapter<String> drinkAdapter = new ArrayAdapter<String>(this, R.layout.menu_items,
                drinkItems
        );
        ArrayAdapter<String> foodAdapter = new ArrayAdapter<String>(this,R.layout.menu_items,
                foodItems
        );
        drinkList.setAdapter(drinkAdapter);
        foodList.setAdapter(foodAdapter);
    }

    private void registerclick() {
        setupDrinkListListener(drinkList);
        setupFoodListListener(foodList);
    }

     private void setupDrinkListListener(ListView drinkList) {
        drinkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textview = (TextView) viewClicked;
                String itemString = "com.mazamacoffee.www.mazamacoffeeco.Items."+textview.getText().toString().replaceAll("\\s+","");
                try {
                    Class drinkClass = Class.forName(itemString);
                    Item drinkSelected = (Item) drinkClass.newInstance();
                    Intent ItemOptionsActivity = new Intent(MenuActivity.this, ItemOptionsActivity.class);
                    ItemOptionsActivity.putExtra("Item", drinkSelected);
                    ItemOptionsActivity.putExtra("Purpose", "AddToCart");
                    startActivity(ItemOptionsActivity);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void setupFoodListListener(ListView foodList) {
        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textview = (TextView) viewClicked;
                BakedGoods bakedGoodSelected = new BakedGoods(textview.getText().toString());
                Intent itemOptionsActivity = new Intent(MenuActivity.this, ItemOptionsActivity.class);
                itemOptionsActivity.putExtra("Item", bakedGoodSelected);
                itemOptionsActivity.putExtra("Purpose", "AddToCart");
                startActivity(itemOptionsActivity);
            }
        });
    }
}
