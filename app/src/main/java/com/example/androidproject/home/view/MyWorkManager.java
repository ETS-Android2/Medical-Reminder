package com.example.androidproject.home.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.androidproject.add_medicine.add_medicine_view.AddMedicine;
import com.example.androidproject.add_medicine.add_medicine_view.fragments.DurationFragment;

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

        Context context = getApplicationContext();

        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Toast.makeText(context, "Hello from doWork: ", Toast.LENGTH_SHORT).show();

                Log.i("TAG", "Hello from doWork: from handler ");



                Intent intent = new Intent(context, Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                context.startActivity(intent);

            }
        };

        handler.sendEmptyMessage(0);
        Log.i("TAG", "Hello from doWork: ");

        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

        }
    }




}

  class PurchaseConfirmationDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage("getString")
                .setPositiveButton("OK", (dialog, which) -> {} )
                .create();
    }

    public static String TAG = "PurchaseConfirmationDialog";
}
