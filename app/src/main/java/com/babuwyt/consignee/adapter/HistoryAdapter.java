package com.babuwyt.consignee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.bean.order.HistoryOrderEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/7.
 */

public class HistoryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<HistoryOrderEntity> mList;
    private TextView tv_ordernum;
    public HistoryAdapter(Context context,TextView textView) {
        mContext = context;
        tv_ordernum=textView;
        mList = new ArrayList<HistoryOrderEntity>();
    }
    public HistoryAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<HistoryOrderEntity>();
    }

    public void setmList(ArrayList<HistoryOrderEntity> list) {
        if (list != null) {
            mList = list;
        }

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (tv_ordernum!=null){
            tv_ordernum.setText(mList.size()+"");
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
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_history, null);
            x.view().inject(holder, view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_ordernum.setText(mList.get(i).getSignNo());
        holder.tv_luxian.setText(mList.get(i).getStartPlace()+" --- "+mList.get(i).getEndPlace());
        holder.tv_time.setText(mList.get(i).getStartTime()+" --- "+mList.get(i).getEndTime());
        return view;
    }

    class ViewHolder {
        @ViewInject(R.id.tv_ordernum)
        TextView tv_ordernum;
        @ViewInject(R.id.tv_luxian)
        TextView tv_luxian;
        @ViewInject(R.id.tv_time)
        TextView tv_time;
    }
}
