package com.tencent.qcloud.timchat.ui;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tencent.qcloud.timchat.R;
import com.tencent.qcloud.timchat.ui.customview.Add_Item;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public MainBroadcastReceiver mainBroadcastReceiver;
    private FloatingActionButton button;
    private  Display_Adapter display_adapter;
    private List<Item_display> item = new ArrayList<Item_display>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private  ListView listView;
    private SimpleDateFormat formatter ;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initItem();
        formatter  =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        IntentFilter intentFilter = new IntentFilter("com.tencent.qcloud.timchat.ui.MainActivity");
        mainBroadcastReceiver = new MainBroadcastReceiver();
        this.registerReceiver(mainBroadcastReceiver,intentFilter);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip_layout);
         display_adapter = new Display_Adapter(MainActivity.this,R.layout.item_dispaly,item);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(display_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item_display  show = item.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("title",show.getTitle());
                bundle.putString("holder",show.getHolder());
                bundle.putString("time", formatter.format(show.getTime()));
                bundle.putString("replyId",show.getReply());
                bundle.putInt("imageid",show.getImageID());
                // bundle.put
                Intent intent = new Intent(MainActivity.this,Side_Content.class);
                intent.putExtras(bundle);
                //finish();
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                            Toast.makeText(MainActivity.this,"刷新失败",Toast.LENGTH_LONG).show();

                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1200);
            }
        });
        button = (FloatingActionButton) findViewById(R.id.new_pro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Add_Item.class);
                startActivity(intent);
            }
        });
    }
    public boolean add(String title,int imageid,String uri,String holder,String time,int replyId)
    {
        Item_display item_display = new Item_display(title,imageid,uri,holder,new Date(System.currentTimeMillis()),replyId);
        item.add(item_display);
        return true;
    }
    private void initItem()
    {
        Item_display item_display1 = new Item_display("我士大夫卡就是打开房间啊可是打飞机啊看搜夺眶金发科技的疯狂",R.drawable.a,null,"中国任命姐发的佛挡杀佛士大夫艰苦实践",new Date(System.currentTimeMillis()),19);
        item.add(item_display1);
        Item_display item_display2 = new Item_display("sdkfk" ,R.drawable.b,null,"艾弗森的饭卡机刷卡的飞机ask附件是分开就较大幅度升级快捷方式的借款纠纷",new Date(System.currentTimeMillis()),23);
        item.add(item_display2);
        Item_display item_display3 = new Item_display("sdkfk" ,R.drawable.c,null,"艾弗森的饭卡机刷卡的飞机ask附件是分开就较大幅度升级快捷方式的借款纠纷",new Date(System.currentTimeMillis()),46);
        item.add(item_display3);
        Item_display item_display4 = new Item_display("sdkfk" ,R.drawable.d,null,"艾弗森的饭卡机刷卡的飞机ask附件是分开就较大幅度升级快捷方式的借款纠纷",new Date(System.currentTimeMillis()),9);
        item.add(item_display4);
        Item_display item_display5 = new Item_display("sdkfk" ,R.drawable.e,null,"艾弗森的饭卡机刷卡的飞机ask附件是分开就较大幅度升级快捷方式的借款纠纷",new Date(System.currentTimeMillis()),2);
        item.add(item_display5);
        Item_display item_display6 = new Item_display("sdkfk" ,R.drawable.f, null,"艾弗森的饭卡机刷卡的飞机ask附件是分开就较大幅度升级快捷方式的借款纠纷",new Date(System.currentTimeMillis()),29);
        item.add(item_display6);
        Item_display item_display7 = new Item_display("sdkfk" ,R.drawable.g, null,"艾弗森的饭卡机刷卡的飞机ask附件是分开就较大幅度升级快捷方式的借款纠纷",new Date(System.currentTimeMillis()),14);
        item.add(item_display7);
        Item_display item_display8 = new Item_display("sdkfk" ,R.drawable.h,null,"艾弗森的饭卡机刷卡的飞机ask附件是分开就较大幅度升级快捷方式的借款纠纷",new Date(System.currentTimeMillis()),2);
        item.add(item_display8);
            }
            private Handler handler = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what){
                        case 1:
                            Bundle bundle = msg.getData();
                            String title  =  bundle.getString(Instant.title);
                            String content = bundle.getString(Instant.content);
                            String uri = bundle.getString(Instant.uri);
                            Date date = new Date(System.currentTimeMillis());
                            Item_display item_display = new Item_display(title,0,uri,content,date,19);
                            item.add(item_display);
                            Collections.sort(item, new Comparator<Item_display>() {
                                @Override
                                public int compare(Item_display item_display, Item_display t1) {
                                    if(item_display.getTime().before(t1.getTime())){
                                        return 1;
                                    }
                                    return -1;
                                }
                            });
                            display_adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public class MainBroadcastReceiver extends BroadcastReceiver{
        Message message = new Message();
        @Override
        public void onReceive(Context context, Intent intent) {
           Bundle bundle    =  intent.getBundleExtra(Instant.bundle);
         message.setData(bundle);
         message.what=1;
         Message message2 = new Message();
         message2.what=message.what;
         message2.setData(message.getData());
         handler.sendMessage(message2);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mainBroadcastReceiver);
    }
}

