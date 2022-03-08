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
import androidx.fragment.app.Fragment;

public class DosageFragment extends Fragment implements AdapterView.OnItemSelectedListener  {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dosage,container,false);
        Spinner MedicineDosagesSpinner = (Spinner) view.findViewById(R.id.MedicineDosagesSpinner);
        ArrayAdapter<CharSequence> medicineDosagesAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Medicine_Dosages, android.R.layout.simple_spinner_item);
        medicineDosagesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MedicineDosagesSpinner.setAdapter(medicineDosagesAdapter);
        MedicineDosagesSpinner.setOnItemSelectedListener(this);
        return view;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
