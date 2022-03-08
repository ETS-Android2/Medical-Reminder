package com.example.androidproject.add_medicine_view.fragments;

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
import com.example.androidproject.add_medicine_view.AddMedicineFragmentsCommunicator;

public class DurationFragment extends Fragment  {
    View view;
    AddMedicineFragmentsCommunicator communicator;
    Button next;

    public DurationFragment(){}
    public DurationFragment(AddMedicineFragmentsCommunicator communicator) {
        this.communicator = communicator;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_duration,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next = view.findViewById(R.id.NextDurationBtn);
        next.setOnClickListener(view1 -> communicator.nextFragment());
    }
}
