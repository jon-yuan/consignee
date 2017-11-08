package com.babuwyt.consignee.bean.signno;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/8.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SignNoBean extends BaseBean {
    private ArrayList<SignNoEntity> obj;

    public ArrayList<SignNoEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<SignNoEntity> obj) {
        this.obj = obj;
    }
}
