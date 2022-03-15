package com.example.androidproject.home.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MyWorkManager extends Worker {

    Calendar currentTime=Calendar.getInstance();
    Date  userSettedTime;
    int durationBetweenTimes;


    public MyWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //implement the logic here


        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }

    OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorkManager.class)
                                            .setInitialDelay(durationBetweenTimes, TimeUnit.MINUTES)
                                            .build();
       // WorkManager.getInstance().enqueue(oneTimeWorkRequest);



     PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorkManager.class,12 ,TimeUnit.HOURS).build();
}
