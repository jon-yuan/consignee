package com.babuwyt.consignee.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;

import org.w3c.dom.ProcessingInstruction;
import org.xutils.common.util.MD5;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lenovo on 2017/11/3.
 * 修改密码
 */
@ContentView(R.layout.activity_changepsd)
public class ChangePsdActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.et_psd)
    EditText et_psd;
    @ViewInject(R.id.et_psd_again)
    EditText et_psd_again;
    @ViewInject(R.id.et_authcode)
    EditText et_authcode;
    @ViewInject(R.id.tv_authcode)
    TextView tv_authcode;
    @ViewInject(R.id.tv_confirm)
    TextView tv_confirm;
    @ViewInject(R.id.tv_phone)
    TextView tv_phone;
    private String userPhone;
    private int time = 60;
    private Timer timer;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (time > 0) {
                tv_authcode.setText("(" + time + ") 秒后获取");
                tv_authcode.setEnabled(false);
            } else {

                timer.cancel();
                timer = null;
                time = 60;
                tv_authcode.setText("获取验证码");
                tv_authcode.setEnabled(true);
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
        tv_authcode.setEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        userPhone = SessionManager.getInstance().getUser().getFiphone();
        tv_phone.setText(userPhone);

    }


    @Event(value = {R.id.tv_confirm, R.id.tv_authcode})
    private void getE(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                isEmpty();
                break;
            case R.id.tv_authcode:
                getAuthCode();
                break;
        }
    }

    private void isEmpty() {
        String userPsd = et_psd.getText().toString().trim();
        String authCode = et_authcode.getText().toString().trim();
        if (!UHelper.isPsd(userPsd)) {
            UHelper.showToast(this, getString(R.string.PROMPT_PSD_FORMAT_IS_NOT_TRUE));
            return;
        }
        if (!userPsd.equalsIgnoreCase(et_psd_again.getText().toString())) {
            UHelper.showToast(this, getString(R.string.MAKETRUE_PSD_IS_SUCCESS));
            return;
        }
        if (TextUtils.isEmpty(authCode)) {
            UHelper.showToast(this, getString(R.string.PROMPT_AUTHCODE_IS_NOT_EMPTY));
            return;
        }
        getHttp(userPsd, authCode);
    }

    /**
     * 修改密码
     * @param userPsd
     * @param authCode
     */
    private void getHttp(String userPsd, String authCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("token", SessionManager.getInstance().getUser().getWebtoken());
        map.put("userPhone", userPhone);
        map.put("userPsd", MD5.md5(userPsd));
        map.put("authCode", authCode);
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.CHANGEPSD, map, new ResponseCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess()) {
                    finish();
                    UHelper.showToast(ChangePsdActivity.this, getString(R.string.CHANGE_SUCCESS));
                } else {
                    UHelper.showToast(ChangePsdActivity.this, result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
                Log.d("==12==",ex+"");
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getAuthCode() {
        if (!UHelper.isPhone(userPhone)) {
            UHelper.showToast(this, getString(R.string.PROMPT_PHONE_FORMAT_IS_NOT_TRUE));
            return;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", 3);
        map.put("userPhone", userPhone);
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.GET_AUTHCODE, map, new ResponseCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess()) {
                    timeDown();
                    UHelper.showToast(ChangePsdActivity.this, getString(R.string.HAS_SEND_TO));
                } else {
                    UHelper.showToast(ChangePsdActivity.this, result.getMsg());
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
     * 60秒倒计时
     */

    private void timeDown() {

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                time--;
                Message msg = handler.obtainMessage();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask, 0, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
