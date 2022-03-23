package com.example.androidproject.local_data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.MedicineList;

import java.util.ArrayList;
import java.util.List;

@androidx.room.Dao

public interface MedicineDao {

    @Query("SELECT * FROM medicine_list WHERE date LIKE :date LIMIT 1")
    MedicineList findListByDate(String date);

    @Query("SELECT * FROM medicine_list")
    List<MedicineList> findAll();

    @Insert
    void insertList(MedicineList list);

    @Delete
    void deleteList(MedicineList list);

    @Query("SELECT * FROM medicine WHERE name LIKE :name LIMIT 1")
    Medicine findMedicineByName(String name);

    @Insert
    void insertMedicine(Medicine medicine);

    @Delete
    void deleteMedicine(Medicine medicine);



}
