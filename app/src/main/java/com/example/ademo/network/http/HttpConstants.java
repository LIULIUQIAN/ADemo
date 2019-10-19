package com.example.ademo.network.http;

public class HttpConstants {

    private static final String ROOT_URL = "http://imooc.com/api";

    /**
     * 广告服务器地址,待定
     */
    public static final String DISPLAY_AD_URL = "http://api.youku.com/image.php";

    /**
     * 请求本地产品列表
     */
    public static String PRODUCT_LIST = ROOT_URL + "/fund/search.php";

    /**
     * 本地产品列表更新时间措请求
     */
    public static String PRODUCT_LATESAT_UPDATE = ROOT_URL + "/fund/upsearch.php";

    /**
     * 登陆接口
     */
    public static String LOGIN = ROOT_URL + "/user/login_phone.php";

    /**
     * 检查更新接口
     */
    public static String CHECK_UPDATE = ROOT_URL + "/config/check_update.php";

    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "/product/home_recommand.php";

    /**
     * 课程详情接口
     */
    public static String COURSE_DETAIL = ROOT_URL + "/product/course_detail.php";
}
