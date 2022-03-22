package com.example.androidproject.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidproject.add_medicine.add_medicine_view.AddMedicine;
import com.example.androidproject.add_tracker.AddTracker_Screen;
import com.example.androidproject.add_tracker.RequestList;
import com.example.androidproject.friend_list.FriendList;
import com.example.androidproject.R;
import com.example.androidproject.home.presenter.HomePresenter;
import com.example.androidproject.home.presenter.HomePresenterInterface;
import com.example.androidproject.local_data.LocalDataBase;
import com.example.androidproject.login.loginView.LoginScreen;
import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.model.RequestModel;
import com.example.androidproject.remote_data.AddTracker;
import com.example.androidproject.remote_data.AddTrackerInterface;
import com.example.androidproject.remote_data.MedicineDAO;
import com.example.androidproject.remote_data.RemoteSource;
import com.example.androidproject.repo.ListRepository;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class Home extends AppCompatActivity implements HomeInterface , NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private MedicineListAdapter medicineListAdapter;
    private ArrayList<MedicineDose> medicineArrayList = new ArrayList<>();
    private Handler handler;
    private HomePresenterInterface presenterInterface;
    private Intent intent;
    RemoteSource remote = new MedicineDAO();
    Calendar currentDate;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    AddTrackerInterface addTracker = new AddTracker();
    LoginScreen login = new LoginScreen();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String myEmail ;
    ArrayList<RequestModel> persons  ;
    ArrayList<RequestModel> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(user!=null){
            myEmail = user.getEmail();
        }
        currentDate = Calendar.getInstance();
        intent = getIntent();
        persons = new ArrayList<>();
        persons = addTracker.returnRequestArr();
        friends = addTracker.returnFriendsArr();
        setMyCalendar(findViewById(R.id.calendar));

        setMedicineListAdapter(findViewById(R.id.MyList));

       setDrawer(findViewById(R.id.toolbar), findViewById(R.id.drawer_layout));

        NavigationView navigationView= (NavigationView) findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(this);

        presenterInterface = new HomePresenter(this, ListRepository.getInstance(this , LocalDataBase.getInstance(this)));

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                medicineListAdapter.setMedicineList(medicineArrayList);
                medicineListAdapter.notifyDataSetChanged();
            }
        };
        addTracker.reqList(myEmail);
        addTracker.friendList(myEmail);

    }
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent sendIntent = new Intent(this, LoginScreen.class);
            startActivity(sendIntent);

        }
    }

    @Override
    public void updateList(MedicineList medicineList) {
        if (medicineList != null) {
            medicineArrayList = medicineList.getMedicineDoseArrayList();

        } else {
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
        remote.retrieveData(toString(currentDate));



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

    String toString(Calendar calendar) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(calendar.getTime());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addTracker: {
                Intent sendIntent = new Intent(this, AddTracker_Screen.class);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra("SenderEmail", user.getEmail());
                sendIntent.putExtra("SenderID",user.getUid());
                startActivity(sendIntent);
                break;
            }
            case  R.id.reqList:{
                Intent reqIntent = new Intent(this, RequestList.class);
                reqIntent.putExtra("ARRAYLIST", persons);
                startActivity(reqIntent);
                break;
            }
            case  R.id.trackersList:{
                Intent friendIntent = new Intent(this, FriendList.class);
                friendIntent.putExtra("ARRAYLISTFRIENDS", friends);
                startActivity(friendIntent);
                break;
            }
            case R.id.logout:{
                SharedPreferences data = getSharedPreferences("LoginStatus", MODE_PRIVATE);

        data.edit().putBoolean("LoggedIn", false).commit();
        FirebaseAuth.getInstance().signOut();
                Toast.makeText(this,"hi ",Toast.LENGTH_SHORT).show();
                finishAffinity();
                break;
            }
        }
        return true;
    }
}