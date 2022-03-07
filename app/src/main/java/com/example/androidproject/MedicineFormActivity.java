package com.example.androidproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MedicineFormActivity extends Fragment implements AdapterView.OnItemSelectedListener {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner medicineFormSpinner = (Spinner) view.findViewById(R.id.MedicineFormSpinner);
        ArrayAdapter<CharSequence> medicineFormAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.Medicine_forms, android.R.layout.simple_spinner_item);

        medicineFormAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineFormSpinner.setAdapter(medicineFormAdapter);
        medicineFormSpinner.setOnItemSelectedListener(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_form, container, false);
        return view;    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
