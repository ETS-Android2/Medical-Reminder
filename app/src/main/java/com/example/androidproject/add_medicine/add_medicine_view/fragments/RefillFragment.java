package com.example.androidproject.add_medicine.add_medicine_view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicineFragmentsCommunicator;

public class RefillFragment  extends Fragment {

    AddMedicineFragmentsCommunicator communicator;
    Button next;
    EditText reminderEditText;
    EditText itemsEditText;

    public RefillFragment(){}
    public RefillFragment(AddMedicineFragmentsCommunicator communicator) {
        this.communicator = communicator;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemsEditText = view.findViewById(R.id.MedicineItemEditText);
        reminderEditText = view.findViewById(R.id.MedicineLimitEditText);
        next = view.findViewById(R.id.NextRefillBtn);
        next.setOnClickListener(view1 -> {
            int i = Integer.parseInt(itemsEditText.getText().toString());
            int l = Integer.parseInt(reminderEditText.getText().toString());
            if (i>l && l>=0) {
                communicator.setRecurrence(i);
                communicator.setRefillReminder(l);
                communicator.nextFragment();
            }else {
                Toast.makeText(getContext(), "enter valid values", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
