package com.example.androidproject.local_data;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.remote_data.MedicineDAO;
import com.example.androidproject.remote_data.RemoteSource;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LocalList implements LocalSource {

    private static LocalList localList = null;
    private ListDao listDao;

    RemoteSource remote = new MedicineDAO();

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
                MedicineList list = listDao.findListByDate(medicineList.getDate());
                if (list != null) {
                    ArrayList<MedicineDose> doses = medicineList.getMedicineDoseArrayList();
                    for (MedicineDose dose : list.getMedicineDoseArrayList()) {
                        doses.add(dose);
                    }
                    Log.i("TAG", "run: List updated with new one");
                    medicineList.setMedicineDoseArrayList(doses);
                    deleteList(medicineList);
                }

                ArrayList<MedicineDose> doses = sortList(medicineList.getMedicineDoseArrayList());

                medicineList.setMedicineDoseArrayList(doses);


                String id = medicineList.getDate();
                Map<String,Object> obj = new HashMap<>();

                Gson gson = new Gson();
                ArrayList<String> strings =new ArrayList<>();
                for(MedicineDose d : medicineList.getMedicineDoseArrayList()){
                    strings.add(gson.toJson(d,MedicineDose.class));

                }

                Log.i("TAG", "insertMedicineList: "+strings.toString());
                obj.put("medicinesDose",strings.toString());

                remote.addMedicineDose(id,obj);

                listDao.insertList(medicineList);
            }
        }).start();

    }

    private ArrayList<MedicineDose> sortList(ArrayList<MedicineDose> dose){
        ArrayList<MedicineDose> doseArrayList = new ArrayList<>();
        Collections.sort(dose, new Comparator<MedicineDose>() {
            @Override
            public int compare(MedicineDose m1, MedicineDose m2) {
                return m2.getHour() > m1.getDose() ? 1 : m1.getHour() > m2.getHour() ? -1 : 0 ;
            }
        });

        return dose;
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
