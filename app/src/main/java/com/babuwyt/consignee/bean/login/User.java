package com.babuwyt.consignee.bean.login;

import com.babuwyt.consignee.util.request.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/10/18.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class User implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String fid;
    private String fuserid;
    private String floginname;
    private String fpassword;
    private String flogintime;
    private String flogincount;
    private String fforwardercode;
    private String fisdelete;
    private String fiphone;
    private String fstate;
    private String webtoken;
    private String companyname;
    private String fusername;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getFloginname() {
        return floginname;
    }

    public void setFloginname(String floginname) {
        this.floginname = floginname;
    }

    public String getFpassword() {
        return fpassword;
    }

    public void setFpassword(String fpassword) {
        this.fpassword = fpassword;
    }

    public String getFlogintime() {
        return flogintime;
    }

    public void setFlogintime(String flogintime) {
        this.flogintime = flogintime;
    }

    public String getFlogincount() {
        return flogincount;
    }

    public void setFlogincount(String flogincount) {
        this.flogincount = flogincount;
    }

    public String getFforwardercode() {
        return fforwardercode;
    }

    public void setFforwardercode(String fforwardercode) {
        this.fforwardercode = fforwardercode;
    }

    public String getFisdelete() {
        return fisdelete;
    }

    public void setFisdelete(String fisdelete) {
        this.fisdelete = fisdelete;
    }

    public String getFiphone() {
        return fiphone;
    }

    public void setFiphone(String fiphone) {
        this.fiphone = fiphone;
    }

    public String getFstate() {
        return fstate;
    }

    public void setFstate(String fstate) {
        this.fstate = fstate;
    }

    public String getWebtoken() {
        return webtoken;
    }

    public void setWebtoken(String webtoken) {
        this.webtoken = webtoken;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getFusername() {
        return fusername;
    }

    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    /**
     *  "fid": 342,
     "fuserid": null,
     "floginname": "13227051298",
     "fpassword": null,
     "floginip": "192.168.2.151",
     "flogintime": 1510103650952,
     "flogincount": 15,
     "fforwardercode": "shr1106002",
     "fcreatetime": null,
     "fisdelete": null,
     "fiphone": "13227051298",
     "fstate": null,
     "webtoken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0VXNlciI6eyJmaWQiOjM0MiwiZnVzZXJpZCI6bnVsbCwiZmxvZ2lubmFtZSI6IjEzMjI3MDUxMjk4IiwiZnBhc3N3b3JkIjpudWxsLCJmbG9naW5pcCI6IjE5Mi4xNjguMi4xNTEiLCJmbG9naW50aW1lIjoxNTEwMTAzNjUwOTUyLCJmbG9naW5jb3VudCI6MTUsImZmb3J3YXJkZXJjb2RlIjoic2hyMTEwNjAwMiIsImZjcmVhdGV0aW1lIjpudWxsLCJmaXNkZWxldGUiOm51bGwsImZpcGhvbmUiOiIxMzIyNzA1MTI5OCIsImZzdGF0ZSI6bnVsbCwid2VidG9rZW4iOm51bGwsImNvbXBhbnluYW1lIjoi5pS26LSn5Lq6MTEwNjAwMiIsImZ1c2VybmFtZSI6bnVsbH0sImlzcyI6InRtc2FwaXVzZXIiLCJleHAiOjE1MTEzOTk2NTEsIm5iZiI6MTUxMDEwMzY1MX0.LhjL__KGEzjtytIvty5HWzrCueDg6iFMOi1GUyahOJw",
     "companyname": "收货人1106002",
     "fusername": null
     */
}
