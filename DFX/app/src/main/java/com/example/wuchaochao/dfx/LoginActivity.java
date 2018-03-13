package com.example.wuchaochao.dfx;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by wuchaochao on 2018/1/26.
 */

public class LoginActivity extends AppCompatActivity{
    private EditText etCourseNumber;
    private EditText etCoursePassword;
    private Button btnCourseLogin;
    private EditText etCourseCheck;
    private ImageView ivCheckCode;
    private boolean isBinder = false;
    private MyBroadcastReceiver mReceiver;
    private DataService.DataBinder myBinder;
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (DataService.DataBinder) service;
            isBinder = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinder = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x123: {//登陆成功
                   // MainActivity mainActivity = new MainActivity();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    btnCourseLogin.setEnabled(false);
                    break;
                }
                case 0x124: {//获取验证码
                    Bundle bl = msg.getData();
                    byte[] data = bl.getByteArray(Constant.CHECK);
                    Bitmap bim = BitmapFactory.decodeByteArray(data, 0, data.length);
                    ivCheckCode.setImageBitmap(bim);
                    break;
                }
                case 0x125: {//登录失败
                    Bundle bl = msg.getData();
                    String error = bl.getString(Constant.LOGIN_FAIL);
                    myBinder.getCheckImg();//重新获取验证码
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                    break;
                }
              }
            }
        };
        //private MyBroadcastReceiver mReceiver;
        private void login() {

            String courseName = etCourseNumber.getText().toString().trim();
            String coursePassword = etCoursePassword.getText().toString();
            String checkCode = etCourseCheck.getText().toString().trim();
            User user = createUser(courseName, coursePassword, checkCode);
            myBinder.login(user);

        }
        public User createUser(String name, String passwd, String check) {
            User user = new User();
            user.setName(name);
            user.setPasswd(passwd);
            user.setCheck(check);
            return user;
        }
       private void getCode() {

        myBinder.getCheckImg();
      }

    public void initView() {
            setContentView(R.layout.login);
            etCourseNumber = (EditText) findViewById(R.id.et_course_username);
            etCoursePassword = (EditText) findViewById(R.id.et_course_password);
            btnCourseLogin = (Button) findViewById(R.id.btn_course_login);
            etCourseCheck = (EditText) findViewById(R.id.et_course_check);
            ivCheckCode = (ImageView) findViewById(R.id.iv_checkCode);
            ivCheckCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCode();
                }
            });
        btnCourseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"wo dian ji le",Toast.LENGTH_LONG).show();
                login();
            }
        });
        }
        protected void initData() {

            mReceiver = new MyBroadcastReceiver();
            String jwUrl="http://42.247.7.170/";
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BroadcastAction.LOGIN_SUCCESS);
            intentFilter.addAction(BroadcastAction.LOGIN_FAIL);
            intentFilter.addAction(BroadcastAction.CHECK_IMG);
            intentFilter.addAction(BroadcastAction.COURSE_TABLE);
            intentFilter.addAction(BroadcastAction.COURSE_XND);
            intentFilter.addAction(BroadcastAction.SCORE);
            registerReceiver(mReceiver, intentFilter);
            Intent it = new Intent(this, DataService.class);
            it.putExtra(Constant.JW_URL, jwUrl);
            bindService(it, sc, Context.BIND_AUTO_CREATE);

        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (isBinder) {
                unbindService(sc);
            }
            unregisterReceiver(mReceiver);
        }

        /**
         * 广播监听类
         */
        private class MyBroadcastReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Message msg = new Message();
                Bundle bdl = intent.getExtras();
                msg.setData(bdl);
                switch (action) {
                    case BroadcastAction.LOGIN_SUCCESS: {
                        //登陆成功
                        msg.what = 0x123;
                        break;
                    }
                    case BroadcastAction.CHECK_IMG: {
                        //获取验证码
                        msg.what = 0x124;
                        break;
                    }
                    case BroadcastAction.LOGIN_FAIL: {
                        //登录失败
                        msg.what = 0x125;
                        break;
                    }
                }
                mHandler.sendMessage(msg);
            }
        }


}
