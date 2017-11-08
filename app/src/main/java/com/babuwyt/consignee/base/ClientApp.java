package com.babuwyt.consignee.base;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.babuwyt.consignee.bean.login.User;
import com.babuwyt.consignee.finals.SharePrefKeys;
import com.babuwyt.consignee.util.SharePreferencesUtils;
import com.google.gson.Gson;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by lenovo on 2017/6/28.
 */

public class ClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        x.Ext.init(this);
        initUserInfo();
        initJpush();
//        Location();
        String registrationId = JPushInterface.getRegistrationID(this);
        Log.d("=registrationId=",registrationId+"");
    }

    private void initJpush(){
        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
    /**
     * 初始化用户信息
     */
    private void initUserInfo() {
        String data = SharePreferencesUtils.getString(this, SharePrefKeys.XML_PREFERENCES, SharePrefKeys.XML_NAME_USER_INFO, "");
        if (!TextUtils.isEmpty(data)) {
            Gson gson = new Gson();
            User user = gson.fromJson(data, User.class);
            SessionManager sessionManager = SessionManager.getInstance();
            if (user != null) {
                sessionManager.setUser(user);
            }
        }
    }
    /**
     * 保存登录用户的信息
     *
     * @param user
     */
    public void saveLoginUser(User user) {
        if (user == null) {
            return;
        }
        Gson gson = new Gson();
        String data = gson.toJson(user);

        SharePreferencesUtils.saveString(this, SharePrefKeys.XML_PREFERENCES, SharePrefKeys.XML_NAME_USER_INFO, data);
        this.initUserInfo();
    }
    /**
     * 清除用户登录信息
     */
    public  void clearLoginUser() {
        SharePreferencesUtils.clearAll(this, SharePrefKeys.XML_PREFERENCES);
        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.setUser(null);
    }
}
