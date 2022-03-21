package com.example.androidproject.alarm_dialog.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.androidproject.R;
import com.example.androidproject.alarm_dialog.presenter.FloatingWindowPresenter;
import com.example.androidproject.alarm_dialog.presenter.FloatingWindowPresenterInterface;
import com.example.androidproject.alarm_dialog.presenter.MyWorkManager;
import com.example.androidproject.local_data.LocalDataBase;
import com.example.androidproject.repo.ListRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class FloatingWindow extends AppCompatActivity implements FloatingWindowInterface {

    FloatingWindowPresenterInterface presenterInterface;
    ArrayList<Integer> times;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_floating);
        presenterInterface = new FloatingWindowPresenter(this, ListRepository.getInstance(this , LocalDataBase.getInstance(this)));
        setMediaPlayer();
        presenterInterface.getTimes();

    }

    void setMediaPlayer(){
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.cleopatra);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    public void stopAlarm(View view){
        mMediaPlayer.stop();
        finishAffinity();
    }

    @Override
    protected void onStop() {
        mMediaPlayer.stop();
        super.onStop();

    }

}