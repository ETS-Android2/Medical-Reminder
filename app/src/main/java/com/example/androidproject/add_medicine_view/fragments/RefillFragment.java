package com.example.androidproject.add_medicine_view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;
import com.example.androidproject.add_medicine_view.AddMedicineFragmentsCommunicator;

public class RefillFragment  extends Fragment {

    AddMedicineFragmentsCommunicator communicator;
    Button next;

    public RefillFragment(){}
    public RefillFragment(AddMedicineFragmentsCommunicator communicator) {
        this.communicator = communicator;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next = view.findViewById(R.id.NextRefillBtn);
        next.setOnClickListener(view1 -> communicator.nextFragment());
    }
}
