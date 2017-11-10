package com.babuwyt.consignee.bean.order;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/10.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class ComNumBean extends BaseBean {
    private ArrayList<ComNumEntity> obj;

    public ArrayList<ComNumEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<ComNumEntity> obj) {
        this.obj = obj;
    }
}
