package com.babuwyt.consignee.bean.version;


import com.babuwyt.consignee.bean.BaseBean;
import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class VersionBean extends BaseBean {
    private VersionEntity obj;

    public VersionEntity getObj() {
        return obj;
    }

    public void setObj(VersionEntity obj) {
        this.obj = obj;
    }
}
