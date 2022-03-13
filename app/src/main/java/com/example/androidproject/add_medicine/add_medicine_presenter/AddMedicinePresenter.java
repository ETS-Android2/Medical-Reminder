package com.example.androidproject.add_medicine.add_medicine_presenter;

import android.util.Log;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.repo.ListRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddMedicinePresenter implements AddmedicinePresenterInterface {


    private ListRepository repository;

    public AddMedicinePresenter(ListRepository repository){
        this.repository = repository;
    }

    @Override
    public void addNewMedicine(Medicine medicine) {
        Log.i("TAG", " New medicine was added :) ");

        String startDate = medicine.getStartDate();
        String endDate = medicine.getEndDate();
        ArrayList<int[]> doses = medicine.getDoseTime();

        Calendar startTime = Calendar.getInstance();

        while (!startDate.equals(toString(startTime))){
            Log.i("TAG", "start data: "+startDate + "   current date: "+toString(startTime));
            startTime.add(Calendar.DAY_OF_MONTH,1);
        }

        while (!endDate.equals(toString(startTime))){
            ArrayList<MedicineDose> doseArrayList = new ArrayList<>();
            for (int i = 0;i < doses.size();i++){
                int[] time = doses.get(i);
                MedicineDose medicineDose = new MedicineDose();
                medicineDose.setName(medicine.getName());
                medicineDose.setDose(medicine.getDosagesPerTime());
                medicineDose.setHour(time[0]);
                medicineDose.setMinute(time[1]);

                doseArrayList.add(medicineDose);

                Log.i("TAG", "Dose added: "+i);

            }
            Log.i("TAG", "List added: ");
            Log.i("TAG", "end data: "+endDate + "   current date: "+toString(startTime));
            MedicineList list = new MedicineList(toString(startTime),doseArrayList);
            repository.insertList(list);

            startTime.add(Calendar.DAY_OF_MONTH,1);

        }

        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.MONTH, 1);




    }

    String toString(Calendar calendar){

        Calendar startTime = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(calendar.getTime());
    }

}
