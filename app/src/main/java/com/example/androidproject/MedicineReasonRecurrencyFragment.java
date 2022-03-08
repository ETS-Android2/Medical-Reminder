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

public class MedicineReasonRecurrencyFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reason_recurrency,container,false);
        Spinner medicineRecurrencySpinner = (Spinner) view.findViewById(R.id.MedicineRecurrencySpinner);
        ArrayAdapter<CharSequence> medicineRecurrencyAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Medicine_Recurrency, android.R.layout.simple_spinner_item);
        medicineRecurrencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineRecurrencySpinner.setAdapter(medicineRecurrencyAdapter);
        medicineRecurrencySpinner.setOnItemSelectedListener(this);

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
