package com.babuwyt.consignee.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.StateFollowAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.ClientApp;
import com.babuwyt.consignee.bean.order.Driver;
import com.babuwyt.consignee.bean.order.StateFollowBean;
import com.babuwyt.consignee.bean.order.StatefollowEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.babuwyt.consignee.views.dialog.PromptDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/8/24.
 * 订单跟踪
 */
@ContentView(R.layout.activity_stategenzong)
public class StateFollowActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.listview)
    ListView listview;
    TextView tv_dingdan;
    TextView tv_state;
    TextView tv_siji;
    TextView tv_fsiji;
    TextView tv_dianhua;
    TextView tv_fdianhua;
    ImageView image_callphone;
    LinearLayout layout_driver;
    private TextView tv_wancheng;
    private ArrayList<StatefollowEntity> mList;
    private StateFollowAdapter mAdapter;
    private String orderId;
    private Driver mdriver;
    private boolean state;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        orderId=getIntent().getStringExtra("orderId");
        init();
        getState();
    }

    private void init() {
        toolbar.setNavigationIcon(R.drawable.icon_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAdapter=new StateFollowAdapter(this);
        mList=new ArrayList<StatefollowEntity>();
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        listview.addHeaderView(header());
        listview.addFooterView(footer());
    }

    private View header(){
        View header= LayoutInflater.from(this).inflate(R.layout.view_state_header,null);
        tv_dingdan=header.findViewById(R.id.tv_dingdan);
        tv_state=header.findViewById(R.id.tv_state);
        tv_siji=header.findViewById(R.id.tv_siji);
        tv_fsiji=header.findViewById(R.id.tv_fsiji);
        tv_dianhua=header.findViewById(R.id.tv_dianhua);
        tv_fdianhua=header.findViewById(R.id.tv_fdianhua);
        image_callphone=header.findViewById(R.id.image_callphone);
        layout_driver=header.findViewById(R.id.layout_driver);

        image_callphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c();
            }
        });
        return header;
    }
    private View footer(){
        View footer= LayoutInflater.from(this).inflate(R.layout.view_state_footer,null);
        tv_wancheng=footer.findViewById(R.id.tv_wancheng);
        return footer;
    }
    private void getState(){
        ArrayList<String> list=new ArrayList<String>();
        list.add(orderId);
        mDialog.showDialog();
        XUtil.GetPing(BaseURL.GETWORKTRACK, list, new ResponseCallBack<StateFollowBean>() {
            @Override
            public void onSuccess(StateFollowBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                if (result.isSuccess()){
                    mList.clear();
                    mList.addAll(result.getObj().getWorktrack());
                    state=result.getObj().isState();
                    mAdapter.notifyDataSetChanged();
                    mdriver=result.getObj().getDriver();
                    setData();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
                UHelper.showToast(StateFollowActivity.this,getString(R.string.NET_ERROR));
            }
        });
    }

    private void setData(){
        if (mdriver==null){
            return;
        }
        tv_dingdan.setText(getString(R.string.order)+mdriver.getFsendcarno());
        tv_dianhua.setText(getString(R.string.linkphone)+mdriver.getFtel());
        tv_fdianhua.setText(getString(R.string.linkphone)+mdriver.getFftel());
        tv_siji.setText(getString(R.string.zhusiji)+mdriver.getDrivername()+getString(R.string.chepaihao)+mdriver.getFplateno());
        tv_fsiji.setText(getString(R.string.fusiji)+mdriver.getFdrivername());
        tv_state.setText(getString(R.string.yipaiche));
        tv_wancheng.setEnabled(state);
        if (mdriver==null || TextUtils.isEmpty(mdriver.getDrivername())){
            layout_driver.setVisibility(View.GONE);
        }else {
            layout_driver.setVisibility(View.VISIBLE);
        }
    }



    private void c(){
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.CALL_PHONE)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(StateFollowActivity.this, rationale).show();
                    }
                })
                .callback(listener)
                .start();
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // Successfully.
            if (requestCode == 100) {
                // TODO ...
                call();
            }
        }

        @SuppressLint("NewApi")
        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // Failure.
            if (requestCode == 100) {
                // TODO ...
                PromptDialog dialog = new PromptDialog(StateFollowActivity.this);
                dialog.setTitle(getString(R.string.prompt));
                dialog.setMsg(getString(R.string.call_quanxian));
                dialog.setCanceledTouchOutside(true);
                dialog.setOnClick1(getString(R.string.cancal), new PromptDialog.Btn1OnClick() {
                    @Override
                    public void onClick() {


                    }
                });
                dialog.setOnClick2(getString(R.string.ok), new PromptDialog.Btn2OnClick() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivityForResult(intent, 0);
                    }
                });
                dialog.create();
                dialog.showDialog();

            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            call();
        }
    }

    @SuppressLint("MissingPermission")
    private void call(){
        if (mdriver==null){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mdriver.getFtel()));
        startActivity(intent);
    }

}
