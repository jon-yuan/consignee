package com.babuwyt.consignee.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.SignDetailsAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.signno.SignNoBean;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.ui.fragment.SignDetailsFragmentOne;
import com.babuwyt.consignee.ui.fragment.SignDetailsFragmentTwo;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.babuwyt.consignee.views.CustomViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/6.
 * 电子签收详情
 */
@ContentView(R.layout.activity_sign)
public class SignActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.viewpager)
    CustomViewPager viewpager;
    private SignDetailsAdapter mAdapter;
    private ArrayList<Fragment> mList;
    private SignDetailsFragmentOne fragmentOne;
    private SignDetailsFragmentTwo fragmentTwo;

    private String signNo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
        getHttp();
    }

    private void init() {
        signNo=getIntent().getStringExtra("signNo");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mList=new ArrayList<Fragment>();
        fragmentOne=new SignDetailsFragmentOne();
        fragmentTwo=new SignDetailsFragmentTwo();
        mList.add(fragmentOne);
        mList.add(fragmentTwo);
        mAdapter=new SignDetailsAdapter(getSupportFragmentManager(),mList);
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(2);
        fragmentOne.setCallBack(new SignDetailsFragmentOne.FragmentOneCallBack() {
            @Override
            public void callBackOne(String s) {
                viewpager.setCurrentItem(1);
            }
        });
    }

    private void getHttp(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("signNo",signNo);
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.SIGNNOTEDETAILS,map,new ResponseCallBack<SignNoBean>(){
            @Override
            public void onSuccess(SignNoBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess() && result.getObj()!=null && result.getObj().size()>0){
                    fragmentOne.setCustomsignview(result.getObj().get(0));
                    title.setText(result.getObj().get(0).getSignNo());
                }else {
                    UHelper.showToast(SignActivity.this,result.getMsg());
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
