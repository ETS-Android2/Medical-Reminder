package com.example.androidproject.add_medicine.add_medicine_view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicineFragmentsCommunicator;

public class MedicineStrengthFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    View view;
    AddMedicineFragmentsCommunicator communicator;
    Button next;
    EditText strength;
    String unit;
    public MedicineStrengthFragment(){}
    public MedicineStrengthFragment(AddMedicineFragmentsCommunicator communicator) {
        this.communicator = communicator;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_strength,container,false);
        Spinner medicineStrengthSpinner = (Spinner) view.findViewById(R.id.MedicineStrengthSpinner);
        ArrayAdapter<CharSequence> medicineStrengthAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Medicine_Strength, android.R.layout.simple_spinner_item);
        medicineStrengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineStrengthSpinner.setAdapter(medicineStrengthAdapter);
        medicineStrengthSpinner.setOnItemSelectedListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next = view.findViewById(R.id.NextStrengthBtn);
        strength = view.findViewById(R.id.MedicineStrengthEditText);
        next.setOnClickListener((view1) -> {
            if (strength.getText().toString().matches("")){
                Toast.makeText(view.getContext(), "Please enter the medicine strength", Toast.LENGTH_SHORT).show();

            }else {
                communicator.setMedicineStrength(Integer.parseInt(strength.getText().toString()));
                communicator.setMedicineStrengthUnit(unit);
                communicator.nextFragment();
            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        unit = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
