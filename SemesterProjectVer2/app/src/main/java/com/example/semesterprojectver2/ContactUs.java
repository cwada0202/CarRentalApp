package com.example.semesterprojectver2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {
    //Toolbar declare
    Toolbar tb6;
    // declare variables
    EditText etEmail, etMsg, etCC,etSubject;
    Button btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        //TOOLBAR SETTING
        tb6 = findViewById(R.id.toolbar);
        setSupportActionBar(tb6);
        // Link the int variables to XML file
        etEmail = findViewById(R.id.et_Email);
        etCC= findViewById(R.id.et_CC);
        etSubject=findViewById(R.id.et_Subject);
        etMsg = findViewById(R.id.et_Message);
        btnSendEmail = findViewById(R.id.btn_Send);

        // action for clicking SEND EMAIL button
        btnSendEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // get user input from each plain texts
                String emails = etEmail.getText().toString();
                String cc= etCC.getText().toString();
                String subject= etSubject.getText().toString();
                String message = etMsg.getText().toString();

                // Use Intent for sending Email to user input address,
                // place user input data into each object
                Intent inn = new Intent(Intent.ACTION_SENDTO);
                //URI=uniform resource identifier
                //send an email you need to specify mailto: as URI using setData() method
                inn.setData(Uri.parse("mailto:"+emails));
                //A String[] holding e-mail addresses that should be delivered to.
                inn.putExtra(Intent.EXTRA_EMAIL,emails);
                //A String[] holding e-mail addresses that should be carbon copied.
                inn.putExtra(Intent.EXTRA_CC,cc);
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
    }
}
