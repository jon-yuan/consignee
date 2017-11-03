package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by lenovo on 2017/10/30.
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {

    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
    }

    private void init() {
        intent=new Intent();

    }


    @Event(value = {R.id.tv_logout,R.id.linearyout_changepsd})
    private void gete(View v) {
        switch (v.getId()) {
            case R.id.tv_logout:

                break;
            case R.id.linearyout_changepsd:
                intent.setClass(this,ChangePsdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
