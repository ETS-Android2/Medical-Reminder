package com.example.androidproject.local_data;

import android.content.Context;
import android.util.Log;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.remote_data.MedicineDAO;
import com.example.androidproject.remote_data.RemoteSource;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LocalDataBase implements LocalSource {

    private static LocalDataBase localDataBase = null;
    private MedicineDao medicineDao;

    RemoteSource remote = new MedicineDAO();

    private LocalDataBase(Context context) {
        RoomDb database = RoomDb.getInstance(context);
        medicineDao = database.listDao();

    }

    public static LocalDataBase getInstance(Context context) {
        if (localDataBase == null) {
            localDataBase = new LocalDataBase(context);
        }
        return localDataBase;
    }


    @Override
    public void insertList(MedicineList medicineList) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                MedicineList list = medicineDao.findListByDate(medicineList.getDate());
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

                medicineDao.insertList(medicineList);
            }
        }).start();

    }

    private ArrayList<MedicineDose> sortList(ArrayList<MedicineDose> dose){
        ArrayList<MedicineDose> doseArrayList = new ArrayList<>();
        Collections.sort(dose, new Comparator<MedicineDose>() {
            @Override
            public int compare(MedicineDose m1, MedicineDose m2) {
                return m2.getHour()*60+m2.getMinute() > m1.getDose()*60+m1.getMinute() ? 1 : m1.getHour()*60+m1.getMinute() > m2.getHour()*60+m2.getMinute() ? -1 : 0 ;
            }
        });

        return dose;
    }

    @Override
    public void deleteList(MedicineList medicineList) {
        medicineDao.deleteList(medicineList);
    }

    @Override
    public MedicineList findListByDate(String date) {
        return medicineDao.findListByDate(date);
    }

    @Override
    public void insertMedicine(Medicine medicine) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Medicine oldMedicine= findMedicineByName(medicine.getName());
                if (oldMedicine != null){
                    deleteMedicine(medicine);
                }
                medicineDao.insertMedicine(medicine);
            }
        }).start();
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        medicineDao.deleteMedicine(medicine);
    }

    @Override
    public Medicine findMedicineByName(String name) {
        return medicineDao.findMedicineByName(name);
    }

    @Override
    public ArrayList<Integer> getTodayTimes() {

        ArrayList<Integer> timeList = new ArrayList<>();

        Calendar today = Calendar.getInstance();
        MedicineList list = findListByDate(new SimpleDateFormat("dd-MM-yyyy").format(today.getTime()));

        if (list != null) {
            int timeInMinutes = 0;
            for (MedicineDose dose : list.getMedicineDoseArrayList()) {
                timeInMinutes = dose.getHour() * 60 + dose.getMinute();
                if (!timeList.contains(Integer.valueOf(timeInMinutes))) {
                    timeList.add(Integer.valueOf(timeInMinutes));
                    Log.i("TAG", "getTodayTimes: " + timeInMinutes);
                }
            }
        }


        return timeList;
    }
}
