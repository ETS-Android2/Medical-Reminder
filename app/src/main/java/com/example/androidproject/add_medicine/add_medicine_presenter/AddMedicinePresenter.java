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

        Calendar currentDate = Calendar.getInstance();

        while (!startDate.equals(toString(currentDate))){
            Log.i("TAG", "start data: "+startDate + "   current date: "+toString(currentDate));
            currentDate.add(Calendar.DAY_OF_MONTH,1);
        }


        while (!endDate.equals(toString(currentDate))){
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
            Log.i("TAG", "end data: "+endDate + "   current date: "+toString(currentDate));

            MedicineList list = new MedicineList(toString(currentDate),doseArrayList);
            repository.insertList(list);

            currentDate.add(Calendar.DAY_OF_MONTH,1);

        }
        repository.insertMedicine(medicine);
        repository.updateManagerTimes();
    }

    String toString(Calendar calendar){

        Calendar startTime = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(calendar.getTime());
    }

}
