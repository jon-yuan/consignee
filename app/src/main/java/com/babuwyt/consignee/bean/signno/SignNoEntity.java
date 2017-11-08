package com.babuwyt.consignee.bean.signno;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/8.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SignNoEntity {
    private String signNo;
    private String shiperName;
    private String consigneName;
    private String linkMan;
    private String phoneNum;
    private String compactNo;
    private String goodName;
    private String speciFication;
    private String amount;
    private String legalAmount;
    private String transAmount;
    private String carrierName;
    private String driverName;
    private String driverCarId;
    private String sendTime;
    private String orderNumber;
    private String addressDetails;

    public String getSignNo() {
        return signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo;
    }

    public String getShiperName() {
        return shiperName;
    }

    public void setShiperName(String shiperName) {
        this.shiperName = shiperName;
    }

    public String getConsigneName() {
        return consigneName;
    }

    public void setConsigneName(String consigneName) {
        this.consigneName = consigneName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCompactNo() {
        return compactNo;
    }

    public void setCompactNo(String compactNo) {
        this.compactNo = compactNo;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getSpeciFication() {
        return speciFication;
    }

    public void setSpeciFication(String speciFication) {
        this.speciFication = speciFication;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLegalAmount() {
        return legalAmount;
    }

    public void setLegalAmount(String legalAmount) {
        this.legalAmount = legalAmount;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverCarId() {
        return driverCarId;
    }

    public void setDriverCarId(String driverCarId) {
        this.driverCarId = driverCarId;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    /**
     *  "signNo": "DOC20171106-01",
     "shiperName": "货主1106002",
     "consigneName": "收货人1106002",
     "linkMan": "张瑞宝",
     "phoneNum": "13227051298",
     "compactNo": "HZ11060022017001",
     "goodName": "电脑",
     "speciFication": null,
     "amount": 100,
     "legalAmount": null,
     "transAmount": null,
     "carrierName": "承运人1106002",
     "driverName": "张瑞宝",
     "driverCarId": "陕A12365",
     "sendTime": "2017-11-06 15:01:19.0",
     "orderNumber": "WD201711060001",
     "addressDetails": "北京市西城区港务大道"
     */
}
