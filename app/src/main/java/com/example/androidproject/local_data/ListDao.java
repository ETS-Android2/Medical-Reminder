package com.example.androidproject.local_data;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.androidproject.model.MedicineList;

import java.util.ArrayList;

@androidx.room.Dao

public interface ListDao {

    @Query("SELECT * FROM medicine_list WHERE date LIKE :date " + "LIMIT 1")
    LiveData<MedicineList> findListByDate(String date);

    @Insert
    void insertList(MedicineList list);

    @Delete

    void deleteList(MedicineList list);
}
