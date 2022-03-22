package com.example.androidproject.home.view;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.androidproject.R;

public class Notifications {

    public static final String CHANNEL_ID="x_channelId";

    public static void showNotifications(Context context){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"CHANNEL display name", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            // to manage the notification
            NotificationManager notificationManager=context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
            Intent intent=new Intent(context,Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID);
            builder.setSmallIcon(R.drawable.app_logo)
                    .setContentTitle("Medicine Reminder")
                    .setContentText("hello ...............")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);


            NotificationManagerCompat nmc=NotificationManagerCompat.from(context);
            nmc.notify(10,builder.build());



    }


}
