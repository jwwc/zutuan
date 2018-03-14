package com.tencent.qcloud.timchat.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.TIMConversationType;
import com.tencent.qcloud.timchat.R;

/**
 * Created by wuchaochao on 2018/2/13.
 */

public class Side_Content extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
         Bundle bundle = getIntent().getExtras();
         String title = bundle.getString("title");
         String holder = bundle.getString("holder");
         TextView title_big = (TextView) findViewById(R.id.content_title);
         TextView content_title = (TextView) findViewById(R.id.content);
         ImageView image_photo = (ImageView) findViewById(R.id.image_person);
         image_photo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Side_Content.this, ChatActivity.class);
                 intent.putExtra("identify", "86-13082565669");
                 intent.putExtra("type", TIMConversationType.C2C);
                 startActivity(intent);
                 finish();
             }
         });
         //TextView title_time = findViewById(R.id.);
         title_big.setText(title);
         content_title.setText(holder);
         //title_time.setText(time);
    }
}
