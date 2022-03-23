package com.example.androidproject.drug_screen.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.androidproject.drug_screen.presenter.DrugScreenPresenter;
import com.example.androidproject.drug_screen.presenter.DrugScreenPresenterInterface;
import com.example.androidproject.home.presenter.HomePresenter;
import com.example.androidproject.home.presenter.HomePresenterInterface;
import com.example.androidproject.local_data.LocalDataBase;
import com.example.androidproject.model.Medicine;
import com.example.androidproject.repo.ListRepository;

import java.util.ArrayList;


public class DrugScreenActivity extends AppCompatActivity implements DrugScreenInterface {

    DrugScreenPresenterInterface presenterInterface ;
    Medicine medicine;
    Handler handler;
    TextView nameTextView;
    TextView amountTextView;
    ListView timesListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_screen);

        presenterInterface = new DrugScreenPresenter(this, ListRepository.getInstance(this , LocalDataBase.getInstance(this)));

        nameTextView = findViewById(R.id.drugItemNameextView);
        amountTextView = findViewById(R.id.drugAmountTextView);
        timesListView = findViewById(R.id.reminderDruglistView);

        presenterInterface.getMedicine(getIntent().getStringExtra("MedicineName"));



        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                nameTextView.setText(medicine.getName());
                amountTextView.setText(medicine.getRecurrence()+"");
                ArrayList<String> list = new ArrayList<>();
                for (int[] x : medicine.getDoseTime()){
                    list.add(" "+x[0]+" : "+x[1]);
                }
                ArrayAdapter<String> adapter =   new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                timesListView.setAdapter(adapter);
            }
        };

    }

    @Override
    public void showMedicine(Medicine medicine) {
        this.medicine = medicine;
        Log.i("TAG", "showMedicine: ");
        handler.sendEmptyMessage(0);
    }
}