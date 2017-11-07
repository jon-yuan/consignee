package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.HistoryAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.bigkoo.pickerview.TimePickerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lenovo on 2017/11/7.
 */
@ContentView(R.layout.activity_historyorader)
public class HistoryOrderActivity extends BaseActivity{
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.listView)
    ListView listView;
    @ViewInject(R.id.tv_ordernum)
    TextView tv_ordernum;
    @ViewInject(R.id.start_time)
    TextView start_time;
    @ViewInject(R.id.end_time)
    TextView end_time;

    private HistoryAdapter mAdapter;
    private ArrayList<String> mList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
    }

    private void init() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        startActivity(new Intent(HistoryOrderActivity.this,SearchActivity.class));
                        break;
                }
                return true;
            }
        });
        mAdapter=new HistoryAdapter(this);
        mList=new ArrayList<String>();
        mList.add("1");
        mList.add("1");
        mList.add("1");
        mAdapter.setmList(mList);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(HistoryOrderActivity.this,LookSignNoActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.history_order, menu);
        return true;
    }

    @Event(value = {R.id.end_time,R.id.start_time})
    private void getE(View v){
        switch (v.getId()){
            case R.id.end_time:
                showTimeSelect(end_time);
                break;

            case R.id.start_time:
                showTimeSelect(start_time);
                break;
        }
    }

    /**
     * 选择时间
     */

    private void showTimeSelect(final TextView textView){
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat timeformat=new SimpleDateFormat("yyyy年MM月dd号");
                String time=timeformat.format(date);
                textView.setText(time);
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setLabel("年","月","日","","","")//默认设置为年月日时分秒
                .build();
        pvTime.show();

    }



    @Override
    protected void onResume() {
        super.onResume();
        tv_ordernum.setText(mList.size()+"");
    }
}
