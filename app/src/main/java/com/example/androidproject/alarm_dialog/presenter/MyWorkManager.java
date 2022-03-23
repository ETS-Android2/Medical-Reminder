package com.example.androidproject.alarm_dialog.presenter;

import android.util.Log;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.androidproject.alarm_dialog.view.FloatingWindow;
import com.example.androidproject.refill_reminder.presenter.MyRefillWorker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class MyWorkManager {
    private static void setNextAlarm(int time) {

        WorkManager workManager = WorkManager.getInstance();
        workManager.cancelAllWorkByTag("OneTime");
        if (time > 0) {
            OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                    .setInitialDelay(time, TimeUnit.MINUTES).addTag("OneTime")
                    .build();
            workManager.enqueue(oneTimeWorkRequest);
            Log.i("TAG", "setNextAlarm: Done :) ");
        }else {
            Log.i("TAG", "setNextAlarm: cancelAllWorkByTag(OneTime) and no new ");
        }
    }


    public static void updateTimes(ArrayList<Integer> times) {
        int nextTime = getNextTime(times);

        setNextAlarm(nextTime);
    }

    private static int getNextTime(ArrayList<Integer> times) {
        int res = 0;
        Collections.sort(times);

        if (times.size() > 0) {
            Calendar today = Calendar.getInstance();
            int current = today.get(Calendar.HOUR_OF_DAY) * 60 + today.get(Calendar.MINUTE);

            for (Integer i : times ) {
                Log.i("TAG", current + " getNextTime: Time is " + i.intValue());
                if (current < i.intValue()) {
                    res = i.intValue()- current;
                    break;
                }
            }
        }
        Log.i("TAG", "getNextTime: res is " + res);
        return res;
    }

    public static void setCustomAlarm(int interval,String tag){
        WorkManager workManager = WorkManager.getInstance();
        workManager.cancelAllWorkByTag(tag);
            OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyRefillWorker.class)
                    .setInitialDelay(interval, TimeUnit.MINUTES).addTag(tag)
                    .build();
            workManager.enqueue(oneTimeWorkRequest);
            Log.i("TAG", "setCustomAlarm: Done :) on  "+interval);

    }

    public static void snooze(){
        WorkManager workManager = WorkManager.getInstance();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .build();
        workManager.enqueue(oneTimeWorkRequest);
        Log.i("TAG", "Snoozed: Done :) for 5 Minutes  ");
    }
}

