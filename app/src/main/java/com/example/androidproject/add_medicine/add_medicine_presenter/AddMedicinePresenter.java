package com.example.androidproject.add_medicine.add_medicine_presenter;

import android.util.Log;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.remote_data.MedicineDAO;
import com.example.androidproject.remote_data.RemoteSource;
import com.example.androidproject.repo.ListRepository;
import com.google.firebase.firestore.FieldValue;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddMedicinePresenter implements AddmedicinePresenterInterface {


    private ListRepository repository;
    RemoteSource remote = new MedicineDAO();
    Calendar currentDate;
    int i = 0;

    public AddMedicinePresenter(ListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewMedicine(Medicine medicine) {
        Log.i("TAG", " New medicine was added :) ");

        String startDate = medicine.getStartDate();
        String endDate = medicine.getEndDate();
        List<int[]> doses = medicine.getDoseTime();

        currentDate = Calendar.getInstance();

        while (!startDate.equals(toString(currentDate))) {
            Log.i("TAG", "start data: " + startDate + "   current date: " + toString(currentDate));
            currentDate.add(Calendar.DAY_OF_MONTH, 1);
        }


        while (!endDate.equals(toString(currentDate))) {
            ArrayList<MedicineDose> doseArrayList = new ArrayList<>();

            for (int i = 0; i < doses.size(); i++) {

                int[] time = doses.get(i);
                MedicineDose medicineDose = new MedicineDose();
                medicineDose.setName(medicine.getName());
                medicineDose.setDose(medicine.getDosagesPerTime());
                medicineDose.setHour(time[0]);
                medicineDose.setMinute(time[1]);

                doseArrayList.add(medicineDose);

                Log.i("TAG", "Dose added: " + i);

            }


            Log.i("TAG", "List added: ");
            Log.i("TAG", "end data: " + endDate + "   current date: " + toString(currentDate));

            MedicineList list = new MedicineList(toString(currentDate), doseArrayList);
            repository.insertList(list);
            currentDate.add(Calendar.DAY_OF_MONTH, 1);

        }
        uploadData(medicine);

    }

    String toString(Calendar calendar) {

        Calendar startTime = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(calendar.getTime());
    }

    private void uploadData(Medicine medicine) {


        String id = medicine.getName();
        Map<String, Object> doc = new HashMap<>();
        Map<String, ArrayList<int[]>> doc2 = new HashMap<>();
        Map<String, Object> nestedData = new HashMap<>();


        doc.put("id", id);
        doc.put("form", medicine.getMedicineForm());
        doc.put("reason", medicine.getReasonOfTakingDrug());
        doc.put("recurrency", medicine.getRecurrenceOfTakingDrug());
        doc.put("strength", medicine.getMedicineStrength() + medicine.getMedicineStrengthUnit());
        doc.put("minItem", medicine.getRefillReminder());
        doc.put("totalItem", medicine.getTotalItem());
        doc.put("startdate", medicine.getStartDate());
        doc.put("enddate", medicine.getEndDate());
        for (int i = 0; i < medicine.getDosagesPerTime(); i++) {
            nestedData.put("" + i, Arrays.toString(medicine.getDoseTime().get(i)));
        }
        doc.put("DoseTime", nestedData);

        doc.put("DosagesPerTime", medicine.getDosagesPerTime());

        remote.add(id, doc);
        //   remote.addArr(id, medicine);

    }
}