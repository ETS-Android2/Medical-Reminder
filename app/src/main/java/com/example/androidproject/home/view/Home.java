package com.example.androidproject.home.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicine;
import com.example.androidproject.home.presenter.HomePresenter;
import com.example.androidproject.home.presenter.HomePresenterInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class Home extends AppCompatActivity implements HomeInterface {

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private MedicineListAdapter medicineListAdapter;
    private ArrayList<Medicine> medicineArrayList = new ArrayList<>();
    private HashMap<String, ArrayList<Medicine>> map = new HashMap<>();
    private HomePresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMyCalendar(findViewById(R.id.calendar));

        setMedicineListAdapter(findViewById(R.id.MyList));

        setDrawer(findViewById(R.id.toolbar), findViewById(R.id.drawer_layout));

        presenterInterface = new HomePresenter(this);
        presenterInterface.getMedicineList();

    }

    @Override
    public void updateMedicineList(HashMap<String, ArrayList<Medicine>> map) {
        this.map = map;
    }

    private void setMedicineListAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        medicineListAdapter = new MedicineListAdapter(medicineArrayList);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(medicineListAdapter);

    }

    private void updateListAdapter(ArrayList<Medicine> list){
        medicineListAdapter.setMedicineList(list);
        medicineListAdapter.notifyDataSetChanged();
    }

    private void setDrawer(Toolbar bar, DrawerLayout drawer) {


        Toolbar toolbar = bar;
        DrawerLayout drawerLayout = drawer;
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
                //drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                //drawerOpened = true;
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();


    }

    public void addMedicine(View view) {

        Medicine medicine = new Medicine();
        medicine.setName("name ");
        medicine.setDosagesPerTime(3);
        medicine.setMedicineStrength(555);
        medicineArrayList.add(medicine);
        medicineListAdapter.notifyDataSetChanged();

        Intent i = new Intent(this, AddMedicine.class);
        Toast.makeText(this, "new Med Added ", Toast.LENGTH_SHORT).show();

        startActivity(i);


    }

    private void setMyCalendar(View myCalendar) {

        HorizontalCalendarView calendarView = (HorizontalCalendarView) myCalendar;

        Calendar starttime = Calendar.getInstance();
        starttime.add(Calendar.MONTH, -6);

        Calendar endtime = Calendar.getInstance();
        endtime.add(Calendar.MONTH, 6);

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

    private void dateSelected(String date) {
        Toast.makeText(this, "" + date, Toast.LENGTH_SHORT).show();
        medicineListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}