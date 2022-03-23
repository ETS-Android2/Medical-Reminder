package com.example.androidproject.add_medicine.add_medicine_presenter;

import android.util.Log;

import com.example.androidproject.alarm_dialog.presenter.MyWorkManager;
import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.remote_data.MedicineDAO;
import com.example.androidproject.remote_data.RemoteSource;
import com.example.androidproject.repo.ListRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddMedicinePresenter implements AddmedicinePresenterInterface {


    private ListRepository repository;
    RemoteSource remote = new MedicineDAO();

    public AddMedicinePresenter(ListRepository repository){
        this.repository = repository;
    }

    @Override
    public void addNewMedicine(Medicine medicine) {
        Log.i("TAG", " New medicine was added :) ");

        Log.i("TAG", "addNewMedicine: "+medicine.toString());
        String startDate = medicine.getStartDate();
        String endDate = medicine.getEndDate();
        ArrayList<int[]> doses = medicine.getDoseTime();
        int timeInterval = medicine.getTreatmentDuration();

        int items = medicine.getRecurrence();
        int limit = medicine.getRefillReminder();

        int reminder = 0 ;

        boolean whileFlag = true;

        Calendar currentDate = Calendar.getInstance();

        while (!startDate.equals(toString(currentDate))){
            Log.i("TAG", "start data: "+startDate + "   current date: "+toString(currentDate));
            currentDate.add(Calendar.DAY_OF_MONTH,1);
        }


        while (whileFlag){
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
                items--;

            }

            if (items>limit){
                reminder +=24;
            }



            Log.i("TAG", "List added: ");
            Log.i("TAG", "end data: "+endDate + "   current date: "+toString(currentDate));

            MedicineList list = new MedicineList(toString(currentDate),doseArrayList);
            repository.insertList(list);

            for (int d=0;d<=timeInterval;d++){
                currentDate.add(Calendar.DAY_OF_MONTH,1);
                if (endDate.equals(toString(currentDate)))
                    whileFlag = false;
            }

        }
        repository.insertMedicine(medicine);
        repository.updateManagerTimes();
        uploadData(medicine);

        MyWorkManager.setCustomAlarm(reminder*60,medicine.getName());
    }

    String toString(Calendar calendar){

        Calendar startTime = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(calendar.getTime());
    }
    private void uploadData(Medicine medicine) {


        String id = medicine.getName();
        Map<String, Object> doc = new HashMap<>();
        //  Map<String, ArrayList<int[]>> doc2 = new HashMap<>();
        Map<String, Object> nestedData = new HashMap<>();


        doc.put("\"name\"", id);
        doc.put("\"medicineForm\"", medicine.getMedicineForm());
        doc.put("\"reasonOfTakingDrug\"", medicine.getReasonOfTakingDrug());
        doc.put("\"recurrenceOfTakingDrug\"", medicine.getRecurrenceOfTakingDrug());
        doc.put("\"medicineStrength\"", medicine.getMedicineStrength());
        doc.put("\"recurrence\"", medicine.getRecurrence());
        doc.put("\"TreatmentDuration\"", medicine.getTreatmentDuration());
        doc.put("\"RefillReminder\"", medicine.getRefillReminder());
        doc.put("\"startDate\"", medicine.getStartDate());
        doc.put("\"endDate\"", medicine.getEndDate());
        int i = 0;
        String s="[";
           for ( i = 0; i < medicine.getDosagesPerTime()-1; i++) {
               s=s+""+Arrays.toString(medicine.getDoseTime().get(i))+",";
          //   nestedData.put("" + i, Arrays.toString(medicine.getDoseTime().get(i)));
         }
        s=s+""+Arrays.toString(medicine.getDoseTime().get(i));
           s=s+"]";
            doc.put("\"doseTime\"", s);

        doc.put("\"dosagesPerTime\"", medicine.getDosagesPerTime());

        remote.add(id, doc);
        //   remote.addArr(id, medicine);

    }

}
