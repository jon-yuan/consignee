package com.babuwyt.consignee.bean.order;

import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/10.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class HistoryOrderBean extends BaseBean {
    private ArrayList<HistoryOrderEntity> obj;

    public ArrayList<HistoryOrderEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<HistoryOrderEntity> obj) {
        this.obj = obj;
    }
}
