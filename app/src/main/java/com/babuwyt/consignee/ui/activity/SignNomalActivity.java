package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.SignDetailsAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.bean.signno.SignNoBean;
import com.babuwyt.consignee.bean.signno.SignSuccessBean;
import com.babuwyt.consignee.bean.signno.URLBean;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.ui.fragment.SignDetailsFragmentOne;
import com.babuwyt.consignee.ui.fragment.SignDetailsFragmentTwo;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.babuwyt.consignee.views.CustomViewPager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
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
public class SignNomalActivity extends BaseActivity {
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
        fragmentTwo.setConfirmCallBack(new SignDetailsFragmentTwo.confirmCallBack() {
            @Override
            public void callback(Object o) {
                JSONObject object= (JSONObject) o;
                try {
                    int qtycheck=object.getInt("qtycheck");
                    int packcheck=object.getInt("packcheck");
                    String remark=object.getString("remark");
                    goSign(qtycheck,packcheck,remark);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                    UHelper.showToast(SignNomalActivity.this,result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
            }
        });
    }


    private void goSign(int packcheck, int qtycheck, final String remark){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("packcheck", packcheck);
        map.put("qtycheck",qtycheck);
        map.put("remark",remark);
        map.put("signNo",signNo);
        map.put("fid",SessionManager.getInstance().getUser().getFid());
        map.put("phoneNum",SessionManager.getInstance().getUser().getFiphone());
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.SIGN_DO,map,new ResponseCallBack<SignSuccessBean>(){
            @Override
            public void onSuccess(SignSuccessBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();

                if (result.isSuccess()){
                    Intent intent=new Intent();
                    intent.setClass(SignNomalActivity.this,SignDoActivity.class);
                    intent.putExtra("URL",result.getObj());
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
                Log.d("错误==",ex+"");
            }
        });
    }
}
