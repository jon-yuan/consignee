package com.babuwyt.consignee.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseFragment;
import com.babuwyt.consignee.util.UHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lenovo on 2017/11/6.
 */
@ContentView(R.layout.fragment_signdetailstwo)
public class SignDetailsFragmentTwo extends BaseFragment {

    @ViewInject(R.id.group_num)
    RadioGroup group_num;
    @ViewInject(R.id.group_ok)
    RadioGroup group_ok;
    @ViewInject(R.id.et_remark)
    EditText et_remark;
    @ViewInject(R.id.tv_confirm)
    TextView tv_confirm;

    private int numTrue=1;//数量正确
    private int okTrue=1;//包装正确
    @Override
    public void onVisible() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        group_num.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio1:
                        numTrue=1;
                        break;
                    case R.id.radio2:
                        numTrue=0;
                        break;
                }
            }
        });
        group_ok.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio3:
                        okTrue=1;
                        break;
                    case R.id.radio4:
                        okTrue=0;
                        break;
                }
            }
        });
    }
    @Event(value = {R.id.tv_confirm})
    private void getE(View v){
        switch (v.getId()){
            case R.id.tv_confirm:
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("numTrue",numTrue);
                    jsonObject.put("okTrue",okTrue);
                    jsonObject.put("input",et_remark.getText().toString());
                    confirmCallBack.callback(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    public interface confirmCallBack<T>{
        void callback(T o);
    }
    private confirmCallBack confirmCallBack;
    public void setConfirmCallBack(confirmCallBack confirmCallBack){
        this.confirmCallBack=confirmCallBack;
    }
}
