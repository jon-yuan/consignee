package com.babuwyt.consignee.bean.order;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/11/10.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class ComNumEntity {
    private String comNum;
    private boolean select;

    public String getComNum() {
        return comNum;
    }

    public void setComNum(String comNum) {
        this.comNum = comNum;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    /**
     *  "comNum": "ht1106001-5777"
     */
}
