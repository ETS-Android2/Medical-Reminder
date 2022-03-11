package com.example.androidproject.home.presenter;

import com.example.androidproject.home.view.HomeInterface;

public class HomePresenter implements HomePresenterInterface{

    private HomeInterface homeInterface;

    public HomePresenter(HomeInterface homeInterface){
        this.homeInterface = homeInterface;
    }

    @Override
    public void getMedicineList() {

    }
}
