package com.example.androidproject.remote_data;

import com.example.androidproject.model.MedicineList;

public interface RemoteSource {
    public void add(String id , Object doc);
    public void addMedicineDose(String id , Object med);
    public MedicineList retrieveData (String  date);

}