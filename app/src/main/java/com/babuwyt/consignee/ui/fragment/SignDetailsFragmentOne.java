package com.babuwyt.consignee.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by lenovo on 2017/11/3.
 */
@ContentView(R.layout.fragment_signdetailsone)
public class SignDetailsFragmentOne extends BaseFragment {
    @Override
    public void onVisible() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Event(value = {R.id.tv_confirm})
    private void getE(View v){
        switch (v.getId()){
            case R.id.tv_confirm:
                callBack.callBackOne("123");
                break;
        }
    }


    public interface  FragmentOneCallBack{
        void callBackOne(String s);
    }
    private FragmentOneCallBack callBack;
    public void setCallBack(FragmentOneCallBack callBack){
        this.callBack=callBack;
    }
}
