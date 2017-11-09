package com.babuwyt.consignee.bean.location;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/9.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LatLngEntity {
    private double lon;
    private double lat;
    private String adr;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    /**
     *  "drc": "301",
     "utc": "1510196688000",
     "spd": "0.0",
     "lon": "72474268",
     "adr": "浙江省绍兴市上虞区入口，向东北方向，200米",
     "lat": "18021990"
     */
}
