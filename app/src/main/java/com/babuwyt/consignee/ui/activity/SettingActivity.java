package com.babuwyt.consignee.ui.activity;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Event(value ={R.id.tv_logout})
    private void gete(View v){
        switch (v.getId()){
            case R.id.tv_logout:

                break;
        }
    }
}
