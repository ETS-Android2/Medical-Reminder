package com.example.androidproject.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.androidproject.SplashActivity;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicine;
import com.example.androidproject.local_data.LocalList;
import com.example.androidproject.model.Medicine;
import com.example.androidproject.R;
import com.example.androidproject.home.presenter.HomePresenter;
import com.example.androidproject.home.presenter.HomePresenterInterface;
import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.repo.ListRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class Home extends AppCompatActivity implements HomeInterface {

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private MedicineListAdapter medicineListAdapter;
    private ArrayList<MedicineDose> medicineArrayList = new ArrayList<>();
    private Handler handler;
    private HomePresenterInterface presenterInterface;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();

        setMyCalendar(findViewById(R.id.calendar));

        setMedicineListAdapter(findViewById(R.id.MyList));

        setDrawer(findViewById(R.id.toolbar), findViewById(R.id.drawer_layout));

        presenterInterface = new HomePresenter(this, ListRepository.getInstance(this , LocalList.getInstance(this)));

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                medicineListAdapter.setMedicineList(medicineArrayList);
                medicineListAdapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    public void updateList(MedicineList medicineList) {
        if (medicineList != null) {
            medicineArrayList = medicineList.getMedicineDoseArrayList();

        }else{
            medicineArrayList = new ArrayList<>();
        }

        handler.sendEmptyMessage(0);
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
        presenterInterface.getMedicineList(date);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}