package com.babuwyt.consignee.bean.order;


import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/4.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class StateFollowBean extends BaseBean {
    private StateFollowBaseBean obj;


    public StateFollowBaseBean getObj() {
        return obj;
    }

    public void setObj(StateFollowBaseBean obj) {
        this.obj = obj;
    }
}
