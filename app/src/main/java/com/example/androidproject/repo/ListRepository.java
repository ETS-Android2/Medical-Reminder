package com.example.androidproject.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.androidproject.local_data.LocalSource;
import com.example.androidproject.model.MedicineList;

public class ListRepository implements RepoInterface {

    private Context context;
    private LocalSource localSource;
    private ListRepository repository = null;

    private ListRepository(Context context, LocalSource localSource) {
        this.context = context;
        this.localSource = localSource;
    }

    public ListRepository getInstance(Context context, LocalSource localSource) {
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
    public LiveData<MedicineList> findListByDate(String date) {
        return localSource.findListByDate(date);
    }

}
