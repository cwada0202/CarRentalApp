package com.example.semesterprojectver2;

import android.content.Context;
import android.icu.util.Currency;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

// Inheritance CarInventory class to this class
public class CarInventoryAdapter extends ArrayAdapter<CarInventory> {
    Context myContext;
    int myResource;
    CarInventory cl;

    public CarInventoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CarInventory> objects) {
        super(context, resource, objects);
        myContext = context;
        myResource = resource;
    }
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        /*
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        // object item based on the position
        String name=getItem(position).getCarType();
        Double desc=getItem(position).getCarDescription();
        int cid=getItem(position).getCarId();

        cl = new CarInventory(name,desc,cid);
        //
        if(convertView==null)
        {
            // inflate the layout
            LayoutInflater myInflater = LayoutInflater.from(myContext);
            convertView = myInflater.inflate(myResource,parent,false);

        }

        // get the TextView
        TextView tv_name=convertView.findViewById(R.id.tv_carType);
        ImageView iv_fruits=convertView.findViewById(R.id.iv_carImage);
        TextView tv_description=convertView.findViewById(R.id.tv_description);

        //Format constructor to convert double value into 2 decimal places
        NumberFormat REAL_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
        //set the text (item name) and tag (item ID) values
        tv_name.setText(name);
        tv_description.setText(REAL_FORMATTER.format(desc));
        iv_fruits.setImageResource(cid);


        return convertView;

    }
}
