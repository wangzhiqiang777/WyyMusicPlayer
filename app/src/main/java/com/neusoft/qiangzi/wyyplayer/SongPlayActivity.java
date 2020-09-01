package com.neusoft.qiangzi.wyyplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzx.starrysky.StarrySky;
import com.lzx.starrysky.control.OnPlayerEventListener;
import com.lzx.starrysky.provider.SongInfo;

import java.util.Timer;
import java.util.TimerTask;

public class SongPlayActivity extends AppCompatActivity {

    private String songId;
    private SongInfo songInfo;
    ImageView ivCover, ivPlay;
    TextView tvName,tvArtist;
    ProgressBar progressBar;

    Timer progressTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);
        ivCover = findViewById(R.id.ivCover);
        ivPlay = findViewById(R.id.ivPlayStart);
        tvName = findViewById(R.id.tvName);
        tvArtist = findViewById(R.id.tvArtist);
        progressBar = findViewById(R.id.progressBar);

        Intent i = getIntent();
        songInfo = i.getParcelableExtra("songid");
        songId = songInfo.getSongId();
        Log.d(TAG, "onCreate: songid="+songId);

        progressTimer = new Timer();
        progressTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                progressBar.setProgress((int)StarrySky.with().getPlayingPosition()/1000);
            }
        }, 1000,1000);

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StarrySky.with().isCurrMusicIsPlaying(songId)){
                    StarrySky.with().pauseMusic();
                    ivPlay.setImageResource(R.drawable.ic_baseline_play_circle_filled);
                }else {
                    StarrySky.with().playMusicById(songId);
                    ivPlay.setImageResource(R.drawable.ic_baseline_pause_circle_filled);
                }
            }
        });

        StarrySky.with().addPlayerEventListener(eventListener);
        if(!StarrySky.with().isCurrMusicIsPlaying(songId)){
            StarrySky.with().playMusicById(songId);
        }
        updateView();

    }

    private static final String TAG = "SongPlayActivity";
    private void updateView() {
        if(songInfo == null)return;
        ivPlay.setImageResource(R.drawable.ic_baseline_pause_circle_filled);
        tvName.setText(songInfo.getSongName());
        tvArtist.setText(songInfo.getArtist());
        Glide.with(this).load(songInfo.getSongCover()).into(ivCover);
        progressBar.setMax((int)songInfo.getDuration()/1000);
    }

    OnPlayerEventListener eventListener = new OnPlayerEventListener() {
        @Override
        public void onMusicSwitch(SongInfo songInfo) {
            Log.d(TAG, "onMusicSwitch: ");
            SongPlayActivity.this.songInfo = songInfo;
            updateView();
        }

        @Override
        public void onPlayerStart() {
            Log.d(TAG, "onPlayerStart: ");
            updateView();
        }

        @Override
        public void onPlayerPause() {
            Log.d(TAG, "onPlayerPause: ");
        }

        @Override
        public void onPlayerStop() {

        }

        @Override
        public void onPlayCompletion(SongInfo songInfo) {

        }

        @Override
        public void onBuffering() {

        }

        @Override
        public void onError(int i, String s) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressTimer.cancel();
        StarrySky.with().removePlayerEventListener(eventListener);
    }
}