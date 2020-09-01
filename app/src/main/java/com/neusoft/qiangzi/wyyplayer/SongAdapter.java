package com.neusoft.qiangzi.wyyplayer;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lzx.starrysky.StarrySky;
import com.lzx.starrysky.provider.SongInfo;

import java.util.List;
import android.os.Handler;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private static final String TAG = "SongAdapter";
    private Handler mHandler = null;

    List<SongInfo> mData;
    Context context;
    SongInfo selectedItemData;

    public SongInfo getSelectedItemData() {
        Log.d(TAG, "getSelectedItemData: ");
        return selectedItemData;
    }

    public SongAdapter(Context context, List<SongInfo> Data){
        Log.d(TAG, "SongAdapter: ");
        this.context = context;
        mData = Data;
    }

    public void initHandler(Handler uiHandler){
        mHandler = uiHandler;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mData.size());
        if (mData != null)
            return mData.size();
        return 0;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_artist, tv_duration;
        ImageView imageView;
        SongInfo bean;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "SongViewHolder: ");
            tv_artist = itemView.findViewById(R.id.textView_artist);
            tv_name = itemView.findViewById(R.id.textView_name);
            tv_duration = itemView.findViewById(R.id.textView_duration);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: " + bean.getSongId());
                    selectedItemData = bean;
                    Toast.makeText(context,  bean.getSongId() + " |||| " + bean.getSongName(), Toast.LENGTH_LONG).show();
                    StarrySky.with().playMusicById(bean.getSongId());
                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                }
            });
        }

        private static final String TAG = "MyViewHolder";

        /*
        绑定textView控件显示内容
         */
        public void bind(SongInfo bean){
            this.bean=bean;
            Log.d(TAG, "bind: " + bean.getSongId() + "|||||" + bean.getSongName());
            tv_name.setText(bean.getSongName());
            tv_artist.setText(bean.getArtist());
            long duration = bean.getDuration();
            int minite,second;
            minite = (int)(duration/1000)/60;
            second = (int)(duration/1000)%60;
            tv_duration.setText(minite + ":" + second);
            Log.d(TAG, "bind: image url = "+bean.getSongCover());
            Glide.with(context).load(bean.getSongCover())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);
        }
    }
}
