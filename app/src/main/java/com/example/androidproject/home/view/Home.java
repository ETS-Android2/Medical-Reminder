package com.example.androidproject.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.androidproject.add_medicine.add_medicine_view.AddMedicine;
import com.example.androidproject.local_data.LocalDataBase;
import com.example.androidproject.R;
import com.example.androidproject.home.presenter.HomePresenter;
import com.example.androidproject.home.presenter.HomePresenterInterface;
import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.repo.ListRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class Home extends AppCompatActivity implements HomeInterface {

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private MedicineListAdapter medicineListAdapter;
    private ArrayList<MedicineDose> medicineArrayList = new ArrayList<>();
    private Handler handler;
    private HomePresenterInterface presenterInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Notifications.showNotifications(this);

        checkOverlayPermission();
        checkStoragePermission();

        setMyCalendar(findViewById(R.id.calendar));

        setMedicineListAdapter(findViewById(R.id.MyList));

        setDrawer(findViewById(R.id.toolbar), findViewById(R.id.drawer_layout));

        presenterInterface = new HomePresenter(this, ListRepository.getInstance(this , LocalDataBase.getInstance(this)));

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                medicineListAdapter.setMedicineList(medicineArrayList);
                medicineListAdapter.notifyDataSetChanged();
            }
        };
        Calendar today = Calendar.getInstance();
        presenterInterface.getMedicineList(new SimpleDateFormat("dd-MM-yyyy").format(today.getTime()));

        Log.i("TAG", "onCreate: Finished ");


        PeriodicWorkRequest periodicWork =
                new PeriodicWorkRequest.Builder(MyPeriodicWorker.class,20, TimeUnit.MINUTES )
                        .addTag("periodicWork")
                        .build();
        WorkManager workManager = WorkManager.getInstance();
        workManager.cancelAllWorkByTag("periodicWork");
        workManager.enqueue(periodicWork);



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

        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.MONTH, -6);

        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.MONTH, 6);

        ArrayList datesToBeColored = new ArrayList();
        datesToBeColored.add(Tools.getFormattedDateToday());

        calendarView.setUpCalendar(startTime.getTimeInMillis(),
                endTime.getTimeInMillis(),
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

    public void checkOverlayPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // send user to the device settings
                Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(myIntent);
            }
        }
    }

    public void checkStoragePermission(){

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED|| checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},5);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }
    }

}