package com.example.androidproject.home.presenter;

import com.example.androidproject.home.view.HomeInterface;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.repo.ListRepository;

public class HomePresenter implements HomePresenterInterface{

    private HomeInterface homeInterface;
    private ListRepository repository;
    public MedicineList medicineList;

    public HomePresenter(HomeInterface homeInterface, ListRepository repository){
        this.repository = repository;
        this.homeInterface = homeInterface;
    }

    @Override
    public void getMedicineList(String date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
               medicineList = repository.findListByDate(date);
               updateList();
            }
        }).start();

    }

    void updateList(){
        homeInterface.updateList(medicineList);
    }
}
