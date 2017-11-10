package com.babuwyt.consignee.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseFragment;
import com.babuwyt.consignee.bean.signno.SignNoEntity;
import com.babuwyt.consignee.views.CustomSignView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lenovo on 2017/11/3.
 */
@ContentView(R.layout.fragment_signdetailsone)
public class SignDetailsFragmentOne extends BaseFragment {
    @ViewInject(R.id.customsignview)
    CustomSignView customsignview;
    private SignNoEntity mEntity;
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
                if (mEntity==null){
                    return;
                }
                callBack.callBackOne(mEntity.getSignNo());
                break;
        }
    }

    public void setCustomsignview(SignNoEntity entity){
        this.mEntity=entity;
        customsignview.setData(mEntity);
    }
    public interface  FragmentOneCallBack{
        void callBackOne(String s);
    }
    private FragmentOneCallBack callBack;
    public void setCallBack(FragmentOneCallBack callBack){
        this.callBack=callBack;
    }
}
