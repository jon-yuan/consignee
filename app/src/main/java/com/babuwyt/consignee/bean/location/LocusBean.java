package com.babuwyt.consignee.bean.location;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/9.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LocusBean extends BaseBean {
    private ArrayList<LocusEntity> obj;

    public ArrayList<LocusEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<LocusEntity> obj) {
        this.obj = obj;
    }
}
