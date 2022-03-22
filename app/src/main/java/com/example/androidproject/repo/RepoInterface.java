package com.example.androidproject.repo;

import androidx.lifecycle.LiveData;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.MedicineList;

import java.util.ArrayList;

public interface RepoInterface {
    public void insertList(MedicineList medicineList);
    public void deleteList(MedicineList medicineList);
    public MedicineList findListByDate(String date);

    public void insertMedicine(Medicine medicine);
    public void deleteMedicine(Medicine medicine);
    public Medicine findMedicineByName(String name);

    public void updateManagerTimes();

}
