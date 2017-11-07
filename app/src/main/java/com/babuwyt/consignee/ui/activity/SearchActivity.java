package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.HistoryAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.bigkoo.pickerview.OptionsPickerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/7.
 */
@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity {
    @ViewInject(R.id.listview)
    ListView listview;
    @ViewInject(R.id.tv_type)
    TextView tv_type;
    private HistoryAdapter mAdapter;
    private ArrayList<String> mList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
    }

    private void init() {
        mAdapter=new HistoryAdapter(this);
        mList=new ArrayList<String>();
        mList.add("1");
        mList.add("1");
        mList.add("1");
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(SearchActivity.this,LookSignNoActivity.class));
            }
        });
    }

    @Event(value = {R.id.tv_type})
    private void getE(View view){
        switch (view.getId()){
            case R.id.tv_type:
                showType();
                break;
        }
    }

    /**
     * 打开选择类型
     */
    private void showType() {

        Resources res = getResources();
        String[] transaction = res.getStringArray(R.array.search_type);
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < transaction.length; i++) {

            list.add(transaction[i]);
        }
        final OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tv_type.setText(list.get(options1));
            }
        }).build();
        pvOptions.setPicker(list);
        pvOptions.show();
    }
}
