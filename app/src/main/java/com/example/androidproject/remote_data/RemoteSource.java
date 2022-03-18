package com.example.androidproject.remote_data;

import com.example.androidproject.home.view.MedicineListAdapter;
import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public interface RemoteSource {
    public void add(String id , Object doc);
    public void addMedicineDose(String id , Object med);
    public void retrieveData (String  date);

}