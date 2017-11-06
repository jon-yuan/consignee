package com.babuwyt.consignee.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.ReceiptAdapter;
import com.babuwyt.consignee.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/6.
 * 签收列表
 */
@ContentView(R.layout.activity_receipt)
public class ReceiptListActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.listview)
    ListView listview;
    private ReceiptAdapter mAdapter;
    private ArrayList<String> mList;
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

        mList=new ArrayList<String>();
        mList.add("DOC20170818-16");
        mList.add("DOC20170818-15");
        mList.add("DOC20170818-14");
        mAdapter=new ReceiptAdapter(this);
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
    }
}
