package com.babuwyt.consignee.bean.order;



import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/4.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class StateFollowBaseBean {
    private ArrayList<StatefollowEntity> worktrack;
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }
    private boolean state;
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ArrayList<StatefollowEntity> getWorktrack() {
        return worktrack;
    }

    public void setWorktrack(ArrayList<StatefollowEntity> worktrack) {
        this.worktrack = worktrack;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
