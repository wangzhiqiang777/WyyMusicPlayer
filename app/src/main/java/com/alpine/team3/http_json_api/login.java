package com.alpine.team3.http_json_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.Response;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.alpine.team3.http_json_api.RequestSongInfoInterceptor.BASE_URL;

public class login extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "login";
    EditText edittext_phone, edittext_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.button_login).setOnClickListener(this);
        edittext_phone = findViewById(R.id.editText_phone);
        edittext_password = findViewById(R.id.editText_password);
        initOKGO();
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
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效

        //https相关设置
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/okhttp-OkGo
        OkGo.getInstance().init(login.this.getApplication())                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(1);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_login:
                String phone = edittext_phone.getText().toString();
                String password = edittext_password.getText().toString();
                OkGo.<String>get(BASE_URL + "/login/cellphone?phone=" + phone + "&password=" + password).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String jsonData = response.body();
                        JSONObject json = JSON.parseObject(jsonData);
                        int code = json.getInteger("code");
                        if (code == 200){
                            String uid = json.getJSONObject("account").getLong("id").toString();
                            Intent intent = new Intent(login.this, MainActivity.class);
                            intent.putExtra("uid", uid);
                            startActivity(intent);
                        }else{
                            Log.d(TAG, "onSuccess: 登录失败" + code);
                            Toast.makeText(login.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        //Toast 返回
                        Toast.makeText(login.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });

                /*OkGo.<String>get("http://10.0.2.2:3000/login/cellphone?phone=" + phone + "&password=" + password).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        String jsonData = response.body();
                        JSONObject json = JSON.parseObject(jsonData);
                        int code = json.getInteger("code");
                        if (code == 200){
                            String uid = json.getJSONObject("account").getLong("id").toString();
                            Intent intent = new Intent(login.this, MainActivity.class);
                            intent.putExtra("uid", uid);
                            startActivity(intent);
                        }else{
                            Log.d(TAG, "onSuccess: 登录失败" + code);
                            Toast.makeText(login.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.d(TAG, "onError: ");
                        Toast.makeText(login.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });*/
                break;
            default:
        }
    }
}