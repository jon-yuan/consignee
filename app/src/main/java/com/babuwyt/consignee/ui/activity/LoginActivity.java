package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.ClientApp;
import com.babuwyt.consignee.bean.login.LoginBean;
import com.babuwyt.consignee.bean.login.User;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.google.gson.Gson;

import org.xutils.common.util.MD5;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/26.
 */

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    //    @ViewInject(R.id.toolbar)
//    Toolbar toolbar;
    @ViewInject(R.id.tv_login)
    TextView tv_login;
    @ViewInject(R.id.et_userAct)
    EditText et_userAct;
    @ViewInject(R.id.et_psd)
    EditText et_psd;

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
    }

    private void init() {
        intent = new Intent();
    }


    @Event(value = {R.id.tv_login, R.id.tv_forgetpsd})
    private void getE(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                isEmpty();
                break;
            case R.id.tv_forgetpsd:
                intent.setClass(this, ForgetPsdActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void isEmpty() {
        String useract = et_userAct.getText().toString().trim();
        String userpsd = et_psd.getText().toString().trim();
        if (TextUtils.isEmpty(useract)) {
            UHelper.showToast(this, getString(R.string.PLEASE_INPUT_USERACT));
            return;
        }
        if (!UHelper.isPsd(userpsd)) {
            UHelper.showToast(this, getString(R.string.PROMPT_PSD_FORMAT_IS_NOT_TRUE));
            return;
        }
        goLoginIn(useract, userpsd);
    }

    private void goLoginIn(String userAct, String userPsd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userAct",userAct);
        map.put("userPsd", MD5.md5(userPsd));
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.LOGIN, map, new ResponseCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess()) {
                    User user = result.getObj();
                    if (user != null) {
                        isBindTel(user);
                    } else {
                        UHelper.showToast(LoginActivity.this, getString(R.string.login_error));
                    }
                } else {
                    UHelper.showToast(LoginActivity.this, result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
            }
        });
    }

    /**
     * 判断是否绑定了手机号
     */
    private void isBindTel(User user){
        ((ClientApp) getApplication()).saveLoginUser(user);
//        if (TextUtils.isEmpty(user.getFiphone())){
//            intent.setClass(LoginActivity.this,BindTelActivity.class);
//            startActivity(intent);
//            finish();
//        }else {
//            intent.setClass(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}
