package com.babuwyt.consignee.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.bean.order.OrderEntity;
import com.babuwyt.consignee.bean.signno.SignNoEntity;
import com.babuwyt.consignee.ui.activity.LocusActivity;
import com.babuwyt.consignee.ui.activity.PositionActivity;
import com.babuwyt.consignee.ui.activity.StateFollowActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/3.
 */

public class MainAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<OrderEntity> mList;

    public MainAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<OrderEntity>();
    }

    public void setmList(ArrayList<OrderEntity> list) {
        if (list != null) {
            mList = list;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_main_item, null);
            x.view().inject(holder, view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_dervernum.setText(mList.get(i).getOrderNumber());
        holder.tv_cartype.setText(mList.get(i).getCarType());
        holder.tv_huozhu.setText(mList.get(i).getShiperName());
        holder.tv_product.setText(mList.get(i).getGoodsName());
//        ArrayList<SignNoEntity> list = mList.get(i).getSignList();
//        holder.tv_no3.setVisibility(View.GONE);
//        holder.tv_no4.setVisibility(View.GONE);
//        switch (list.size()) {
//            case 0:
//                holder.tv_no1.setText("");
//                break;
//            case 1:
//                holder.tv_no1.setText(list.get(0).getSignNo());
//                break;
//            case 2:
//                holder.tv_no1.setText(list.get(0).getSignNo());
//                holder.tv_no2.setText(list.get(1).getSignNo());
//                break;
//            case 3:
//                holder.tv_no1.setText(list.get(0).getSignNo());
//                holder.tv_no2.setText(list.get(1).getSignNo());
//                holder.tv_no3.setText(list.get(2).getSignNo());
//                holder.tv_no3.setVisibility(View.VISIBLE);
//                break;
//            default:
//                holder.tv_no1.setText(list.get(0).getSignNo());
//                holder.tv_no2.setText(list.get(1).getSignNo());
//                holder.tv_no3.setText(list.get(2).getSignNo());
//                holder.tv_no4.setText("...");
//                holder.tv_no3.setVisibility(View.VISIBLE);
//                holder.tv_no4.setVisibility(View.VISIBLE);
//                break;
//        }

        holder.layout_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(mContext, PositionActivity.class);
                intent.putExtra("fsendcarid",mList.get(i).getOrderId());
                intent.putExtra("drivername",mList.get(i).getDriverName());
                mContext.startActivity(intent);
            }
        });
        holder.layout_guiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(mContext, LocusActivity.class);
                intent.putExtra("fsendcarid",mList.get(i).getFsendcarid());
                mContext.startActivity(intent);
            }
        });
        holder.layout_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(mContext, StateFollowActivity.class);
                intent.putExtra("orderId",mList.get(i).getOrderId());
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    class ViewHolder {
        @ViewInject(R.id.tv_dervernum)
        TextView tv_dervernum;
        @ViewInject(R.id.tv_cartype)
        TextView tv_cartype;
        @ViewInject(R.id.tv_huozhu)
        TextView tv_huozhu;
        @ViewInject(R.id.tv_product)
        TextView tv_product;
        @ViewInject(R.id.tv_no1)
        TextView tv_no1;
        @ViewInject(R.id.tv_no2)
        TextView tv_no2;
        @ViewInject(R.id.tv_no3)
        TextView tv_no3;
        @ViewInject(R.id.tv_no4)
        TextView tv_no4;
        @ViewInject(R.id.layout_location)
        LinearLayout layout_location;
        @ViewInject(R.id.layout_guiji)
        LinearLayout layout_guiji;
        @ViewInject(R.id.layout_state)
        LinearLayout layout_state;
    }

}
