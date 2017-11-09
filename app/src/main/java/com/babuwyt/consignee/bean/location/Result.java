package com.babuwyt.consignee.bean.location;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/9.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class Result {
    private String name;
    private String address;
    private LocusEntity gps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocusEntity getGps() {
        return gps;
    }

    public void setGps(LocusEntity gps) {
        this.gps = gps;
    }
}
