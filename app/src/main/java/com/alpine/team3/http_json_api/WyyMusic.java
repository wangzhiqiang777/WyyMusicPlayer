package com.alpine.team3.http_json_api;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lzx.starrysky.provider.SongInfo;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.alpine.team3.http_json_api.RequestSongInfoInterceptor.BASE_URL;

public class WyyMusic {

    private static final String TAG = "WywMusic";
    String uid = null;
    private List<SongInfo> playList = new ArrayList<>();
    private Context context;
    private Application application;
    private boolean isLogin = false;
    private OnWyyListener wyyListener;
    private int playlistCount;
    private int currentPlaylistId;

    public WyyMusic(Context context, Application application) {
        this.context = context;
        this.application = application;
    }

    public void login(){
        initOKGO();
        loginCheck();
    }

    private void initOKGO() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(context)));              //使用数据库保持cookie，如果cookie不过期，则一直有效

        //https相关设置
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/okhttp-OkGo
        OkGo.getInstance().init(application)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(1);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    private void loginCheck(){
        String phone = "18742016198";
        String password = "wzq02087374";
        OkGo.<String>get(BASE_URL + "/login/cellphone?phone=" + phone + "&password=" + password).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String jsonData = response.body();
                JSONObject json = JSON.parseObject(jsonData);
                int code = json.getInteger("code");
                if (code == 200){
                    uid = json.getJSONObject("account").getLong("id").toString();
//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    intent.putExtra("uid", uid);
//                    startActivity(intent);
                    Log.d(TAG, "onSuccess: 登录成功！");
                    isLogin = true;
                    wyyListener.onLoginResult(true);
                }else{
                    Log.d(TAG, "onSuccess: 登录失败" + code);
                    Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                    wyyListener.onLoginResult(false);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                //Toast 返回
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getPlaylistCount() {
        return playlistCount;
    }

    public void setWyyListener(OnWyyListener wyyListener) {
        this.wyyListener = wyyListener;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public List<SongInfo> getPlayList() {
        return playList;
    }

    public void requestNextPlayList(){
        requestSongList((currentPlaylistId+1)%playlistCount);
    }
    private WyyPlaylist wyyPlaylist;
    public void requestPlayList(){
        OkGo.<String>get(BASE_URL + "/user/playlist?uid=" + uid).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String jsonData = response.body();
                Log.d(TAG, "onSuccess: playlist json="+jsonData);
                Gson gson = new Gson();
                wyyPlaylist = gson.fromJson(jsonData, WyyPlaylist.class);
                if (wyyPlaylist!=null && wyyPlaylist.getCode() == 200){
                    Log.d(TAG, "requestPlayList: playlistCount="+playlistCount);
                    requestSongList(currentPlaylistId);
                }else{
                    Log.d(TAG, "requestPlayList: 获取歌单失败");
                    wyyListener.onGotPlaylist(null);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                wyyListener.onGotPlaylist(null);
            }
        });
    }
    public void requestSongList(int playlistId){

        OkGo.<String>get(BASE_URL + "/user/playlist?uid=" + uid).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String jsonData = response.body();
                JSONObject json = JSON.parseObject(jsonData);
                Log.d(TAG, "onSuccess: playlist json="+json);
                int code = json.getInteger("code");
                if (code == 200){
                    Toast.makeText(context, "获取歌单成功", Toast.LENGTH_SHORT).show();
                    //String listId = json.getJSONArray("playlist").getJSONObject(0).getString("id");
                    JSONArray array = json.getJSONArray("playlist");
                    playlistCount = array.size();
                    String listId = array.getJSONObject(playlistId).getLong("id").toString();
                    Log.d(TAG, "onSuccess: playlistCount="+playlistCount+",index="+playlistId+",listId="+listId);
                    OkGo.<String>get(BASE_URL + "/playlist/detail?id=" + listId).execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String jsonData = response.body();
                            JSONObject json = JSON.parseObject(jsonData);
                            Log.d(TAG, "onSuccess: json="+json);
                            int code = json.getInteger("code");
                            if (code == 200){
                                Toast.makeText(context, "获取歌曲成功", Toast.LENGTH_SHORT).show();
                                JSONArray jArr = json.getJSONObject("playlist").getJSONArray("tracks");
                                int i;
                                playList.clear();
                                for (i = 0; i < jArr.size(); i++){
                                    JSONObject j = jArr.getJSONObject(i);
                                    String name = j.getString("name");
                                    String id = j.getString("id");
                                    String artist = j.getJSONArray("ar").getJSONObject(0).getString("name");
                                    String curl = j.getJSONObject("al").getString("picUrl");
                                    Log.d(TAG, "onSuccess: curl="+curl);
                                    long duration = j.getLong("dt");
                                    SongInfo info = new SongInfo();
                                    info.setSongId(id);
                                    info.setSongName(name);
                                    info.setArtist(artist);
                                    info.setSongCover(curl);
                                    info.setDuration(duration);
                                    playList.add(info);
                                }
                                currentPlaylistId = playlistId;
                                wyyListener.onGotPlaylist(playList);

                            }else{
                                Log.d(TAG, "onSuccess: 获取歌曲失败" + code);
                                Toast.makeText(context, "获取歌曲失败", Toast.LENGTH_SHORT).show();
                                wyyListener.onGotPlaylist(null);
                            }
                        }
                    });
                }else{
                    Log.d(TAG, "onSuccess: 获取歌单失败" + code);
                    Toast.makeText(context, "获取歌单失败", Toast.LENGTH_SHORT).show();
                    wyyListener.onGotPlaylist(null);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                wyyListener.onGotPlaylist(null);
            }
        });
    }

    public interface OnWyyListener{
        void onLoginResult(boolean ok);
        void onGotPlaylist(List<SongInfo> list);
    }
}
