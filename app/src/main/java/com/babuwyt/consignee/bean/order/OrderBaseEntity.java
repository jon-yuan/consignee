package com.babuwyt.consignee.bean.order;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/13.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderBaseEntity {
    private ArrayList<OrderEntity> orderDetails;

    public ArrayList<OrderEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
