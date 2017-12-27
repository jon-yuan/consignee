package com.babuwyt.consignee.bean.order;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by bbkj on 2017/12/6.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderDetailsBean extends BaseBean {
    private OrderDetailsEntity obj;

    public OrderDetailsEntity getObj() {
        return obj;
    }

    public void setObj(OrderDetailsEntity obj) {
        this.obj = obj;
    }
}
