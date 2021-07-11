package com.example.a18arid2979q1th;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    DatePicker picker;
    int totalDays;
    int totalMonths;
    int totalYears;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(Html.fromHtml("<font>Age Calculator </font>"));
        DetailFragment details = new DetailFragment();

        Button calBtn=(Button) findViewById(R.id.calculate);
        picker=(DatePicker)findViewById(R.id.datePicker1);

        calBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int sDay=picker.getDayOfMonth();
                int sMonth=picker.getMonth();
                int sYear=picker.getYear();

                LocalDate pdate = LocalDate.of(sYear,sMonth,sDay);
                LocalDate now = LocalDate.now();

                Period diff = Period.between(pdate, now);

                totalMonths=diff.getMonths();
                totalYears=diff.getYears();
                totalDays=totalYears/365;

                Toast.makeText(getApplicationContext(),String.valueOf(pdate),Toast.LENGTH_LONG).show();
                SharedPreferences preferences=getSharedPreferences("PhoneBook",MODE_PRIVATE);
                String Unit = preferences.getString("unit","");
                String Color = preferences.getString("color","");
                Bundle bundle = new Bundle();
                bundle.putString("unit", Unit);
                if(Unit.equals("Days")){
                    bundle.putInt("days", totalDays);
                }else if(Unit.equals("Months")){
                    bundle.putInt("months", totalMonths);
                }else{
                    bundle.putInt("years", totalYears);
                }
                bundle.putString("color",Color);
                details.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.main_frame, details);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Intent i = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}