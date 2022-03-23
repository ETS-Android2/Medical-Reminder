package com.example.androidproject.refill_reminder.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.androidproject.R;

public class RefillReminder extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    Vibrator vibrator ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_refill_reminder);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        setMediaPlayer();


    }

    void setMediaPlayer(){
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.cleopatra);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        long[] vibrationWaveFormDurationPattern = {0, 10, 200, 500, 700, 1000, 300, 200, 50, 10};


        // the vibration of the type custom waveforms needs the API version 26
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            // create VibrationEffect instance and createWaveform of vibrationWaveFormDurationPattern
            // -1 here is the parameter which indicates that the vibration shouldn't be repeated.
            VibrationEffect vibrationEffect = VibrationEffect.createWaveform(vibrationWaveFormDurationPattern, -1);

            // it is safe to cancel all the vibration taking place currently
            vibrator.cancel();

            // now initiate the vibration of the device
            vibrator.vibrate(vibrationEffect);
        }

    }

    public void stopRefillAlarm(View view){
        mMediaPlayer.stop();
        vibrator.cancel();
        finish();
    }


    @Override
    protected void onStop() {
        mMediaPlayer.stop();
        vibrator.cancel();
        super.onStop();
    }
}