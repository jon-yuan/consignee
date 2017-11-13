package com.babuwyt.consignee.bean.order;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/8.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderBean extends BaseBean {
    private OrderBaseEntity obj;

    public OrderBaseEntity getObj() {
        return obj;
    }

    public void setObj(OrderBaseEntity obj) {
        this.obj = obj;
    }
}
