package com.babuwyt.consignee.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.babuwyt.consignee.R;
import com.babuwyt.consignee.bean.order.StatefollowEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/24.
 */

public class StateFollowAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<StatefollowEntity> mList;
    ViewHolder holder=null;
    public StateFollowAdapter(Context context){
        this.context=context;
        this.mList=new ArrayList<StatefollowEntity>();
    }
    public void setmList(ArrayList<StatefollowEntity> list){
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

        if (view==null){
            holder=new ViewHolder();

            view= LayoutInflater.from(context).inflate(R.layout.adapter_statefollow,null);
            x.view().inject(holder,view);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        holder.tv_time.setText(mList.get(i).getChecktims());
        holder.tv_addrss.setText(mList.get(i).getFromto()+": "+mList.get(i).getSsq()+mList.get(i).getFaddress());
        holder.tv_state.setText(mList.get(i).getTypetemp());
        if (TextUtils.isEmpty(mList.get(i).getChecktims())){
            holder.tv_time.setTextColor(context.getResources().getColor(R.color.black_666));
            holder.tv_addrss.setTextColor(context.getResources().getColor(R.color.black_666));
            holder.tv_state.setTextColor(context.getResources().getColor(R.color.black_666));
            holder.v_1.setTextColor(context.getResources().getColor(R.color.black_666));
            holder.tv_state.setVisibility(View.GONE);
            holder.img_state.setVisibility(View.VISIBLE);
        }else {
            holder.tv_time.setTextColor(context.getResources().getColor(R.color.btn_blue));
            holder.tv_addrss.setTextColor(context.getResources().getColor(R.color.btn_blue));
            holder.tv_state.setTextColor(context.getResources().getColor(R.color.btn_blue));
            holder.v_1.setTextColor(context.getResources().getColor(R.color.btn_blue));
            holder.tv_state.setVisibility(View.VISIBLE);
            holder.img_state.setVisibility(View.GONE);
        }


        return view;
    }


    class ViewHolder{
        @ViewInject(R.id.tv_time)
        TextView tv_time;
        @ViewInject(R.id.v_1)
        TextView v_1;
        @ViewInject(R.id.tv_state)
        TextView tv_state;
        @ViewInject(R.id.tv_addrss)
        TextView tv_addrss;
        @ViewInject(R.id.img_state)
        ImageView img_state;
    }
}
