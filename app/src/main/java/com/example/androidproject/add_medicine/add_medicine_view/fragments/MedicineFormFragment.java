package com.example.androidproject.add_medicine.add_medicine_view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicineFragmentsCommunicator;

public class MedicineFormFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    AddMedicineFragmentsCommunicator communicator;
    Button next;
    String SelectedForm;

    public MedicineFormFragment(){}
    public MedicineFormFragment(AddMedicineFragmentsCommunicator communicator) {
        this.communicator = communicator;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        next = view.findViewById(R.id.Next2Btn);
        next.setOnClickListener((view1)->
                { communicator.setMedicineForm(SelectedForm);
                communicator.nextFragment();
                });

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
        SelectedForm = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
