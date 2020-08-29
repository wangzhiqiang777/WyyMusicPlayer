package com.alpine.team3.http_json_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lzx.starrysky.StarrySky;
import com.lzx.starrysky.StarrySkyConfig;
import com.lzx.starrysky.provider.SongInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, WyyMusic.OnWyyListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainActivity";
    Button button_play, button_last, button_next;
    RecyclerView recyclerView;
    MusicAdapter musicAdapter;
    WyyMusic wyyMusic;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_play_list);

        //初始化view
//        button_play = findViewById(R.id.button_play);
//        button_play.setOnClickListener(this);
//        button_last = findViewById(R.id.button_last);
//        button_last.setOnClickListener(this);
//        button_next = findViewById(R.id.button_next);
//        button_next.setOnClickListener(this);

        //初始化网易云数据
        wyyMusic = new WyyMusic(this, getApplication());
        wyyMusic.setWyyListener(this);
        wyyMusic.login();

        //初始化列表视图
        musicAdapter = new MusicAdapter(this, wyyMusic.getPlayList());
//        musicAdapter.setChagedListener(new MusicAdapter.OnPlayStateChagedListener() {
//            @Override
//            public void onChaged(boolean isplaying) {
////                if(isplaying) button_play.setText("暂停");
////                else button_play.setText("播放");
//            }
//        });
        recyclerView = findViewById(R.id.rvPlayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);//线性布局
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(musicAdapter);

        //设置下拉更新
        refreshLayout = findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setHorizontalFadingEdgeEnabled(true);
        refreshLayout.setOnRefreshListener(this);

        //初始化播放器
        StarrySkyConfig config = new StarrySkyConfig
                .Builder()
                .addInterceptor(new RequestSongInfoInterceptor())
                .build();
        StarrySky.init(getApplication(), config, null);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_play:
                if(button_play.getText().equals("播放")){
                    button_play.setText("暂停");
                    StarrySky.with().restoreMusic();
                }else{
                    button_play.setText("播放");
                    StarrySky.with().pauseMusic();
                }
                break;
            case R.id.button_last:
                StarrySky.with().skipToPrevious();
                break;
            case R.id.button_next:
                StarrySky.with().skipToNext();
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginResult(boolean ok) {
        if(!ok){
            button_play.setEnabled(false);
            button_last.setEnabled(false);
            button_next.setEnabled(false);
        }else {
            //请求列表
            wyyMusic.requestSongList(0);
        }
    }

    @Override
    public void onGotPlaylist(List<SongInfo> list) {
        Log.d(TAG, "onGetPlayList: " + list.size());
        if(refreshLayout.isRefreshing())refreshLayout.setRefreshing(false);//停止刷新显示
        if(list == null){
            Toast.makeText(this,"网络错误，加载失败！",Toast.LENGTH_SHORT).show();
            return;
        }
        StarrySky.with().updatePlayList(list);
        musicAdapter.setSongList(list);
        musicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        if(wyyMusic.getPlaylistCount() > 1){
            wyyMusic.requestNextPlayList();
        }
    }
}