package com.example.androidproject.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.androidproject.local_data.LocalSource;
import com.example.androidproject.model.MedicineList;
import com.example.androidproject.remote_data.MedicineDAO;
import com.example.androidproject.remote_data.RemoteSource;

import java.util.ArrayList;

public class ListRepository implements RepoInterface {

    private Context context;
    private LocalSource localSource;
    private RemoteSource remoteSource = new MedicineDAO();

    private static ListRepository repository = null;

    private ListRepository(Context context, LocalSource localSource) {
        this.context = context;
        this.localSource = localSource;
    }

    public static ListRepository getInstance(Context context, LocalSource localSource) {
        if (repository == null) {
            repository = new ListRepository(context, localSource);
        }
        return repository;
    }


    @Override
    public void insertList(MedicineList medicineList) {
        localSource.insertList(medicineList);
    }

    @Override
    public void deleteList(MedicineList medicineList) {
        localSource.deleteList(medicineList);
    }

    @Override
    public MedicineList findListByDate(String date) {
        return remoteSource.retrieveData(date);
        // localSource.findListByDate(date);
    }

    @Override
    public ArrayList<Integer> getTimes() {
        return localSource.getTodayTimes();
    }

}
