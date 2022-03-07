package com.example.androidproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MedicineStrengthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);
        Spinner medicineStrengthSpinner = (Spinner) findViewById(R.id.MedicineStrengthSpinner);
        ArrayAdapter<CharSequence> medicineStrengthAdapter = ArrayAdapter.createFromResource(this, R.array.Medicine_Strength, android.R.layout.simple_spinner_item);

        medicineStrengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineStrengthSpinner.setAdapter(medicineStrengthAdapter);
        medicineStrengthSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
