package com.example.androidproject.add_medicine.add_medicine_view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicineFragmentsCommunicator;
import com.example.androidproject.model.Medicine;


public class AddMedicineNameFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    AddMedicineFragmentsCommunicator communicator;
    Button next;
    EditText name;


    public AddMedicineNameFragment() {
        // Required empty public constructor
    }
    public AddMedicineNameFragment(AddMedicineFragmentsCommunicator communicator) {
        this.communicator = communicator;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_medicine_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        next = view.findViewById(R.id.NextAddNameBtn);
        name = view.findViewById(R.id.MedicineAddNameEditText);
        next.setOnClickListener((view1)-> {
            communicator.setName(name.getText().toString());
            communicator.nextFragment();
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}