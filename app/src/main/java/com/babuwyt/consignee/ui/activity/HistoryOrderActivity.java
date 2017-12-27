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
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.order.HistoryOrderBean;
import com.babuwyt.consignee.bean.order.HistoryOrderEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.DateUtils;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.bigkoo.pickerview.TimePickerView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @ViewInject(R.id.springview)
    SpringView springview;

    private HistoryAdapter mAdapter;
    private ArrayList<HistoryOrderEntity> mList;
    private int pageNum=1;
    private String startTime;
    private String endTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
        getHttp();
    }

    private void init() {
        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_search:
//                        startActivity(new Intent(HistoryOrderActivity.this,SearchActivity.class));
//                        break;
//                }
//                return true;
//            }
//        });
        mAdapter=new HistoryAdapter(this,tv_ordernum);
        mList=new ArrayList<HistoryOrderEntity>();

        mAdapter.setmList(mList);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(HistoryOrderActivity.this,OrderDetailsActivity.class);
                intent.putExtra("orderId",mList.get(i).getOrderId());
                startActivity(intent);
            }
        });
        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getHttp();
            }

            @Override
            public void onLoadmore() {
                getHttpMore();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.history_order, menu);
        return true;
    }

    @Event(value = {R.id.end_time,R.id.start_time})
    private void getE(View v){
        switch (v.getId()){
            case R.id.end_time:
                showTimeSelect(end_time,2);
                break;

            case R.id.start_time:
                showTimeSelect(start_time,1);
                break;
        }
    }

    private void getHttp(){
        pageNum=1;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("pageNum",pageNum);
        map.put("type",1);
        map.put("inputeStr","");
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        XUtil.PostJsonObj(BaseURL.HISTORY_ORDER,map,new ResponseCallBack<HistoryOrderBean>(){
            @Override
            public void onSuccess(HistoryOrderBean result) {
                super.onSuccess(result);
                springview.onFinishFreshAndLoad();
                if (result.isSuccess()){
                    mList.clear();
                    mList.addAll(result.getObj());
                    mAdapter.notifyDataSetChanged();
                }else {
                    UHelper.showToast(HistoryOrderActivity.this,result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                springview.onFinishFreshAndLoad();
            }
        });
    }
    private void getHttpMore(){
        pageNum++;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("pageNum",pageNum);
        map.put("type",1);
        map.put("inputeStr","");
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        XUtil.PostJsonObj(BaseURL.HISTORY_ORDER,map,new ResponseCallBack<HistoryOrderBean>(){
            @Override
            public void onSuccess(HistoryOrderBean result) {
                super.onSuccess(result);
                springview.onFinishFreshAndLoad();
                if (result.isSuccess()){
                    mList.addAll(result.getObj());
                    mAdapter.notifyDataSetChanged();
                }else {
                    UHelper.showToast(HistoryOrderActivity.this,result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                springview.onFinishFreshAndLoad();
            }
        });
    }

    /**
     * 选择时间
     */

    private void showTimeSelect(final TextView textView, final int type){
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String timeType="yyyy-MM-dd";
                SimpleDateFormat timeformat=new SimpleDateFormat(timeType);
                String time=timeformat.format(date);
                if (type==1){
                    long newtimes=DateUtils.getStringtoLong(timeformat.format(new Date()),timeType);
                    long selecttimes=DateUtils.getStringtoLong(time,timeType);
                    if (selecttimes>newtimes){
                        UHelper.showToast(HistoryOrderActivity.this,"开始时间不能大于当前时间");
                    }else {
                        startTime=time;
                        textView.setText(startTime);
                    }
                }else {
                    long starttimes=DateUtils.getStringtoLong(startTime,timeType);
                    long endtimes=DateUtils.getStringtoLong(time,timeType);

                    if (starttimes>=endtimes){
                        UHelper.showToast(HistoryOrderActivity.this,"结束时间不能小于或等于开始时间");
                    }else {
                        endTime=time;
                        textView.setText(endTime);
                        getHttp();
                    }

                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setLabel("年","月","日","","","")//默认设置为年月日时分秒
                .build();
        pvTime.show();

    }
}
