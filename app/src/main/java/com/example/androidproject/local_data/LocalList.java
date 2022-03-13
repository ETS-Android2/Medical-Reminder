package com.example.androidproject.local_data;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;

import java.util.ArrayList;

public class LocalList implements LocalSource {

    private static LocalList localList = null;
    private ListDao listDao;

    private LocalList(Context context) {
        RoomDb database = RoomDb.getInstance(context);
        listDao = database.listDao();

    }

    public static LocalList getInstance(Context context) {
        if (localList == null) {
            localList = new LocalList(context);
        }
        return localList;
    }


    @Override
    public void insertList(MedicineList medicineList) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                MedicineList list = findListByDate(medicineList.getDate());
                if (list != null) {
                    ArrayList<MedicineDose> doses = medicineList.getMedicineDoseArrayList();
                    for (MedicineDose dose : list.getMedicineDoseArrayList()) {
                        doses.add(dose);
                    }
                    Log.i("TAG", "run: List updated with new one");
                    medicineList.setMedicineDoseArrayList(doses);
                    deleteList(medicineList);
                }


                listDao.insertList(medicineList);
            }
        }).start();

    }

    @Override
    public void deleteList(MedicineList medicineList) {
        listDao.deleteList(medicineList);
    }

    @Override
    public MedicineList findListByDate(String date) {

        return listDao.findListByDate(date);
    }
}
