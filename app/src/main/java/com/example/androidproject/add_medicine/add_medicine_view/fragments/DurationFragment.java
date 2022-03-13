package com.example.androidproject.add_medicine.add_medicine_view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicineFragmentsCommunicator;

import java.util.Calendar;

public class DurationFragment extends Fragment  {
    View view;
    static AddMedicineFragmentsCommunicator communicator;
    Button next;
    static String startDate = null;
    static String endDate = null;



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
        next.setOnClickListener(view1 -> showDialog() );

        showDialog();

    }

    void showDialog(){

        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

    static void onDateSelected(DatePicker view, int year, int month, int day) {

        month++;

        String m = ""+month;
        if (month<10) m = "0"+month;

        String d = ""+day;
        if (day<10) d = "0"+day;



        if (startDate == null){
            startDate = d+"-"+m+"-"+year;
        }else if (endDate == null){
            endDate = d+"-"+m+"-"+year;
            communicator.setStartDate(startDate);
            communicator.setEndDate(endDate);
            communicator.setTreatmentDuration(5);
            communicator.nextFragment();
        }
    }



     public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
             onDateSelected( view,  year,  month,  day);

        }
    }


}
