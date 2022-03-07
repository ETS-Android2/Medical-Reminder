package com.example.androidproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MedicineReasonRecurrencyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason_recurrency);
        Spinner medicineRecurrencySpinner = (Spinner) findViewById(R.id.MedicineRecurrencySpinner);
        ArrayAdapter<CharSequence> medicineRecurrencyAdapter = ArrayAdapter.createFromResource(this, R.array.Medicine_Recurrency, android.R.layout.simple_spinner_item);
        medicineRecurrencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineRecurrencySpinner.setAdapter(medicineRecurrencyAdapter);
        medicineRecurrencySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
