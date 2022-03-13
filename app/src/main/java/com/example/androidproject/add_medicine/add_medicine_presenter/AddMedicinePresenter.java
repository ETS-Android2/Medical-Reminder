package com.example.androidproject.add_medicine.add_medicine_presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.remote_data.MedicineDAO;
import com.example.androidproject.remote_data.RemoteSource;
import com.google.android.gms.tasks.Task;

public class AddMedicinePresenter implements AddmedicinePresenterInterface {


    RemoteSource  remoteSource = new MedicineDAO();
    String TAG="";
    @Override
    public void addNewMedicine(Medicine medicine) {
        Log.i(TAG, "addNewMedicine: Doooooooooooooooooooooone");
        remoteSource.add(medicine).addOnSuccessListener(suc->
        {
            Log.i("TAG", " New medicine was added :");
        }).addOnFailureListener(er->
        {
            Log.i("TAG", " Fail to add:");
        });
    }
}
