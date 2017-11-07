package com.babuwyt.consignee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.babuwyt.consignee.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/7.
 */

public class HistoryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mList;
    public HistoryAdapter(Context context){
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
            view= LayoutInflater.from(mContext).inflate(R.layout.adapter_history,null);

            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        return view;
    }

    class ViewHolder{

    }
}
