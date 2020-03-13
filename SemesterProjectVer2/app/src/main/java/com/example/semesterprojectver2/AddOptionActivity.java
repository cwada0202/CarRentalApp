package com.example.semesterprojectver2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.channels.GatheringByteChannel;
import java.text.NumberFormat;
import java.util.Locale;

public class AddOptionActivity extends AppCompatActivity {
    // Get name of selected radio group
    // radio group has two radio buttons: rb_male, rb_female
    private String getRadioButton(){
        // get radiogroup view id
        RadioGroup rg=findViewById(R.id.rdg_Insurance);
        // get position of radio group and return corresponding text
        return ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
    }
    // get integer of selected insurance type
    private int getInsurance()
    {
        //link id from xml file to java file
        RadioButton rb100=findViewById(R.id.rb_Insurance100);
        RadioButton rb50=findViewById(R.id.rb_Insurance50);
        // get each text as string
        String ins100 = rb100.getText().toString();
        String ins50 = rb50.getText().toString();
        int ins;
        //when user pick full coverage
        if(getRadioButton()==ins100)
        {
            ins=50;
        }
        // when user pick half coverage
        else if(getRadioButton()==ins50)
        {
            ins=25;
        }
        // none
        else
            ins=0;
            return ins;
    }

   // Get value of checkbox
    private double getGPSCheckBox(){
        CheckBox ch= findViewById(R.id.cb_gps);
        //Use if statement to find checkbox is on or off
        // return string "11.99" when is on, "0" when is off
        if (ch.isChecked())
            return 11.99;
        else
            return 0.00;
    }
    // Get value of checkbox
    private double getSateliteCheckBox(){
        CheckBox ch = findViewById(R.id.cb_sateRadio);
        //Use if statement to find checkbox is on or off
        // return string "5.99" when is on, "0" when is off
        if (ch.isChecked())
            return 5.99;
        else
            return 0.00;
    }
    // Get value of checkbox
    private double getChildSeatCheckBox(){
        CheckBox ch = findViewById(R.id.cb_infantSeat);
        //Use if statement to find checkbox is on or off
        // return string "5.99" when is on, "0" when is off
        if (ch.isChecked())
            return 5.99;
        else
            return 0.00;
    }
    // declare text view variables
    TextView tvRentalDays;
    TextView tvRentalCar;
    Button btnNext;

    //TOOLBAE SETTING
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_option);

        //TOOLBAR SETTING
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        //get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        //enable the up button
        ab.setDisplayHomeAsUpEnabled(true);

        //Sets where to display contextMenu
        //Register contextMenu to fg_mail textView->have to set on views
        registerForContextMenu(findViewById(R.id.fg_option));

        // link xml file to java file
        tvRentalDays=findViewById(R.id.tv_rentalDays_opt);
        tvRentalCar=findViewById(R.id.tv_rentalCar_opt);
        //get Intent from Main activity
        Intent receiverInn= getIntent();
        // get String value from Intent
        final Integer days=receiverInn.getIntExtra("rentaldays",0);
        final Double carPrice=receiverInn.getDoubleExtra("rentalcar",0);
        // set on declared text view
        tvRentalDays.setText("Rental days: "+days+" days");
        //NumberFormat REAL_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
        tvRentalCar.setText("Rental Price: $"+carPrice+"/day");


        //link view vriables to xml file
        btnNext=findViewById(R.id.btn_next_opt);
        // action for clicking btn_Next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get total amount of options
                Double option=GetOptionPrice(getInsurance(),getGPSCheckBox(),getSateliteCheckBox());
                // calculate total amount of rentals
                Double ttlAmount = ((carPrice+option)*days)+getChildSeatCheckBox();
                // send totalamount of rentals to next activity
                Intent inn = new Intent(getApplicationContext(),RegisterActivity.class);
                // with password
                inn.putExtra("ttlAmount",ttlAmount);
                // start new activity
                startActivity(inn);
            }
        });


    }//onCrete ends here
    //override a method to Create ContextMenu that defined in comtextmenu.xml in menu layou
    @Override
    public void onCreateContextMenu(ContextMenu m, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // return true
        super.onCreateContextMenu(m, v, menuInfo);
        // getMenuInflater from contextMenu.xml
        getMenuInflater().inflate(R.menu.optionmenu,m);
        // creating contextMenu title
        m.setHeaderTitle("ContextMenu");
    }
    //override a method to define action when each menu item is selected
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // Switch to covert action each time by itemID
        switch (item.getItemId()) {
            //When item 1 is selected go to google map to display store location
            case R.id.item1:
                Intent inn = new Intent(getApplicationContext(), FindLocation.class);
                startActivity(inn);
                return true;
            // When item2 is selected go to video activity
            case R.id.item2:
                Intent inn2 = new Intent(getApplicationContext(), MediaPlayerActivity.class);
                startActivity(inn2);
                return true;
                // when item3 is selected to to contact information to send email
            case R.id.item3:
                Intent inn3 = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(inn3);
            default:
                return super.onContextItemSelected(item);

        }
    }
    // method for calculation option price
    public double GetOptionPrice ( int ins, double gps, double satellite){

        Double price = ins + gps + satellite;

        return price;
    }

}



