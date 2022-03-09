package com.example.androidproject.add_medicine.add_medicine_presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.androidproject.MainActivity;
import com.example.androidproject.Medicine;
import com.example.androidproject.add_medicine.add_medicine_view.AddMedicine;

public class AddMedicinePresenter implements AddmedicinePresenterInterface{


    @Override
    public void addNewMedicine(Medicine medicine) {
        Log.i("TAG", " New medicine was added :) ");
    }
}
