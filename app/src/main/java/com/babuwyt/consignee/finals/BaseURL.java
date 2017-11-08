package com.babuwyt.consignee.finals;

/**
 * 此处专门放接口名
 * Created by yw on 2014/12/9.
 */
public class BaseURL {
    /**
     * 每页数量
     */
    public static final int PAGESIZE = 20;
    /**
     * 请求地址
     */
//    public static final String BASE_URL = "http://www.babuwyt.com:8080/tmsapi//";//测试服务器地址
//    public static final String BASE_URL = "http://123.206.75.242:8090/tmsapi/";//测试服务器地址
    public static final String BASE_URL = "http://192.168.2.151:8080/tms/";//张瑞宝主机
//    public static final String BASE_URL = "http://119.29.230.77:8090/tmsapi/";//正式服务器地址
//    private static final String BASE_URL = "http://192.168.2.71:8090/";
    public static final String BASE_IMAGE_URI = "http://bbkj-1253538594.picgz.myqcloud.com/";



    /**
     * 登录
     */
    public static final String LOGIN=BASE_URL+"AppConsignee/consigneLogin";
    /**
     * 重置密码
     */
    public static final String RESETPSD=BASE_URL+"AppConsignee/resetPsd";
    /**
     * 重置密码
     */
    public static final String CHANGEPSD=BASE_URL+"AppConsignee/modificationPsd";
    /**
     * 获取首页订单
     */
    public static final String GETORDERSHOW=BASE_URL+"AppConsignee/orderShow";
    /**
     * 签收单列表详情
     */
    public static final String ORDERDETAILS=BASE_URL+"AppConsignee/orderDetails";
    /**
     * 获取验证码
     * type    类型1：注册，2：修改密码，3忘记密码 ，4 绑定手机
     */
    public static final String GET_AUTHCODE=BASE_URL+"AppConsignee/gainCauthCode";
    /**
     * 检测版本
     *
     * (1 : 司机App  2 : 现场App  3 : 合伙人App  4 : 货主App 5:收货人)
     */
    public static final String CHECKVERSION=BASE_URL+"appcommon/getappversion";
}

