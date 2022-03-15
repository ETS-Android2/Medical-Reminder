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
    EditText total;
    EditText minToRefill;

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
        next = view.findViewById(R.id.NextRefillBtn);
        total = view.findViewById(R.id.MedicineItemEditText);
        minToRefill = view.findViewById(R.id.MedicineLimitEditText);
        next.setOnClickListener((view1) ->{
            if (total.getText().toString().matches("")) {
                Toast.makeText(view.getContext(), "Please enter the total number of items ", Toast.LENGTH_SHORT).show();
            }
            else if(minToRefill.getText().toString().matches("")){
                Toast.makeText(view.getContext(), "Please enter the limit ", Toast.LENGTH_SHORT).show();

            }else {
                communicator.setTotalItem(Integer.parseInt(total.getText().toString()));
                communicator.setRefillReminder(Integer.parseInt(minToRefill.getText().toString()));
                communicator.confirmAddingMedicine();
            }
            });
    }
}
