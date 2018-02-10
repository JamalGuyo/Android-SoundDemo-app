package com.example.root.a201sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SeekBar seekBar, musicSeek;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//PLAYING THE MUSIC
        mediaPlayer = MediaPlayer.create(this, R.raw.californialove);
//CUSTOMIZING THE AUDIO SEEKBAR
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBar = findViewById(R.id.sickbar);
        seekBar.setMax(maxVolume);
        seekBar.setProgress(currVolume);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//CUSTOMIZING  THE MUSIC SEEKBAR
        musicSeek = findViewById(R.id.sickbarmusic);
        musicSeek.setMax(mediaPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                musicSeek.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0, 1000);

        musicSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });

    }

    public void controllerAudio(View view) {
        int id = view.getId();

        switch(id){
            case R.id.pplay:
                mediaPlayer.start();
                break;
            case R.id.ppause:
                mediaPlayer.pause();
                break;
            case R.id.sstop:
                mediaPlayer.stop();
                break;
        }
    }
}
