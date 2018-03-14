package com.tencent.qcloud.timchat.ui.customview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.qcloud.timchat.R;
import com.tencent.qcloud.timchat.ui.Instant;
import com.tencent.qcloud.timchat.ui.Item_display;
import com.tencent.qcloud.timchat.ui.Side_Content;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuchaochao on 2018/3/2.
 */

public class Add_Item extends AppCompatActivity {
    private EditText editText;
    private EditText resume;
    private ImageView imageView;
    private Button button;
    private Bitmap temp_bitmap=null;
    private static final int GALLERY_REQUEST_CODE=1;
    private Uri uri = null;
    private List<Item_display> item = new ArrayList<Item_display>();
    private String title = null;
    private String content = null;
    private String photo_path = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
       editText =(EditText) findViewById(R.id.title);
       resume = (EditText) findViewById(R.id.resume);
       imageView = (ImageView) findViewById(R.id.image_match);
       button = (Button) findViewById(R.id.release);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                title = editText.getText().toString().trim();
                content = resume.getText().toString().trim();
               Intent intent = new Intent("com.tencent.qcloud.timchat.ui.MainActivity");
               Bundle bundle = new Bundle();
               photo_path = String.valueOf(uri);
               bundle.putString(Instant.uri,photo_path);
               bundle.putString(Instant.title,title);
               bundle.putString(Instant.content,content);
               intent.putExtra(Instant.bundle,bundle);
               if(title.isEmpty()||content.isEmpty())
               {
                   Toast.makeText(Add_Item.this,"赛事名或者赛事简介为空",Toast.LENGTH_LONG).show();
               }
               else{
                   if(photo_path==null)
                   {
                       Toast.makeText(Add_Item.this,"赛事宣传海报为空",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       sendBroadcast(intent);
                       finish();
                   }
               }
           }
       });
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               intent.setType("image/*");
               startActivityForResult(intent, GALLERY_REQUEST_CODE);
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUEST_CODE) {
            if (data == null)
                return;
            if (resultCode == RESULT_OK) {
                 uri = data.getData();
                InputStream in = null;
                try {
                    in =  getContentResolver().openInputStream(uri);
                     Bitmap bitmap = BitmapFactory.decodeStream(in);
                    imageView.setImageBitmap(bitmap);
                    in.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
