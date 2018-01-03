package com.babuwyt.consignee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.OrderDetailsAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.order.Address;
import com.babuwyt.consignee.bean.order.OrderDetailsBean;
import com.babuwyt.consignee.bean.order.OrderDetailsEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.babuwyt.consignee.views.CustomGridView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bbkj on 2017/12/5.
 */
@ContentView(R.layout.activity_orderdetails)
public class OrderDetailsActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.gridview)
    CustomGridView zhgridview;
    @ViewInject(R.id.img_zhuanghuo)
    ImageView img_zhuanghuo;
    @ViewInject(R.id.qianshou_gridview)
    CustomGridView qsgridview;
    @ViewInject(R.id.image_qianshou)
    ImageView image_qianshou;

    @ViewInject(R.id.layout_state)
    LinearLayout layout_state;

    @ViewInject(R.id.tv_state)
    TextView tv_state;
    @ViewInject(R.id.tv_ordernum)
    TextView tv_ordernum;
    @ViewInject(R.id.tv_form)
    TextView tv_form;
    @ViewInject(R.id.tv_to)
    TextView tv_to;
    @ViewInject(R.id.tv_timeti)
    TextView tv_timeti;
    @ViewInject(R.id.tv_timexie)
    TextView tv_timexie;
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_beizhu)
    TextView tv_beizhu;
    @ViewInject(R.id.tv_bztype)
    TextView tv_bztype;
    @ViewInject(R.id.tv_num)
    TextView tv_num;
    @ViewInject(R.id.tv_weight)
    TextView tv_weight;
    @ViewInject(R.id.tv_tiji)
    TextView tv_tiji;
    @ViewInject(R.id.tv_jiazhi)
    TextView tv_jiazhi;


    @ViewInject(R.id.xiehuo_gridview)
    CustomGridView xiehuo_gridview;
    @ViewInject(R.id.image_xiehuo)
    ImageView image_xiehuo;


    private OrderDetailsAdapter xhAdapter;
    private ArrayList<String> xhList;


    private OrderDetailsAdapter zhAdapter;
    private OrderDetailsAdapter qsAdapter;
    private String fsendcarno;
    private String Fownerid;
    private String orderid;
    private ArrayList<String> mList;
    private ArrayList<String> qsList;
//    private ArrayList<TworktracklistObj> stateList;
    private boolean showZH=true;//装货照片是否展开
    private boolean showQS=true;//签收照片是否展开
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
//        fsendcarno=getIntent().getStringExtra("fsendcarno");
//        Fownerid=getIntent().getStringExtra("Fownerid");
        orderid=getIntent().getStringExtra("orderId");
        init();
        getHttp();
    }

    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        zhAdapter=new OrderDetailsAdapter(this);
        mList=new ArrayList<String>();
        zhAdapter.setmList(mList);
        zhgridview.setAdapter(zhAdapter);
//        zhAdapter.showView(showZH,zhgridview);
        zhgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(OrderDetailsActivity.this,LookBigPictureActivity.class);
                intent.putExtra("index",i);
                intent.putExtra("list",mList);
                startActivity(intent);
            }
        });

        qsList=new ArrayList<String>();
        qsAdapter=new OrderDetailsAdapter(this);
        qsAdapter.setmList(qsList);
        qsgridview.setAdapter(qsAdapter);
//        qsAdapter.showView(showQS,qsgridview);
        qsgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(OrderDetailsActivity.this,LookBigPictureActivity.class);
                intent.putExtra("index",i);
                intent.putExtra("list",qsList);
                startActivity(intent);
            }
        });


        xhAdapter = new OrderDetailsAdapter(this);
//        xhAdapter.setShow(xhShow);
        xhList = new ArrayList<String>();

        xhAdapter.setmList(xhList);
        xiehuo_gridview.setAdapter(xhAdapter);
        xiehuo_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(OrderDetailsActivity.this,LookBigPictureActivity.class);
                intent.putExtra("index",i);
                intent.putExtra("list",xhList);
                startActivity(intent);
            }
        });



    }
    private void setData(OrderDetailsEntity b){
        String str="";
        tv_ordernum.setText(b.getOrderNumber());
//        for (Fromtomap fromtomap: b.getFromtolist()){
//            if (fromtomap.getFromto().equals("FROM")){
//                tv_form.setText(fromtomap.getFaddress());
//                break;
//            }
//        }
//        for (Fromtomap fromtomap: b.getFromtolist()){
//            if (fromtomap.getFromto().equals("TO")){
//                tv_to.setText(fromtomap.getFaddress());
//            }
//        }
        ArrayList<Address> list=b.getLoadAddressList();
        for (Address address : list){
            if (address.getAddressType()==1){
                tv_form.setText(address.getProvinceName()+address.getCityName()+address.getAreaName()+address.getAddressDetails());
            }

            if (address.getAddressType()==2){
                tv_to.setText(address.getProvinceName()+address.getCityName()+address.getAreaName()+address.getAddressDetails());
            }
        }
        tv_name.setText(b.getGoodsName());
        tv_timeti.setText(b.getExtractTime());
        tv_timexie.setText(b.getFarricetime());
        tv_bztype.setText(b.getGoodsPackType());
        tv_num.setText(b.getGoodsNum());
        tv_weight.setText(b.getGoodsWeight());
        tv_tiji.setText(b.getGoodsVolume());
        tv_jiazhi.setText(b.getGoodsHedge());
        switch (b.getOrderState()) {
            case "1":
                str="装货已签到";
                break;
            case "2":
                str="装货已拍照";
                break;
            case "3":
                str="已装货（装货照片已审核）";
                break;
            case "4":
                str="卸货已签到";
                break;
            case "5":
                str="已卸货（签收单照片已审核）";
                break;
            case "6":
                str="签收单已交回";
                break;
            case "7":
                str="签收单已确认";
                break;
                default:
                    str="未开始";
                    break;
        }
        tv_state.setText(str);
        tv_beizhu.setText("");
        if (mList!=null){
            mList.clear();
            mList.addAll(b.getLoadingPic());
            zhAdapter.notifyDataSetChanged();
        }
        if (qsList!=null){
            qsList.clear();
            qsList.addAll(b.getUnloadingPic());
            qsAdapter.notifyDataSetChanged();
        }
        if (xhList!=null){
            xhList.clear();
            xhList.addAll(b.getUnloadPhoto());
            xhAdapter.notifyDataSetChanged();
        }

    }
    private void getHttp(){
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("orderid",orderid);
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.ORDER_DETAILS,map,new ResponseCallBack<OrderDetailsBean>(){
            @Override
            public void onSuccess(OrderDetailsBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess()){
                    setData(result.getObj());
                }else {
                    UHelper.showToast(OrderDetailsActivity.this,result.getMsg());
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
                Log.d("服务器异常",ex+"");
            }
        });
    }
    @Event(value = {R.id.img_zhuanghuo,R.id.image_qianshou,R.id.layout_state})
    private void getE(View v){
        switch (v.getId()){
            case R.id.img_zhuanghuo:
                showZH=!showZH;
//                zhAdapter.showView(showZH,zhgridview);
                break;
            case R.id.image_qianshou:
                showQS=!showQS;
//                qsAdapter.showView(showQS,qsgridview);
                break;

            case R.id.layout_state:
                Intent intent=new Intent(this,StateFollowActivity.class);
                intent.putExtra("orderId",orderid);
                startActivity(intent);
                break;
        }
    }
}
