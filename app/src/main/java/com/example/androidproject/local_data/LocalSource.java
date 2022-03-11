package com.example.androidproject.local_data;

import androidx.lifecycle.LiveData;

import com.example.androidproject.model.MedicineList;
import java.util.ArrayList;


public interface LocalSource {
    public void insertList(MedicineList medicineList);
    public void deleteList(MedicineList medicineList);
    public LiveData<MedicineList>  findListByDate(String date);
}
