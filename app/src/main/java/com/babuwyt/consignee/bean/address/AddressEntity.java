package com.babuwyt.consignee.bean.address;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/8.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class AddressEntity {
    private String provinceName;
    private String cityName;
    private String areaName;
    private String addressDetails;
    private String addressType;
    private String areaId;
    private String cityId;
    private String proviceId;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProviceId() {
        return proviceId;
    }

    public void setProviceId(String proviceId) {
        this.proviceId = proviceId;
    }

    /**
     * "provinceName": "上海市",
     "cityName": "上海市",
     "areaName": "宝山区",
     "addressDetails": "富锦路1151号",
     "addressType": "1",
     "areaId": "310113",
     "cityId": "3101",
     "proviceId": "31"
     */
}
