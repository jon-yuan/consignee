package com.babuwyt.consignee.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.signno.SignNoBean;
import com.babuwyt.consignee.bean.signno.SignNoEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.babuwyt.consignee.views.BannerView;
import com.babuwyt.consignee.views.CustomSignView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/6.
 */
@ContentView(R.layout.activity_looksignno)
public class SignDetailsMoreActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.bannerview)
    BannerView bannerview;
    private ArrayList<View> vList;
    private ArrayList<SignNoEntity> mList;
    private String orderId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
        goHttp();
    }

    private void init() {
        orderId=getIntent().getStringExtra("orderId");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mList=new ArrayList<SignNoEntity>();
        vList=new ArrayList<View>();

        bannerview.addChangeLister(new BannerView.ChangeLister() {
            @Override
            public void onChangeLister(int position) {
                title.setText(mList.get(position).getSignNo());
            }
        });
    }
    private void goHttp() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("orderId", orderId);
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.ORDERDETAILS, map, new ResponseCallBack<SignNoBean>() {
            @Override
            public void onSuccess(SignNoBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess()) {
                    if (result.getObj()!=null){
                        mList.clear();
                        mList.addAll(result.getObj());
                        setmList(mList);
                    }
                } else {
                    UHelper.showToast(SignDetailsMoreActivity.this, result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
            }
        });
    }

    private void setmList(ArrayList<SignNoEntity> mList){
        for (SignNoEntity entity : mList){
            CustomSignView view=new CustomSignView(this);
            view.setData(entity);
            vList.add(view);
        }
        title.setText(mList.get(0).getSignNo());
        bannerview.setViewList(vList);
    }
}
