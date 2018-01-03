package com.babuwyt.consignee.bean.order;


import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by bbkj on 2017/12/6.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderDetailsEntity {
    private String orderId;
    private String orderNumber;
    private String driverOrderNumber;
    private String orderSysNumber;
    private String carType;
    private String extractTime;
    private String Farricetime;
    private String routeFrom;
    private String routeTo;
    private String remark;
    private String shiperName;
    private String goodsName;
    private String goodsNum;
    private String goodsPackType;
    private String goodsVolume;
    private String goodsWeight;
    private String goodsHedge;
    private String orderState;
    private String type;
    private String fsendcarid;
    private String driverName;

    private ArrayList<String> loadingPic;
    private ArrayList<String> unloadingPic;
    private ArrayList<Address> loadAddressList;
    private ArrayList<String>  unloadPhoto;

    public ArrayList<String> getUnloadPhoto() {
        return unloadPhoto;
    }

    public void setUnloadPhoto(ArrayList<String> unloadPhoto) {
        this.unloadPhoto = unloadPhoto;
    }

    public ArrayList<String> getLoadingPic() {
        return loadingPic;
    }

    public void setLoadingPic(ArrayList<String> loadingPic) {
        this.loadingPic = loadingPic;
    }

    public ArrayList<String> getUnloadingPic() {
        return unloadingPic;
    }

    public void setUnloadingPic(ArrayList<String> unloadingPic) {
        this.unloadingPic = unloadingPic;
    }

    public ArrayList<Address> getLoadAddressList() {
        return loadAddressList;
    }

    public void setLoadAddressList(ArrayList<Address> loadAddressList) {
        this.loadAddressList = loadAddressList;
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

    public String getFarricetime() {
        return Farricetime;
    }

    public void setFarricetime(String farricetime) {
        Farricetime = farricetime;
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

    public String getShiperName() {
        return shiperName;
    }

    public void setShiperName(String shiperName) {
        this.shiperName = shiperName;
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

    public String getGoodsPackType() {
        return goodsPackType;
    }

    public void setGoodsPackType(String goodsPackType) {
        this.goodsPackType = goodsPackType;
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

    public String getFsendcarid() {
        return fsendcarid;
    }

    public void setFsendcarid(String fsendcarid) {
        this.fsendcarid = fsendcarid;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /****
     *
     *
     * OrderDetailsActivity
     */
}
