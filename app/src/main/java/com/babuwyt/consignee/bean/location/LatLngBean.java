package com.babuwyt.consignee.bean.location;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/9.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LatLngBean extends BaseBean {

    private Result obj;

    public Result getObj() {
        return obj;
    }

    public void setObj(Result obj) {
        this.obj = obj;
    }
}
