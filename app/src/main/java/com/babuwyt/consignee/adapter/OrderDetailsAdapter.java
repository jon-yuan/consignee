package com.babuwyt.consignee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.DensityUtils;
import com.babuwyt.consignee.util.request.ImageOptions;
import com.babuwyt.consignee.util.request.XUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 */

public class OrderDetailsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> mList;
    private ViewHolder holder;
    private boolean isShow=true;
    public OrderDetailsAdapter(Context context){
        this.context=context;
        mList=new ArrayList<String>();
    }
    public void setmList(ArrayList<String> list){
        if (list!=null){
            mList=list;
        }
    }
    @Override
    public int getCount() {

        return isShow == true ? mList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.adapter_orderdetalis_item,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        int sWidth= DensityUtils.deviceWidthPX(context);
        int space=DensityUtils.dip2px(context,(float) 55);
        holder.image.getLayoutParams().height=(sWidth-space)/4;
        holder.image.getLayoutParams().width=(sWidth-space)/4;

//        x.image().bind(holder.image, BaseURL.BASE_IMAGE_URI+mList.get(i), XUtil.options(false));
        x.image().bind(holder.image, BaseURL.BASE_IMAGE_URI+mList.get(i), ImageOptions.options(ImageView.ScaleType.CENTER_CROP));
        return view;
    }
    class ViewHolder{
        @ViewInject(R.id.image)
        ImageView image;
    }

}
