package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.ClientApp;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.bean.login.User;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lenovo on 2017/9/10.
 */
@ContentView(R.layout.activity_bindtel)
public class BindTelActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.et_tel)
    EditText et_tel;
    @ViewInject(R.id.tv_authcode)
    EditText tv_authcode;
    @ViewInject(R.id.tv_auth)
    TextView tv_auth;
    private int time=60;
    private Timer timer;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(time>0){
                tv_auth.setText("("+time+") 秒后获取");
                tv_auth.setEnabled(false);
                tv_auth.setBackgroundResource(R.color.line);
            }else{

                timer.cancel();
                timer=null;
                time=60;
                tv_auth.setText("获取验证码");
                tv_auth.setEnabled(true);
                tv_auth.setBackgroundResource(R.color.btn_blue);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
    }
    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Event(value = {R.id.btn_mt,R.id.tv_auth})
    private void getE(View v){
        switch (v.getId()){
            case R.id.btn_mt:
                BindTel();
                break;
            case R.id.tv_auth:
                getCode();
                break;
        }
    }

    private void BindTel(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("userId",SessionManager.getInstance().getUser().getFid());
        map.put("authCode",tv_authcode.getText().toString());
        map.put("userPhone",et_tel.getText().toString().trim());
        mDialog.showDialog();
        XUtil.PostJson(BaseURL.BIND_PHONE, map, new ResponseCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess()){
                    User user=SessionManager.getInstance().getUser();
                    user.setFiphone(et_tel.getText().toString());
                    ((ClientApp) getApplication()).saveLoginUser(user);
                    Intent intent=new Intent();
                    intent.setClass(BindTelActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                UHelper.showToast(BindTelActivity.this,result.getMsg());
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
            }
        });
    }
    //获取验证码
    private void getCode(){
        if (!UHelper.isPhone(et_tel.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.PROMPT_PHONE_FORMAT_IS_NOT_TRUE));
            return;
        }
        Map<String,String> map=new HashMap<String, String>();
        map.put("type","4");
        map.put("userPhone",et_tel.getText().toString().trim());

        XUtil.PostJson(BaseURL.GET_AUTHCODE, map, new ResponseCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                if (result.isSuccess()){
                    timeDown();
                }
                UHelper.showToast(BindTelActivity.this,result.getMsg());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
    private void timeDown(){
        if (!UHelper.isPhone(et_tel.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.PROMPT_PHONE_FORMAT_IS_NOT_TRUE));
            return;
        }
        timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                time--;
                Message msg=handler.obtainMessage();
                msg.what=1;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask,0,1000);

    }

}
