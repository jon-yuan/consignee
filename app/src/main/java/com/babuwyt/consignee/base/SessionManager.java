package com.babuwyt.consignee.base;

import android.text.TextUtils;

import com.babuwyt.consignee.bean.User;


/**
 * Created by lenovo on 2017/6/30.
 */

public class SessionManager {
    private static SessionManager manager;
    /**
     * 文件保存的路径，文件夹
     */
    private String appFileDirPath;
    /**
     * 保存的当前登录的用户对象*
     */
    private User mUser;

    /**
     * 定位信息
     */
    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (manager == null) {
            manager = new SessionManager();
        }
        return manager;

    }

    /**
     * 更新当前登录的用户对象
     *
     * @param user
     */
    public void setUser(User user) {
        this.mUser = user;
    }


    /**
     * 获取当前登录的用户对象
     *
     * @return
     */
    public User getUser() {
        return mUser;
    }

    /**
     * 是否已经登陆
     */
    public boolean isLogin() {
        boolean b = false;
        if (mUser == null) {
            b = false;
        } else {
            if (TextUtils.isEmpty(mUser.getId())) {
                b = false;
            } else {
                b = true;
            }
        }
        return b;

    }

    /**
     * 设置保存路径
     *
     * @param path
     */
    public void setAppFileDirPath(String path) {
        this.appFileDirPath = path;
    }

    public String getAppFileDirPath() {
        return appFileDirPath;
    }

}
