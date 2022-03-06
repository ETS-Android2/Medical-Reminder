package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    HashMap<String,ArrayList<String>> map = new HashMap<>();
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMyCalendar(findViewById(R.id.calendar));

        fab = findViewById(R.id.fab);

        listView = findViewById(R.id.MyList);



    }

   public void addMedicine(View view){

       Intent intent = new Intent(this , AddMedicine.class);
       startActivity(intent);

    }





    void setMyCalendar(View myCalendar){

        HorizontalCalendarView calendarView = (HorizontalCalendarView) myCalendar;

        Calendar starttime = Calendar.getInstance();
        starttime.add(Calendar.MONTH,-6);

        Calendar endtime = Calendar.getInstance();
        endtime.add(Calendar.MONTH,6);

        ArrayList datesToBeColored = new ArrayList();
        datesToBeColored.add(Tools.getFormattedDateToday());

        calendarView.setUpCalendar(starttime.getTimeInMillis(),
                endtime.getTimeInMillis(),
                datesToBeColored,
                new HorizontalCalendarView.OnCalendarListener() {
                    @Override
                    public void onDateSelected(String date) {
                        dateSelected(date);
                    }
                });
    }


    void dateSelected(String date){

        if (!map.containsKey(date)) {
            ArrayList<String> list = new ArrayList<>();

            list.add(date);

            map.put(date, list);
        }

        ArrayList<String> list1 = map.get(date);


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list1);

        listView.setAdapter(arrayAdapter);

        arrayAdapter.notifyDataSetChanged();

    }


}