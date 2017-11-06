package com.babuwyt.consignee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.consignee.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/6.
 */

public class ReceiptAdapter extends BaseAdapter {
    private ArrayList<String> mList;
    private Context mContext;

    public ReceiptAdapter(Context context){
        mContext=context;
        mList=new ArrayList<String>();
    }

    public void setmList(ArrayList<String> list){
        if (list!=null){
            mList=list;
        }
    }

    @Override
    public int getCount() {
        return mList.size();
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
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.adapter_receipt,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
            holder.img_qianshou.setVisibility(i==0?View.VISIBLE:View.INVISIBLE);
            holder.img_next.setVisibility(i==0?View.INVISIBLE:View.VISIBLE);
            holder.tv_num.setText(mList.get(i));

        return view;
    }

    class ViewHolder{
        @ViewInject(R.id.img_qianshou)
        ImageView img_qianshou;
        @ViewInject(R.id.img_next)
        ImageView img_next;
        @ViewInject(R.id.tv_num)
        TextView tv_num;
    }
}
