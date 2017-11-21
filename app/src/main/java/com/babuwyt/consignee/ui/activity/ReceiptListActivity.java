package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.ReceiptAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.signno.SignNoBean;
import com.babuwyt.consignee.bean.signno.SignNoEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/6.
 * 签收列表
 */
@ContentView(R.layout.activity_receipt)
public class ReceiptListActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.listview)
    ListView listview;
    private ReceiptAdapter mAdapter;
    private ArrayList<SignNoEntity> mList;
    private String rqcode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
        getHttp();
    }

    private void init() {
        rqcode=getIntent().getStringExtra("rqcode");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mList=new ArrayList<SignNoEntity>();
        mAdapter=new ReceiptAdapter(this);
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                if (mList.get(i).getSignState()==1){
                    intent.setClass(ReceiptListActivity.this,SignDetailsActivity.class);
//                    intent.setClass(ReceiptListActivity.this,SignNomalActivity.class);
                    intent.putExtra("signNo",mList.get(i).getSignNo());
//                    intent.putExtra("signNo","DOC20171106-04");
                }else{
                    intent.setClass(ReceiptListActivity.this,SignNomalActivity.class);
                    intent.putExtra("signNo",mList.get(i).getSignNo());
//                    intent.putExtra("signNo","DOC20171106-04");
                }
                startActivity(intent);
            }
        });
    }

    private void getHttp(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("driverOrderNumber",rqcode);
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.SIGN_NOTE,map,new ResponseCallBack<SignNoBean>(){
            @Override
            public void onSuccess(SignNoBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess() && result.getObj()!=null){
                    mList.addAll(result.getObj());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
            }
        });
    }
}
