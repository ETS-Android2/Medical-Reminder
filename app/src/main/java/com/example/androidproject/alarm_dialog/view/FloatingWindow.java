package com.example.androidproject.alarm_dialog.view;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.alarm_dialog.presenter.FloatingWindowPresenter;
import com.example.androidproject.alarm_dialog.presenter.FloatingWindowPresenterInterface;
import com.example.androidproject.home.presenter.HomePresenter;
import com.example.androidproject.local_data.LocalList;
import com.example.androidproject.repo.ListRepository;
import java.util.ArrayList;

public class FloatingWindow extends AppCompatActivity implements FloatingWindowInterface {

    FloatingWindowPresenterInterface presenterInterface;
    ArrayList<Integer> times;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_floating);
        presenterInterface = new FloatingWindowPresenter(this, ListRepository.getInstance(this , LocalList.getInstance(this)));

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

    @Override
    public void updateTimes(ArrayList<Integer> times) {
        this.times = times;
    }
}