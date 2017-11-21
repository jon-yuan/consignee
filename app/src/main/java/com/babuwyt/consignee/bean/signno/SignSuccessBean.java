package com.babuwyt.consignee.bean.signno;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/21.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SignSuccessBean extends BaseBean {
    private String obj;

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
}
