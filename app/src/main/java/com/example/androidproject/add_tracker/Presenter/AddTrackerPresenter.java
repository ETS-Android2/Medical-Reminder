package com.example.androidproject.add_tracker.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.androidproject.add_medicine.add_medicine_presenter.AddMedicinePresenter;
import com.example.androidproject.add_medicine.add_medicine_presenter.AddmedicinePresenterInterface;
import com.example.androidproject.local_data.LocalDataBase;
import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.RequestModel;
import com.example.androidproject.remote_data.AddTracker;
import com.example.androidproject.remote_data.AddTrackerInterface;
import com.example.androidproject.repo.ListRepository;

import java.util.ArrayList;

public class AddTrackerPresenter implements AddTrackerPresenterInterface{
    AddTrackerInterface addTrackerInterface = new AddTracker();
    ArrayList<Medicine> myFriendMedicines = new ArrayList<>();
    Context context;

    public AddTrackerPresenter(Context context){
        this.context = context;
    }

    @Override
    public void sendRequest(String id, RequestModel req) {
        addTrackerInterface.sendRequest(id,req);
    }

    @Override
    public void updateStatus(String id) {
        addTrackerInterface.updateStatus(id);

    }

    @Override
    public void getMyFriendMedicines(String id) {

    }

    @Override
    public void deleteHealthTracker(String id) {
        addTrackerInterface.deleteHealthTracker(id);
    }
}
