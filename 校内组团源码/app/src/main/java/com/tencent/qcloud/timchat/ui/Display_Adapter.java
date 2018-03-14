package com.tencent.qcloud.timchat.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tencent.qcloud.timchat.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by wuchaochao on 2018/2/13.
 */

public class Display_Adapter extends ArrayAdapter<Item_display> {
    private final int resourceId;
    public Display_Adapter(@NonNull Context context, int resource,List<Item_display> objects) {
        super(context, resource,objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         Item_display item_display = (Item_display) getItem(position);
         View view = LayoutInflater.from(getContext()).inflate(R.layout.item_dispaly,parent,false);
        // boolean result = view.isHardwareAccelerated();
         //Log.e(TAG, "getView: =========="+result );
         ImageView image = (ImageView) view.findViewById(R.id.title_view);
         TextView  title = (TextView) view.findViewById(R.id.title);
         TextView  holder = (TextView) view.findViewById(R.id.holder);
         TextView  time = (TextView) view.findViewById(R.id.time);
         TextView replyId = (TextView) view.findViewById(R.id.replyId);
         //image.setImageResource(item_display.getImageID());
        if(item_display.getImageID() == 0) {
            Picasso
                    .with(getContext())
                    .load(item_display.getUri())
                    .fit()
                    .into(image);
        }
        else {
            Picasso
                    .with(getContext())
                    .load(item_display.getImageID())
                    .fit() // will explain later
                    .into(image);
        }
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
         title.setText(item_display.getTitle());
         holder.setText(item_display.getHolder());
         time.setText(formatter.format(item_display.getTime()));
         replyId.setText(item_display.getReply());
        return  view;
    }
}
