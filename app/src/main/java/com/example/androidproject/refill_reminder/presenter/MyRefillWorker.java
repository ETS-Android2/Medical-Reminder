package com.example.androidproject.refill_reminder.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.androidproject.refill_reminder.view.RefillReminder;

public class MyRefillWorker extends Worker {

    public MyRefillWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Context context =  getApplicationContext();
        Log.i("TAG", "doWork: ");

        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Toast.makeText(context, "Hello from RefillReminder doWork: ", Toast.LENGTH_SHORT).show();

                Log.i("TAG", "Hello from RefillReminder doWork: from handler ");

                Intent intent = new Intent(context, RefillReminder.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        };

        handler.sendEmptyMessage(0);


        return null;
    }
}
