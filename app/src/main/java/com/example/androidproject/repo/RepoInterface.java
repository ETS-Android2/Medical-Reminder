package com.example.androidproject.repo;

import androidx.lifecycle.LiveData;

import com.example.androidproject.model.MedicineList;

public interface RepoInterface {
    public void insertList(MedicineList medicineList);
    public void deleteList(MedicineList medicineList);
    public MedicineList findListByDate(String date);

}
