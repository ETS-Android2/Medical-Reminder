package com.example.androidproject.home.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.androidproject.alarm_dialog.view.FloatingWindow;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyWorker extends Worker {

    Calendar currentTime=Calendar.getInstance();
    Date  userSettedTime;
    int durationBetweenTimes;


    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //implement the logic here

        Context context =  getApplicationContext();
        Log.i("TAG", "doWork: ");

        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Toast.makeText(context, "Hello from doWork: ", Toast.LENGTH_SHORT).show();

                Log.i("TAG", "Hello from doWork: from handler ");

                Intent intent = new Intent(context, FloatingWindow.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        };

        handler.sendEmptyMessage(0);



        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }

    OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                                            .setInitialDelay(durationBetweenTimes, TimeUnit.MINUTES)
                                            .build();

     PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class,12 ,TimeUnit.HOURS).build();
}
