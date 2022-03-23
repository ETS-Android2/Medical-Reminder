package com.example.androidproject.drug_screen.presenter;

import android.util.Log;

import com.example.androidproject.drug_screen.view.DrugScreenInterface;
import com.example.androidproject.home.view.HomeInterface;
import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.repo.ListRepository;

public class DrugScreenPresenter implements DrugScreenPresenterInterface{

    private DrugScreenInterface drugScreenInterface;
    private ListRepository repository;
    public MedicineList medicineList;

    public DrugScreenPresenter(DrugScreenInterface drugScreenInterface, ListRepository repository){
        this.repository = repository;
        this.drugScreenInterface = drugScreenInterface;
        repository.updateManagerTimes();
    }


    @Override
    public void getMedicine(String medicineName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Medicine medicine = repository.findMedicineByName(medicineName);
                Log.i("TAG", "run: " + medicineName);
                drugScreenInterface.showMedicine(medicine);
            }
        }).start();
    }

    @Override
    public void deleteMedicine(String name) {
        repository.deleteMedicineByName(name);
    }
}
