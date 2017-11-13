package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.HistoryAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.SelectTypeEntity;
import com.babuwyt.consignee.bean.order.HistoryOrderBean;
import com.babuwyt.consignee.bean.order.HistoryOrderEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.bigkoo.pickerview.OptionsPickerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/7.
 */
@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity {
    @ViewInject(R.id.listview)
    ListView listview;
    @ViewInject(R.id.tv_type)
    TextView tv_type;
    @ViewInject(R.id.tv_serach)
    TextView tv_serach;
    @ViewInject(R.id.et_input)
    EditText et_input;
    private HistoryAdapter mAdapter;
    private ArrayList<HistoryOrderEntity> mList;
    private String inputeStr;
    private int type = 1;
    private int searchType=0;//0取消 1搜索
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
        getHttp();
    }

    private void init() {
        mAdapter = new HistoryAdapter(this);
        mList = new ArrayList<HistoryOrderEntity>();

        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(SearchActivity.this, SignDetailsMoreActivity.class);
                intent.putExtra("orderId", mList.get(i).getOrderId());
                startActivity(intent);
            }
        });
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() <= 0) {
                    tv_serach.setText(getString(R.string.cancel));
                    searchType=0;
                } else {
                    tv_serach.setText(getString(R.string.action_search));
                    searchType=1;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Event(value = {R.id.tv_type, R.id.tv_serach})
    private void getE(View view) {
        switch (view.getId()) {
            case R.id.tv_type:
                showType();
                break;
            case R.id.tv_serach:
                if (searchType==0){
                    finish();
                }else {
                    getHttp();
                }
                break;
        }
    }

    private void getHttp() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("pageNum", 1);
        map.put("type", 1);
        map.put("inputeStr", inputeStr);
        map.put("startTime", "");
        map.put("endTime", "");
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.HISTORY_ORDER, map, new ResponseCallBack<HistoryOrderBean>() {
            @Override
            public void onSuccess(HistoryOrderBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess()) {
                    mList.clear();
                    mList.addAll(result.getObj());
                    mAdapter.notifyDataSetChanged();
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
     * 打开选择类型
     */
    private void showType() {

        Resources res = getResources();
        String[] transaction = res.getStringArray(R.array.search_type);
        final ArrayList<SelectTypeEntity> list = new ArrayList<SelectTypeEntity>();
        for (int i = 0; i < transaction.length; i++) {
            SelectTypeEntity entity = new SelectTypeEntity();
            entity.setStr(transaction[i]);
            entity.setType(i + 1);
            list.add(entity);
        }
        final OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tv_type.setText(list.get(options1).getStr());
                type = list.get(options1).getType();
            }
        }).build();
        pvOptions.setPicker(list);
        pvOptions.show();
    }
}
