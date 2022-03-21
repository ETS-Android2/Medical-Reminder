package com.example.androidproject.home.view;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.androidproject.local_data.LocalDataBase;
import com.example.androidproject.repo.ListRepository;


public class MyPeriodicWorker extends Worker {
    Context context;
    public MyPeriodicWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {


        Log.i("TAG", "MyPeriodicWorker doWork: ");
        ListRepository repository = ListRepository.getInstance(context, LocalDataBase.getInstance(context));
        repository.updateManagerTimes();


        return null;
    }
}
