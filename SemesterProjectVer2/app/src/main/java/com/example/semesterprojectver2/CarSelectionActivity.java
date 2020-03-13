package com.example.semesterprojectver2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CarSelectionActivity extends AppCompatActivity {
    //Toolbar declare
    Toolbar tb2;

    // declare each view in xml file
    TextView tvRdays;
    ListView lv;
    ArrayList<CarInventory> cList;
    CarInventoryAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selection);
        // get the TextView
        tvRdays=findViewById(R.id.tv_rentalDays);

        //TOOLBAR SETTING
        tb2 = findViewById(R.id.toolbar);
        setSupportActionBar(tb2);
        //get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        //enable the up button
        ab.setDisplayHomeAsUpEnabled(true);

        // get Intent from Main activity
        Intent receiverInn= getIntent();
        // get String value from Intent
        final Integer days=receiverInn.getIntExtra("rentaldays",0);
        // set on declared text view
        tvRdays.setText("Rental days: "+days+" days");

        //get the ListView
        lv=(ListView) findViewById(R.id.lv_cars);

        //Constructor for creating new list
        cList = new ArrayList<>();
        //define each item in order of defined carInventory
        cList.add(new CarInventory("Compact", 50.00, R.drawable.compact));
        cList.add(new CarInventory("Sedan", 55.00, R.drawable.sedan));
        cList.add(new CarInventory("SUV", 70.00, R.drawable.suv));
        cList.add(new CarInventory("Pickup Truck", 90.00, R.drawable.pickuptruck));
        // adapter instance
        myAdapter=new CarInventoryAdapter(getApplication(), R.layout.carlistlayout,cList);
        // setAdapter
        lv.setAdapter(myAdapter);
        //Action when item is clicked-> go to option activity
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                //get position of selected item
                CarInventory selItem =(CarInventory)lv.getItemAtPosition(p);
                //get price of selected item
                Double carPrice=selItem.getCarDescription();
                //Use intent to send value to next activity
                Intent inn = new Intent(getApplicationContext(),AddOptionActivity.class);
                // password for each values
                inn.putExtra("rentaldays", days);
                inn.putExtra("rentalcar",carPrice);
                // start new activity
                startActivity(inn);
            }
        });
    }
}
