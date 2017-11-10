package com.babuwyt.consignee.bean.order;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/10.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class HistoryOrderEntity {
    private String orderId;
    private String signNo;
    private String startPlace;
    private String endPlace;
    private String startTime;
    private String endTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSignNo() {
        return signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String getStartTime() {
        if (startTime==null || startTime.length()<11){
            return "";
        }
        return startTime.substring(0,11);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        if (endTime==null || endTime.length()<11){
            return "";
        }
        return endTime.substring(0,11);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    /**
     * "orderId": "5777",
     "signNo": "DOC20171106-03",
     "startPlace": "陕西省",
     "endPlace": "北京市",
     "startTime": "2017-11-07 10:00:00.0",
     "endTime": "2017-11-08 20:39:19.0"
     */
}
