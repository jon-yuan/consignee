package com.babuwyt.consignee.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.views.BannerView;
import com.babuwyt.consignee.views.CustomSignView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/6.
 */
@ContentView(R.layout.activity_looksignno)
public class LookSignNoActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.bannerview)
    BannerView bannerview;
    private ArrayList<View> mList;
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
        mList=new ArrayList<View>();
        mList.add(new CustomSignView(this));
        mList.add(new CustomSignView(this));
        mList.add(new CustomSignView(this));
        mList.add(new CustomSignView(this));
        bannerview.setViewList(mList);
        bannerview.addChangeLister(new BannerView.ChangeLister() {
            @Override
            public void onChangeLister(int position) {
                title.setText("订单编号"+position);
            }
        });
    }
}
