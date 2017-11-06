package com.babuwyt.consignee.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.babuwyt.consignee.R;

/**
 * Created by lenovo on 2017/11/6.
 */

public class CustomSignView extends LinearLayout {
    private Context mContext;

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
    }

}
