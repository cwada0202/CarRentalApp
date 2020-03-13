package com.example.semesterprojectver2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    //TOOLBAE SETTING
    Toolbar tb;

    //Declare button as mButton
    TextView tvTtlPrice;
    Button btnRent;
    EditText etName;
    EditText etPhoneNum;
    TextView tvDesc;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName=findViewById(R.id.et_Name);
        etPhoneNum=findViewById(R.id.et_PhoneNum);
        tvDesc=findViewById(R.id.tv_emailDesc);
        etEmail=findViewById(R.id.et_Email);

        //TOOLBAE SETTING
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        //Sets where to display contextMenu
        //Register contextMenu to fg_mail textView->have to set on views
        registerForContextMenu(findViewById(R.id.fg_Rev2));


        // Link to tv_fgTitle to xml
        tvTtlPrice = (TextView) findViewById(R.id.tv_totalprice_rev);
        btnRent=findViewById(R.id.btn_rent);

        // get Intent from Main activity
        Intent receiverInn = getIntent();
        // get String value from Intent
        final Double totalPrice=receiverInn.getDoubleExtra("ttlAmount",0);
        // set on declared text view
        NumberFormat REAL_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
        // set total price on ttlPrice text view
        tvTtlPrice.setText("Your total is : " + (REAL_FORMATTER.format(totalPrice)));
        // set text on tvDesc text view
        tvDesc.setText("Your order will be comopleted by sending email");

        //Action for clicking rent button
        // sending email
        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get user input from each plain texts
                String emails = etEmail.getText().toString();
                String subject= "Your rental car receipt";
                String name=etName.getText().toString();
                String phone=etPhoneNum.getText().toString();
                String ttlPrice=tvTtlPrice.getText().toString();
                String message = "Dear "+name+",\nThank you for using us.\nYour total rental price is "
                        +ttlPrice+"\nYour name: "+name+"\nYour phone number: "+phone;

                // Use Intent for sending Email to user input address,
                // place user input data into each object
                Intent inn = new Intent(Intent.ACTION_SENDTO);
                //URI=uniform resource identifier
                //send an email you need to specify mailto: as URI using setData() method
                inn.setData(Uri.parse("mailto:"+emails));
                //A String[] holding e-mail addresses that should be delivered to.
                inn.putExtra(Intent.EXTRA_EMAIL,emails);
                //A constant string holding the desired subject line of a message.
                inn.putExtra(Intent.EXTRA_SUBJECT,subject);
                //A constant CharSequence that is associated with the Intent, used with ACTION_SEND to supply the literal data to be sent.
                inn.putExtra(Intent.EXTRA_TEXT,message);

                // code for share information within different application
                if(inn.resolveActivity(getPackageManager())!=null){
                    startActivity(inn);
                }
            }
        });

    }// onCreate ends here
    //override a method to Create ContextMenu that defined in comtextmenu.xml in menu layout
    @Override
    public void onCreateContextMenu(ContextMenu m, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //return true
        super.onCreateContextMenu(m, v, menuInfo);
        // getMenuInflater from contextMenu.xml
        getMenuInflater().inflate(R.menu.optionmenu,m);
        // creating contextMenu title
        m.setHeaderTitle("ContextMenu");
    }

    // Action when contextMenu is selected
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // find views
        etName=findViewById(R.id.et_Name);
        etPhoneNum=findViewById(R.id.et_PhoneNum);

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
        }
        return super.onContextItemSelected(item);
    }

}

