package com.babuwyt.consignee.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.bean.signno.SignNoEntity;

/**
 * Created by lenovo on 2017/11/6.
 */

public class CustomSignView extends LinearLayout {
    private Context mContext;
    private TextView tv_signno,tv_commapy,tv_shouhuofang,tv_address,tv_link_peisonal,tv_phone,tv_hetonghao
            ,tv_productname,tv_guige,tv_num,tv_fading_num,tv_chengjiaonum,tv_chengyunfang,tv_siji,tv_carno,tv_time;
    public CustomSignView(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public CustomSignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    public CustomSignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    private void init(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_customsign, this);
        tv_time=view.findViewById(R.id.tv_time);
        tv_carno=view.findViewById(R.id.tv_carno);
        tv_siji=view.findViewById(R.id.tv_siji);
        tv_chengyunfang=view.findViewById(R.id.tv_chengyunfang);
        tv_chengjiaonum=view.findViewById(R.id.tv_chengjiaonum);
        tv_fading_num=view.findViewById(R.id.tv_fading_num);
        tv_num=view.findViewById(R.id.tv_num);
        tv_guige=view.findViewById(R.id.tv_guige);
        tv_productname=view.findViewById(R.id.tv_productname);
        tv_hetonghao=view.findViewById(R.id.tv_hetonghao);
        tv_phone=view.findViewById(R.id.tv_phone);
        tv_link_peisonal=view.findViewById(R.id.tv_link_peisonal);
        tv_address=view.findViewById(R.id.tv_address);
        tv_signno=view.findViewById(R.id.tv_signno);
        tv_commapy=view.findViewById(R.id.tv_commapy);
        tv_shouhuofang=view.findViewById(R.id.tv_shouhuofang);
    }

    public void setData(SignNoEntity entity){
        if (entity!=null){
            tv_time.setText(entity.getSendTime());
            tv_carno.setText(entity.getDriverCarId());
            tv_siji.setText(entity.getDriverName());
            tv_chengyunfang.setText(entity.getCarrierName());
            tv_chengjiaonum.setText(entity.getTransAmount());
            tv_fading_num.setText(entity.getLegalAmount());
            tv_num.setText(entity.getAmount());
            tv_guige.setText(entity.getSpeciFication());
            tv_productname.setText(entity.getGoodName());
            tv_hetonghao.setText(entity.getCompactNo());
            tv_phone.setText(entity.getPhoneNum());
            tv_link_peisonal.setText(entity.getLinkMan());
            tv_address.setText(entity.getAddressDetails());
            tv_signno.setText(entity.getSignNo());
            tv_commapy.setText(entity.getShiperName());
            tv_shouhuofang.setText(entity.getConsigneName());
        }
    }

}
