package com.babuwyt.consignee.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

/**
 * Created by lenovo on 2017/11/3.
 */
@ContentView(R.layout.activity_changepsd)
public class ChangePsdActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
    }
}
