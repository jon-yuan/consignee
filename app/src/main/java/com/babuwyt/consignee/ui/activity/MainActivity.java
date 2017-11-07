package com.babuwyt.consignee.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.adapter.MainAdapter;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.version.VersionBean;
import com.babuwyt.consignee.bean.version.VersionEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.jpush.LocalBroadcastManager;
import com.babuwyt.consignee.util.jpush.Util;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseProgressCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.babuwyt.consignee.views.dialog.ShaixuanDialog;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{
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
    private ArrayList<String> mList;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(false);
        init();
        registerMessageReceiver();
        getVersion();
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
                switch (item.getItemId()){
                    case R.id.action_shaixuan:
//                        UHelper.showToast(MainActivity.this,"筛选");
                        showShaixuan();
                        break;
                }
                return true;
            }
        });
        springview.setHeader(new DefaultHeader(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mList.add("1");
                mAdapter.notifyDataSetChanged();
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                springview.onFinishFreshAndLoad();
            }
        });
        mList = new ArrayList<String>();
        mList.add("1");
        mList.add("1");
        mList.add("1");
        mAdapter = new MainAdapter(this);
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this,LookSignNoActivity.class));
            }
        });
    }

    @Event(value = {R.id.tv_qianshou})
    private void getE(View v){
        switch (v.getId()){
            case R.id.tv_qianshou:
                startActivity(new Intent(MainActivity.this,RQCodeActivity.class));
                break;
        }
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
            startActivity(new Intent(this,HistoryOrderActivity.class));
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(this,SettingActivity.class));

        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * 筛选
     */

    @SuppressLint("NewApi")
    private void showShaixuan(){
        final ShaixuanDialog dialog=new ShaixuanDialog(this);
        dialog.create();
        dialog.show();
        dialog.setCallBack(new ShaixuanDialog.CallBack() {
            @Override
            public void CallBack(String s) {
                dialog.dismiss();
                UHelper.showToast(MainActivity.this,s);
            }
        });
    }















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
                    UHelper.showToast(MainActivity.this,messge+"");
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

}
