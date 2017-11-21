package com.babuwyt.consignee.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.base.SessionManager;
import com.babuwyt.consignee.finals.Constants;
import com.babuwyt.consignee.util.UHelper;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by lenovo on 2017/10/30.
 * 欢迎页面
 */
@ContentView(R.layout.activity_welcome)
public class WelComeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        storageCard();
    }

    //两秒后页面切换
    private void timeDown() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpToActivity();
            }
        }, 2000);
    }

    private void jumpToActivity() {
        isLogin();
    }

    private void isLogin() {
        //todo 判断是否已经登陆
        if (SessionManager.getInstance().isLogin()) {
            if (TextUtils.isEmpty(SessionManager.getInstance().getUser().getFiphone())) {
                startActivity(new Intent(this, BindTelActivity.class));
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }


    //授权读写权限
    private void storageCard() {

        AndPermission.with(this)
                .requestCode(100)
                .permission(Permission.STORAGE, Permission.LOCATION)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(WelComeActivity.this, rationale).show();
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
                timeDown();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // Failure.
            if (requestCode == 100) {
                // TODO ...
                AlertDialog.Builder builder = new AlertDialog.Builder(WelComeActivity.this);
                builder.setMessage("我们需要储存手机权限，请前往设置界面设置");
                builder.setTitle("授权失败");
                builder.setPositiveButton("好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivityForResult(intent, 0);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();

                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            storageCard();
        }
    }
}
