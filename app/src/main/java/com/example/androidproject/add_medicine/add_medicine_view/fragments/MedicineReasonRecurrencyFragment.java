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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicineFragmentsCommunicator;

public class MedicineReasonRecurrencyFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    View view;
    AddMedicineFragmentsCommunicator communicator;
    Button next;
    EditText reason;
    String recurrency;

    public MedicineReasonRecurrencyFragment(){}
    public MedicineReasonRecurrencyFragment(AddMedicineFragmentsCommunicator communicator) {
        this.communicator = communicator;

    }

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next = view.findViewById(R.id.NextReasonRecurrencyBtn);
        reason = view.findViewById(R.id.MedicineReasonEditText);

        next.setOnClickListener((view1) ->
        {
            communicator.setRecurrenceOfTakingDrug(recurrency);
            communicator.setReasonOfTakingDrug(reason.getText().toString());
            communicator.nextFragment();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        recurrency = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
