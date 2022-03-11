package com.example.androidproject.add_medicine.add_medicine_presenter;

import android.util.Log;

import com.example.androidproject.model.Medicine;

public class AddMedicinePresenter implements AddmedicinePresenterInterface{


    @Override
    public void addNewMedicine(Medicine medicine) {
        Log.i("TAG", " New medicine was added :) ");
    }
}
