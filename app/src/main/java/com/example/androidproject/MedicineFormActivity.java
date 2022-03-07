package com.example.androidproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MedicineFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Spinner medicineFormSpinner = (Spinner) findViewById(R.id.MedicineFormSpinner);
        ArrayAdapter<CharSequence> medicineFormAdapter = ArrayAdapter.createFromResource(this, R.array.Medicine_forms, android.R.layout.simple_spinner_item);

        medicineFormAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineFormSpinner.setAdapter(medicineFormAdapter);
        medicineFormSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
