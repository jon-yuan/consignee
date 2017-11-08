package com.babuwyt.consignee.bean.order;

import com.babuwyt.consignee.bean.address.AddressEntity;
import com.babuwyt.consignee.bean.signno.SignNoEntity;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/8.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderEntity {

    private String orderId;
    private String orderNumber;
    private String driverOrderNumber;
    private String orderSysNumber;
    private String carType;
    private String extractTime;
    private String routeFrom;
    private String routeTo;
    private String remark;
    private String shiperName;
    private String goodsName;
    private String goodsNum;
    private String goodsVolume;
    private String goodsWeight;
    private String goodsHedge;
    private String orderState;
    private String type;
    private ArrayList<SignNoEntity> signList;
    private ArrayList<AddressEntity> loadAddressList;

    public ArrayList<AddressEntity> getLoadAddressList() {
        return loadAddressList;
    }

    public void setLoadAddressList(ArrayList<AddressEntity> loadAddressList) {
        this.loadAddressList = loadAddressList;
    }

    public String getShiperName() {
        return shiperName;
    }

    public void setShiperName(String shiperName) {
        this.shiperName = shiperName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDriverOrderNumber() {
        return driverOrderNumber;
    }

    public void setDriverOrderNumber(String driverOrderNumber) {
        this.driverOrderNumber = driverOrderNumber;
    }

    public String getOrderSysNumber() {
        return orderSysNumber;
    }

    public void setOrderSysNumber(String orderSysNumber) {
        this.orderSysNumber = orderSysNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getExtractTime() {
        return extractTime;
    }

    public void setExtractTime(String extractTime) {
        this.extractTime = extractTime;
    }

    public String getRouteFrom() {
        return routeFrom;
    }

    public void setRouteFrom(String routeFrom) {
        this.routeFrom = routeFrom;
    }

    public String getRouteTo() {
        return routeTo;
    }

    public void setRouteTo(String routeTo) {
        this.routeTo = routeTo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsHedge() {
        return goodsHedge;
    }

    public void setGoodsHedge(String goodsHedge) {
        this.goodsHedge = goodsHedge;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<SignNoEntity> getSignList() {
        return signList;
    }

    public void setSignList(ArrayList<SignNoEntity> signList) {
        this.signList = signList;
    }
    /**
     *  "orderId": 5777,
     "orderNumber": "WD201711060001",
     "driverOrderNumber": "D17220171106001",
     "orderSysNumber": null,
     "carType": "17.5米",
     "extractTime": "2017-11-07 10:00:00.0",
     "createtime": null,
     "routeFrom": "陕西省",
     "routeTo": "北京市",
     "remark": null,
     "goodsName": "电脑",
     "goodsPackType": null,
     "goodsNum": null,
     "goodsVolume": null,
     "goodsWeight": null,
     "goodsHedge": null,
     "orderState": null,
     "signNnm": null,
     "type": null,
     */
}
