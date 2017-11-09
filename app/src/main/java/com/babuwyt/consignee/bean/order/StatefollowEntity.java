package com.babuwyt.consignee.bean.order;

import android.text.TextUtils;


import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/4.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class StatefollowEntity {

    private String fseq;
    private String ssq;
    private String faddress;
    private String fromto;
    private String checktims;
    private String typetemp;
    private String fromtoloadid;
    private int ftype;

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public String getFseq() {
        return fseq;
    }

    public void setFseq(String fseq) {
        this.fseq = fseq;
    }

    public String getSsq() {
        if (TextUtils.isEmpty(ssq)){
            return "";
        }
        return ssq;
    }

    public void setSsq(String ssq) {
        this.ssq = ssq;
    }

    public String getFaddress() {
        if (TextUtils.isEmpty(faddress)){
            return "";
        }
        return faddress;
    }

    public void setFaddress(String faddress) {
        this.faddress = faddress;
    }

    public String getFromto() {
        if (TextUtils.isEmpty(fromto)){
            return "";
        }
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public String getChecktims() {
        return checktims;
    }

    public void setChecktims(String checktims) {
        this.checktims = checktims;
    }

    public String getTypetemp() {
        return typetemp;
    }

    public void setTypetemp(String typetemp) {
        this.typetemp = typetemp;
    }

    public String getFromtoloadid() {
        return fromtoloadid;
    }

    public void setFromtoloadid(String fromtoloadid) {
        this.fromtoloadid = fromtoloadid;
    }

    /**
     * "fseq": 提卸货地顺序,
     "ssq": 提卸货地地址（三级）,
     "faddress": 地址,
     "fromto": "装货地"/“卸货地”,
     "checktims": 完成时间,
     "typetemp": 状态名称
     "fromtoloadid": "FMDD201709010003-1"
     */
}
