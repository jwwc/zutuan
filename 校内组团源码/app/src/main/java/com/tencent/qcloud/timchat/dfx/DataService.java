package com.tencent.qcloud.timchat.dfx;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.content.ContentValues.TAG;

/**
 * Created by wuchaochao on 2018/1/26.
 */

public class DataService extends Service implements OkHttpDAO{
    private String mBaseURL="http://42.247.7.170/";
    /**
    * 记录正方教务系统页面表单的__VIEWSTATE的值
    */
    private String mViewState;
    /*
    * 已登陆用户的信息
    */
    private User mUser;
    @Nullable
    private OkHttpClient mOkHttpClient=new OkHttpClient.Builder().cookieJar(new CookieJar() {
        private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            String u=url.host();
            String urlStr=url.url().toString();
            if(urlStr.equals(mBaseURL)){
                cookieStore.put(u, cookies);
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url.host());
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    }).build();
    @Override
    public void init() {
        Request request = new Request.Builder().url(mBaseURL).build();
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                connectionError();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mViewState=HtmlTools.findViewState(response.body().string());//获取隐藏域
                getCheckImg();
            }
        });
    }
    private  DataBinder myBinder=new DataBinder();
    public class DataBinder extends Binder {

        public void login(User user) {
            DataService.this.login(user);
        }

        public void getCheckImg() {
            DataService.this.getCheckImg();
        }
    }
    @Override
    public void getCheckImg() {
        Request request = new Request.Builder().url(mBaseURL+Constant.CHECK_IMAGE_URL).build();
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                connectionError();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body=response.body();
                Log.e(TAG, "onResponse: 99999999999999999999");
                byte[] data=body.bytes();
                //取得验证码数据并发送广播，将在登陆界面接收广播
                Intent it=new Intent(BroadcastAction.CHECK_IMG);
                it.putExtra(Constant.CHECK,data);
                sendBroadcast(it);
            }
        });

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        this.mViewState="";
        this.mBaseURL=intent.getStringExtra(Constant.JW_URL);//读取教务网地址
        if(mBaseURL==null){
            mBaseURL=Constant.BASE_URL;
        }
        init();
        return myBinder;
    }
    @Override
    public void login(User user) {

        //表单信息
        FormBody body=new FormBody.Builder()
                .add("__VIEWSTATE", mViewState)
                .add("txtUserName", user.getName())
                .add("TextBox2", user.getPasswd())
                .add("txtSecretCode",user.getCheck())
                .add("RadioButtonList1", Constant.RADIO_BUTTON_LIST)
                .add("Button1","")
                .add("lbLanguage","")
                .add("hidPdrs","")
                .add("hidsc","")
                .build();

        Request request=new Request.Builder()
                .url(mBaseURL+Constant.LOGIN_URL)
                .post(body)
                .build();
        mUser=user;//记录登陆的用户
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                connectionError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body=response.body();
                String html=body.string();
                String  errorMessege="";
                //检测是否有登陆错误的信息，有则记录信息，否则登陆成功
                String p="<script language='javascript' defer>alert\\('([\\s\\S]+?)'\\);document\\.getElementById\\('([\\s\\S]+?)'\\)\\.focus\\(\\);</script>";
                Pattern pattern=Pattern.compile(p);
                Matcher m=pattern.matcher(html);
                Intent it=new Intent();
                if(m.find()){
                    //登陆失败
                    errorMessege=m.group(1);
                    it.setAction(BroadcastAction.LOGIN_FAIL);
                    it.putExtra(Constant.LOGIN_FAIL,errorMessege);
                }else {
                    it.setAction(BroadcastAction.LOGIN_SUCCESS);
                    it.putExtra(Constant.LOGIN_SUCCESS,"登陆成功");
                }
                sendBroadcast(it);

            }
        });

    }
    private void connectionError(){
        Log.i("连接失败！","连接失败！");
        Intent it=new Intent();
        String errorMessege="连接失败！";
        it.setAction(BroadcastAction.LOGIN_FAIL);
        it.putExtra(Constant.LOGIN_FAIL,errorMessege);
        sendBroadcast(it);
    }
}
