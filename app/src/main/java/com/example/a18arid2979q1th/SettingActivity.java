package com.example.a18arid2979q1th;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    String[] ageUnit;
    String[] color;
    String Color;
    String Unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setTitle(Html.fromHtml("<font>Setting </font>"));

        ageUnit = new String[]{"Days", "Months", "Years"};
        color= new String[]{"Red", "Green", "Blue"};

        SharedPreferences preferences=getSharedPreferences("PhoneBook",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();

        Spinner spin = (Spinner) findViewById(R.id.unitSpinner);
        Button saveBtn=(Button) findViewById(R.id.saveData);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Unit=ageUnit[position];
                editor.putString("unit",Unit);
                Toast.makeText(getApplicationContext(),ageUnit[position] , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ageUnit);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        Spinner spinner = (Spinner) findViewById(R.id.colorSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Color=color[position];
                editor.putString("color",Color);
                Toast.makeText(getApplicationContext(),color[position] , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter ca = new ArrayAdapter(this,android.R.layout.simple_spinner_item,color);
        ca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(ca);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               editor.apply();
                Toast.makeText(getApplicationContext(),"Changes Saved" , Toast.LENGTH_LONG).show();
            }
        });

    }
}