package com.tencent.qcloud.timchat.ui;

import android.net.Uri;

import java.util.Date;

/**
 * Created by wuchaochao on 2018/2/13.
 */

public class Item_display {
    private String title;
    private int imageID;
    private String holder;
    private Date time;
    private int replyId;
    private String uri;
    private static int i = 0;
    public Item_display(String title, int imageID, String uri, String holder, Date time, int replyId) {
        this.title = title;
        this.imageID = imageID;
        this.holder = holder;
        this.time=time;
        this.replyId=replyId;
        this.uri = uri;
    }
    public String getTitle() {
        return title;
    }

    public int getImageID() {
        return imageID;
    }

    public String getHolder() {
        return holder;
    }

    public Date getTime(){
        //String time=Year+"-"+Month+"-"+Day;
        return time;
    }
    public String getUri(){
        return uri;
    }
    public String getReply(){
        return Integer.toString(replyId)+"人关注";
    }
    public int  getRt(){
        return replyId;
    }

    public void setTime(Date time){
        this.time=time;
    }

    public void setReplyId(int replyId){
        this.replyId=replyId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

}
