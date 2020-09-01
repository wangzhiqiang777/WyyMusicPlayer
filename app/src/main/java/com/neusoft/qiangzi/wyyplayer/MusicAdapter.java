package com.neusoft.qiangzi.wyyplayer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzx.starrysky.StarrySky;
import com.lzx.starrysky.provider.SongInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    private Context context;
    private List<SongInfo> songList;
    private OnPlayStateChagedListener chagedListener;

    public MusicAdapter(Context context, List<SongInfo> songList) {
        this.context = context;
        this.songList = songList;
    }

    public void setChagedListener(OnPlayStateChagedListener chagedListener) {
        this.chagedListener = chagedListener;
    }

    public void setSongList(List<SongInfo> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    private static final String TAG = "MusicAdapter";
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvSongName,tvArtist,tvDuration;
        SongInfo info;
        Button btnPlayStart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            tvSongName = itemView.findViewById(R.id.tvSongName);
            tvArtist = itemView.findViewById(R.id.tvArtist);
            tvDuration = itemView.findViewById(R.id.tvDurantion);
            btnPlayStart = itemView.findViewById(R.id.buttonPlayStart);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, SongPlayActivity.class);
                    i.putExtra("songid", info);
                    context.startActivity(i);
                }
            });
            btnPlayStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(StarrySky.with().isCurrMusicIsPlaying(info.getSongId())){
                        StarrySky.with().pauseMusic();
                        btnPlayStart.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled);
                        if(chagedListener!=null)chagedListener.onChaged(false);
                    }else {
                        StarrySky.with().playMusicById(info.getSongId());
                        btnPlayStart.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled);
                        if(chagedListener!=null)chagedListener.onChaged(true);
                    }
                    Log.d(TAG, "btnPlayStart:onClick: song id = "+info.getSongId());
                }
            });
        }

        public void bindData(int position){
            info = songList.get(position);
            tvSongName.setText(info.getSongName());
            tvArtist.setText(info.getArtist());
            tvDuration.setText(String.valueOf(info.getDuration()));
            Glide.with(context).load(info.getSongCover()).into(imageView);
            if(StarrySky.with().isCurrMusicIsPlaying(info.getSongId()))
                btnPlayStart.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled);
            else
                btnPlayStart.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled);
        }
    }

    public interface OnPlayStateChagedListener{
        void onChaged(boolean isplaying);
    }
}
