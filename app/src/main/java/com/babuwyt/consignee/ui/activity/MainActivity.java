package com.babuwyt.consignee.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.MainAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.order.ComNumBean;
import com.babuwyt.consignee.bean.order.ComNumEntity;
import com.babuwyt.consignee.bean.order.OrderBean;
import com.babuwyt.consignee.bean.order.OrderEntity;
import com.babuwyt.consignee.bean.version.VersionBean;
import com.babuwyt.consignee.bean.version.VersionEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.finals.Constants;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.google.zxing.activity.CaptureActivity;
import com.babuwyt.consignee.util.jpush.LocalBroadcastManager;
import com.babuwyt.consignee.util.jpush.TagAliasOperatorHelper;
import com.babuwyt.consignee.util.jpush.Util;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseProgressCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.babuwyt.consignee.views.dialog.ShaixuanDialog;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.springview)
    SpringView springview;
    @ViewInject(R.id.drawer_layout)
    DrawerLayout drawer;
    @ViewInject(R.id.nav_view)
    NavigationView nav_view;
    @ViewInject(R.id.listview)
    ListView listview;
    @ViewInject(R.id.layout_relative)
    RelativeLayout layout_relative;
    @ViewInject(R.id.tv_qianshou)
    TextView tv_qianshou;
    private ArrayList<OrderEntity> mList;
    private MainAdapter mAdapter;
    private int pageNum = 1;//分页
    private ArrayList<ComNumEntity> entities;
    private ArrayList<String> compactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(false);
        init();
        setAlias();
        setLister();
        registerMessageReceiver();
        getVersion();
        getOrder();
        getComNum();

    }


    @SuppressLint("ResourceAsColor")
    private void init() {
        nav_view.setNavigationItemSelectedListener(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_shaixuan:
//                        UHelper.showToast(MainActivity.this,"筛选");
                        showShaixuan();
                        break;
                }
                return true;
            }
        });
        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getOrder();
            }

            @Override
            public void onLoadmore() {
                getOrderMore();
                springview.onFinishFreshAndLoad();
            }
        });
        compactList = new ArrayList<String>();
        mList = new ArrayList<OrderEntity>();

        mAdapter = new MainAdapter(this);
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SignDetailsMoreActivity.class);
                intent.putExtra("orderId", mList.get(i).getOrderId());
                startActivity(intent);
            }
        });
    }

    //    @Event(value = {R.id.tv_qianshou})
