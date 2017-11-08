package com.babuwyt.consignee.bean.login;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/8.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LoginBean extends BaseBean{
    private User obj;

    public User getObj() {
        return obj;
    }

    public void setObj(User obj) {
        this.obj = obj;
    }
}
