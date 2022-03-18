package com.example.androidproject.add_medicine.add_medicine_view.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicineFragmentsCommunicator;

import java.util.ArrayList;
import java.util.Calendar;

public class DosageFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View view;


    static AddMedicineFragmentsCommunicator communicator;
    static ArrayList<int[]> doseTime = new ArrayList<>();
    static int dose;
    static EditText editText;

    Button next;

    public DosageFragment() {
    }

    public DosageFragment(AddMedicineFragmentsCommunicator communicator) {
        this.communicator = communicator;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dosage, container, false);
        Spinner MedicineDosagesSpinner = (Spinner) view.findViewById(R.id.MedicineDosagesSpinner);
        ArrayAdapter<CharSequence> medicineDosagesAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Medicine_Dosages, android.R.layout.simple_spinner_item);
        medicineDosagesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MedicineDosagesSpinner.setAdapter(medicineDosagesAdapter);
        MedicineDosagesSpinner.setOnItemSelectedListener(this);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next = view.findViewById(R.id.NextDosagesBtn);
        next.setOnClickListener(view1 -> {

            String s = editText.getText().toString().trim();
            if (s.length() > 0)
                dose = Integer.parseInt(s);
            if (dose > 0) {
                showDialog();
            }
        });

         editText = view.findViewById(R.id.MedicineDosagesEditText);



    }

    void showDialog(){
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public static void onTimeSelected(TimePicker view, int hourOfDay, int minute) {

            int[] x = new int[2];
            x[0] = hourOfDay;
            x[1] = minute;
            doseTime.add(x);

        if (doseTime.size()==dose){
            communicator.setDoseTime(doseTime);
            communicator.setDosagesPerTime(Integer.parseInt(editText.getText().toString().trim()));
            communicator.nextFragment();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            onTimeSelected(view, hourOfDay, minute);
        }
    }
}
