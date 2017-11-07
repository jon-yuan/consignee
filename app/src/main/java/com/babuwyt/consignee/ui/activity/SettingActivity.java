package com.babuwyt.consignee.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.ClientApp;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.bean.version.VersionBean;
import com.babuwyt.consignee.bean.version.VersionEntity;
import com.babuwyt.consignee.finals.BaseURL;
import com.babuwyt.consignee.util.UHelper;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseCallBack;
import com.babuwyt.consignee.util.request.CommonCallback.ResponseProgressCallBack;
import com.babuwyt.consignee.util.request.XUtil;
import com.babuwyt.consignee.views.dialog.PromptDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lenovo on 2017/10/30.
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_version)
    TextView tv_version;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();
    }

    private void init() {
        intent = new Intent();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_version.setText(UHelper.getAppVersionInfo(this, UHelper.TYPE_VERSION_NAME));
    }

    @Event(value = {R.id.tv_logout, R.id.linearyout_changepsd, R.id.layout_service, R.id.layout_version})
    private void gete(View v) {
        switch (v.getId()) {
            case R.id.tv_logout:
                logout();
                break;
            case R.id.linearyout_changepsd:
                intent.setClass(this, ChangePsdActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_service:
                intent.setClass(this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_version:
                getVersion();
                break;
        }
    }

    @SuppressLint("NewApi")
    private void logout() {
        PromptDialog dialog = new PromptDialog(this);
        dialog.setTitle(getString(R.string.prompt));
        dialog.setMsg(getString(R.string.logout));
        dialog.setCanceledTouchOutside(true);
        dialog.setOnClick1(getString(R.string.confirm), new PromptDialog.Btn1OnClick() {
            @Override
            public void onClick() {
                ((ClientApp) getApplication()).clearLoginUser();
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });
        dialog.setOnClick2(getString(R.string.cancal), new PromptDialog.Btn2OnClick() {
            @Override
            public void onClick() {

            }
        });
        dialog.create();
        dialog.showDialog();
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
                } else {
                    UHelper.showToast(SettingActivity.this, o.getMsg());
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
