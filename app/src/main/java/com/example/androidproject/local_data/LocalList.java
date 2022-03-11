package com.example.androidproject.local_data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.androidproject.model.MedicineList;

import java.util.ArrayList;

public class LocalList implements LocalSource{

    private static LocalList localList = null;
    private ListDao listDao;

    private LocalList(Context context){
        RoomDb database = RoomDb.getInstance(context);
        listDao = database.listDao();

    }

    public static LocalList getInstance(Context context) {
        if (localList ==null){
            localList =new LocalList(context);
        }
        return localList;
    }


    @Override
    public void insertList(MedicineList medicineList) {
        listDao.insertList(medicineList);
    }

    @Override
    public void deleteList(MedicineList medicineList) {
        listDao.deleteList(medicineList);
    }

    @Override
    public LiveData<MedicineList> findListByDate(String date) {
        return listDao.findListByDate(date);
    }
}