//    private void getE(View v) {
//        switch (v.getId()) {
//            case R.id.tv_qianshou:
////                isCamera();
//                break;
//        }
//    }
    //检查拍照权限，动态设置
    private void isCamera() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    Constants.MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            startActivity(new Intent(MainActivity.this, CaptureActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == Constants.MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("您没有授权成功，无法使用相机进行拍照功能，请前往设置授权！");
                builder.setTitle("授权失败");
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            } else {
                startActivity(new Intent(MainActivity.this, RQCodeActivity.class));
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_order) {
            startActivity(new Intent(this, HistoryOrderActivity.class));
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(this, SettingActivity.class));

        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * 筛选
     */

    @SuppressLint("NewApi")
    private void showShaixuan() {
        final ShaixuanDialog dialog = new ShaixuanDialog(this);
        dialog.create();
        dialog.setmList(entities);
        dialog.show();
        dialog.setCallBack(new ShaixuanDialog.CallBack() {
            @Override
            public void CallBack(ArrayList<String> list) {
                dialog.dismiss();
                compactList = list;
                getOrder();
            }
        });
    }

    private void setAlias() {
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
//        TagAliasOperatorHelper.TagAliasBean.
//        TagAliasOperatorHelper.getInstance().handleAction(this,1,);
    }

    /**
     * 极光推送
     */
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }


    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    UHelper.showToast(MainActivity.this, messge + "");
                    if (!Util.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    /**
     * 获取订单
     */

    private void getOrder() {
        pageNum = 1;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("pageNum", pageNum);
        map.put("compactList", compactList);
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.GETORDERSHOW, map, new ResponseCallBack<OrderBean>() {
            @Override
            public void onSuccess(OrderBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                springview.onFinishFreshAndLoad();
                if (result.isSuccess()) {
                    mList.clear();
                    mList.addAll(result.getObj().getOrderDetails() == null ? new ArrayList<OrderEntity>() : result.getObj().getOrderDetails());
                    mAdapter.notifyDataSetChanged();
                } else {
                    UHelper.showToast(MainActivity.this, result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void getOrderMore() {
        pageNum++;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("pageNum", pageNum);
        map.put("compactList", compactList);
        mDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.GETORDERSHOW, map, new ResponseCallBack<OrderBean>() {
            @Override
            public void onSuccess(OrderBean result) {
                super.onSuccess(result);
                mDialog.dissDialog();
                springview.onFinishFreshAndLoad();
                if (result.isSuccess()) {
                    mList.addAll(result.getObj().getOrderDetails() == null ? new ArrayList<OrderEntity>() : result.getObj().getOrderDetails());
                    mAdapter.notifyDataSetChanged();
                } else {
                    UHelper.showToast(MainActivity.this, result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void getComNum() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(SessionManager.getInstance().getUser().getFid());
        XUtil.GetPing(BaseURL.COMPACTNUM, list, new ResponseCallBack<ComNumBean>() {
            @Override
            public void onSuccess(ComNumBean result) {
                super.onSuccess(result);
                if (result.isSuccess()) {
                    entities = result.getObj();
                    Log.d("", new Gson().toJson(result.getObj()));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    /**
     * 版本检测
     */

    //版本检测
    private void getVersion() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(5 + "");
        XUtil.GetPing(BaseURL.CHECKVERSION, list, new ResponseCallBack<VersionBean>() {
            @Override
            public void onSuccess(VersionBean o) {
                if (o.isSuccess()) {
                    setVersion(o.getObj());
                    SessionManager.getInstance().setServicephone(o.getObj().getFservicephone());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
        });
    }

    private void setVersion(final VersionEntity entity) {
        String vsersionCode = UHelper.getAppVersionInfo(this, UHelper.TYPE_VERSION_CODE);
        if (entity.getFversion() > Integer.parseInt(vsersionCode)) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle("发现新版本");
            builder.setMessage(entity.getFupdateinfo());
            builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    DownLoadFile(entity.getFurl());
                }
            });
            if (entity.getFisforceupdate()) {
                builder.setCancelable(false);
            } else {
                builder.setCancelable(true);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
            }
            builder.create().show();
        }
    }

    /**
     * 下载现版本APP
     */
    private File filepath;

    private void DownLoadFile(String url) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // 获取SD卡的目录
            String path = Environment.getExternalStorageDirectory().getPath();
            filepath = new File(path + File.separator + "apk" + File.separator + "consignee.apk");//仅创建路径的File对象
            if (!filepath.exists()) {
                filepath.mkdir();//如果路径不存在就先创建路径
            }
        }
        // 准备进度条Progress弹窗
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);// 设置是否可以通过点击Back键取消
        dialog.setTitle("下载中...");
        //Progress弹窗设置为水平进度条
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条

        //"http://acj3.pc6.com/pc6_soure/2017-8/me.ele_190.apk"
        XUtil.DownLoadFile(url, filepath.getPath().toString(), new ResponseProgressCallBack<File>() {
            @Override
            public void Started() {
                dialog.show();
            }

            @Override
            public void Success(File o) {
                dialog.dismiss();
                installAPK();
            }

            @Override
            public void Loading(long total, long current, boolean isDownloading) {
                dialog.setMax((int) total);
                dialog.setProgress((int) current);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialog.dismiss();
            }
        });
    }

    private void installAPK() {
        //系统应用界面，安装apk入口，看源码
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.setData(Uri.fromFile(file));
//        intent.setType("application/vnd.android.package-archive");

        //切记当要同时配Data和Type时一定要用这个方法，否则会出错
        intent.setDataAndType(Uri.fromFile(filepath), "application/vnd.android.package-archive");

        startActivityForResult(intent, 0);
    }

    private DisplayMetrics dm;
    private int lastX, lastY;
    long starttime = 0;

    private void setLister() {
        dm = getResources().getDisplayMetrics();
        final int screenWidth = dm.widthPixels;
        final int screenHeight = dm.heightPixels - 50;
        tv_qianshou.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        int ea = motionEvent.getAction();
        final int screenWidth = dm.widthPixels;
        final int screenHeight = dm.heightPixels;
        int l = 0;
        int b = 0;
        int r = 0;
        int t = 0;

        switch (v.getId()) {

            case R.id.tv_qianshou:

                switch (ea) {

                    case MotionEvent.ACTION_DOWN:
                        starttime = System.currentTimeMillis();
                        lastX = (int) motionEvent.getRawX();// 获取触摸事件触摸位置的原始X坐标
                        lastY = (int) motionEvent.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) motionEvent.getRawX() - lastX;
                        int dy = (int) motionEvent.getRawY() - lastY;
                        l = v.getLeft() + dx;
                        b = v.getBottom() + dy;
                        r = v.getRight() + dx;
                        t = v.getTop() + dy;
                        // 下面判断移动是否超出屏幕
                        if (l < 0) {
                            l = 0;
                            r = l + v.getWidth();
                        }
                        if (t < 0) {
                            t = 0;
                            b = t + v.getHeight();
                        }
                        if (r > screenWidth) {
                            r = screenWidth;
                            l = r - v.getWidth();
                        }
                        if (b > screenHeight) {
                            b = screenHeight;
                            t = b - v.getHeight();
                        }
                        v.layout(l, t, r, b);
                        lastX = (int) motionEvent.getRawX();
                        lastY = (int) motionEvent.getRawY();
                        v.postInvalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        long endtime = System.currentTimeMillis();
                        if (endtime - starttime < 200) {
                            isCamera();
                        }
                        break;
                    default:
                        break;
                }
                break;
        }
        return true;
    }

    /**
     * 点击两次退出应用
     * 通过记录按键时间计算时间差实现
     */
    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    Toast.makeText(MainActivity.this, getString(R.string.onclickisexit), Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
            }
        }
        return false;
    }

}
