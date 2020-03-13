package com.example.semesterprojectver2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //declare toolbar variable
    Toolbar tb;

    // declare each views
    EditText etStart;
    EditText etEnd;
    static TextView tvDays;
    Button btnNext;

    // use Calendar interface to display calendar
    Calendar myCal;
    // prompt user to pick a date
    DatePickerDialog dpd;
    // tag for loggat
    private static final String TAG = "CalendarActivity";
    // set date picker for starting and ending date
    private DatePickerDialog.OnDateSetListener mStartDateSetListner;
    private DatePickerDialog.OnDateSetListener mEndDateSetListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TOOLBAR SETTING
        tb = findViewById(R.id.toolbar);
        //Set
        setSupportActionBar(tb);


        // link xml file to java file
        etStart=findViewById(R.id.et_sdate);
        etEnd=findViewById(R.id.et_edate);
        tvDays=findViewById(R.id.tv_days_rev);
        btnNext=findViewById(R.id.btn_next);

        // when user click start date, calendar set today is date and display
        etStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCal=Calendar.getInstance();
                int year = myCal.get(Calendar.YEAR);
                int month = myCal.get(Calendar.MONTH);
                int day = myCal.get(Calendar.DAY_OF_MONTH);
                dpd = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mStartDateSetListner,year,month,day);
                dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dpd.show();
            }
        });
        // for ending date
        etEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCal=Calendar.getInstance();
                int year = myCal.get(Calendar.YEAR);
                int month = myCal.get(Calendar.MONTH);
                int day = myCal.get(Calendar.DAY_OF_MONTH);
                dpd = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mEndDateSetListner,year,month,day);
                dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dpd.show();
            }
        });

        // get user selected date for starting date
        mStartDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                Log.d(TAG,"onDateSet: mm/dd/yyyy: "+month+"/"+dayOfMonth+"/"+year);
                String date = month + "/"+dayOfMonth+"/"+year;
                etStart.setText(date);
            }
        };
        // for ending date
        mEndDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                Log.d(TAG,"onDateSet: mm/dd/yyyy: "+month+"/"+dayOfMonth+"/"+year);
                String date = month + "/"+dayOfMonth+"/"+year;
                etEnd.setText(date);
            }
        };

        // after user choosing pick-up date and returning date, read user input date
        etEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etStart.length()>0 || etEnd.length()>0) {
                    String readSdate = etStart.getText().toString();
                    String readEdate = etEnd.getText().toString();
                    tvDays.setText("Rental days : " + dateDiff(readSdate, readEdate)+" days");
                }
                else
                {
                    tvDays.setText("Rental day is invalid");
                }
            }
        });

        //Action for clicking NEXT Button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // read user input date
                String readSdate = etStart.getText().toString();
                String readEdate = etEnd.getText().toString();
                // get difference between two dates
                int days=dateDiff(readSdate, readEdate);
                // set value into another class
                Intent inn = new Intent (getApplicationContext(),CarSelectionActivity.class);
                // with password
                inn.putExtra("rentaldays", days);
                // start activity
                startActivity(inn);

            }
        });


    }//onCrete ends here

    // create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // linking manu items to context
        MenuInflater inflater = getMenuInflater();
        // get which menu item from resource file and set it to inflater
        inflater.inflate(R.menu.optionmenu,menu);
        return true;
    }

    // action for each option menu is selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Switch to covert action each time by itemID
        switch (item.getItemId()){
            //When item 1 is selected it clears all views contents and radiobutton and checkbox to false
            case R.id.item1:
                Intent inn = new Intent(getApplicationContext(),FindLocation.class);
                startActivity(inn);
                return true;
            // When item2 is selected set default texts inside views and set radioButton and check box
            case R.id.item2:
                Intent inn2 = new Intent(getApplicationContext(), MediaPlayerActivity.class);
                startActivity(inn2);
                return true;
            case R.id.item3:
                Intent inn3= new Intent(getApplicationContext(),ContactUs.class);
                startActivity(inn3);
                default:
                return super.onContextItemSelected(item);

        }
    }

    // method for getting days between two different dates
    public static int dateDiff(String dateFromString, String dateToString) {
        // construtor for format date
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        // dateTo=returning date
        Date dateTo = null;
        // dateFrom=pick-up date
        Date dateFrom = null;

        try{
            // convert date to defined formatting
            dateFrom = sdf.parse(dateFromString);
            dateTo =sdf.parse(dateToString);
        }catch(java.text.ParseException e){
            e.printStackTrace();
        }

        // get each date time
        long dateTimeTo=dateTo.getTime();
        long dateTimeFrom=dateFrom.getTime();
        // calculate days of difference by time
        long dayDiff=(dateTimeTo-dateTimeFrom)/(1000*60*60*24);

        // returns answer of difference date
        return (int)dayDiff;
    }

}

