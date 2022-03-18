package com.example.androidproject.add_medicine.add_medicine_view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.window.SplashScreen;

import com.example.androidproject.R;
import com.example.androidproject.SplashActivity;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicineFragmentsCommunicator;
import com.example.androidproject.model.Medicine;


public class AddMedicineNameFragment extends Fragment implements AdapterView.OnItemSelectedListener {

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

        AutoCompleteTextView medAutoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.MedicineAddNameEditText);
        ArrayAdapter<String> medArrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.auto_complete_list_items, SplashActivity.medicines);
        medAutoCompleteTextView.setThreshold(1);
        medAutoCompleteTextView.setAdapter(medArrayAdapter);


        name = view.findViewById(R.id.MedicineAddNameEditText);

        next = view.findViewById(R.id.NextAddNameBtn);
        next.setOnClickListener(view1 -> {
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