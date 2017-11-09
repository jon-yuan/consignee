package com.babuwyt.consignee.bean.location;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/9.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LocusEntity {
    private double wgLon;
    private double wgLat;

    public double getWgLon() {
        return wgLon;
    }

    public void setWgLon(double wgLon) {
        this.wgLon = wgLon;
    }

    public double getWgLat() {
        return wgLat;
    }

    public void setWgLat(double wgLat) {
        this.wgLat = wgLat;
    }
    /**
     * {
     "wgLon": 114.12124666666666,
     "wgLat": 27.674133333333334
     },
     */
}
