package com.tencent.qcloud.timchat.dfx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by zouyi on 2018/3/4.
 */

import com.tencent.qcloud.timchat.R;

import static android.content.ContentValues.TAG;
public class School extends AppCompatActivity {
    private String[] school = {"南京邮电大学通达学院","南京林业大学","南京大学","河海大学","南京理工大学","南京师范大学",
    "南京中医药大学","南京财经大学","南通大学","南京体育学院","江苏大学"};
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(School.this,android.R.layout.simple_list_item_1,school);
        final ListView listView =  (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Log.e(TAG, "onItemClick: "+position);
                switch(position){
                    case 0:{
                        LoginActivity.jwUrl="http://42.247.7.170/";
                        Constant.CHECK_IMAGE_URL = "/CheckCode.aspx";
                        break;
                    }
                    case 1:{
                        LoginActivity.jwUrl="http://jwk.njfu.edu.cn/";
                        Constant.CHECK_IMAGE_URL = "/sys/ValidateCode.aspx";
                        break;
                    }
                    case 2:{
                        LoginActivity.jwUrl="http://elite.nju.edu.cn/jiaowu/";
                        Constant.CHECK_IMAGE_URL = "/ValidateCode.jsp";
                        break;
                    }
                    case 3:{
                        LoginActivity.jwUrl="http://202.119.113.135/";
                        Constant.CHECK_IMAGE_URL = "/validateCodeAction.do";
                        break;
                    }
                    case 4:{
                        LoginActivity.jwUrl="http://202.119.81.112:8080/";
                        Constant.CHECK_IMAGE_URL = "/verifycode.servlet";
                        break;
                    } case 5:{
                        LoginActivity.jwUrl="http://jwk.njfu.edu.cn/";
                        Constant.CHECK_IMAGE_URL = "/sys/ValidateCode.aspx";
                        break;
                    } case 6:{
                        LoginActivity.jwUrl="http://jwk.njfu.edu.cn/";
                        Constant.CHECK_IMAGE_URL = "/sys/ValidateCode.aspx";
                        break;
                    } case 7:{
                        LoginActivity.jwUrl="http://202.195.210.218/";
                        Constant.CHECK_IMAGE_URL = "/validateCodeAction.do";
                        break;
                    } case 8:{
                        LoginActivity.jwUrl="http://210.28.81.11/";
                        Constant.CHECK_IMAGE_URL = "/CheckCode.aspx";
                        break;
                    } case 9: {
                        LoginActivity.jwUrl = "http://jwgl.ntu.edu.cn/cjcx/";
                        Constant.CHECK_IMAGE_URL = "/cjcx/checkImage.aspx";
                        break;
                    }
                    case 10:{
                        LoginActivity.jwUrl="http://jwc.nipes.cn/";
                        Constant.CHECK_IMAGE_URL = "/sys/ValidateCode.aspx";
                        break;
                    }
                    case 11:{
                        LoginActivity.jwUrl="http://my.ujs.edu.cn/";
                        Constant.CHECK_IMAGE_URL = "/captchaGenerate.portal";
                        break;
                    }
                    default:
                }
                Intent intent = new Intent(School.this,LoginActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

}
