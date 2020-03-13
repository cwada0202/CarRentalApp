package com.example.semesterprojectver2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.NumberFormat;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

//Inheritance Fragment class
public class MainFragment extends Fragment {

    //Declare button as mButton
    private Button mButton;

    //method to create View which displays on fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //Convert the convert inside fragment layout as View
        return inflater.inflate(R.layout.fragment_main,container,false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Linkt tv_fgTitle to xml
        mButton=(Button)view.findViewById(R.id.btn_submit);
        // Action when btn_change has been clicked
        //set onClicklister to go back Main activity
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(getContext(), MainActivity.class);
                startActivity(inn);

            }
        });

    }

}
