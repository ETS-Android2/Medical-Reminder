package com.example.androidproject.alarm_dialog.presenter;

import com.example.androidproject.alarm_dialog.view.FloatingWindowInterface;
import com.example.androidproject.repo.ListRepository;

public class FloatingWindowPresenter implements FloatingWindowPresenterInterface{

    private FloatingWindowInterface windowInterface;
    private ListRepository repository;

   public FloatingWindowPresenter(FloatingWindowInterface windowInterface,ListRepository repository){
        this.repository = repository;
        this.windowInterface = windowInterface;
    }


    @Override
    public void getTimes() {
               repository.updateManagerTimes();
    }
}
