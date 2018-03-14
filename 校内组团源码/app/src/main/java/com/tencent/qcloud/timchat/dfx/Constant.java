package com.tencent.qcloud.timchat.dfx;

/**
 * Created by wuchaochao on 2018/1/26.
 */

public class Constant {
    /**
     * 字符编码
     */
    public static final String ENCODING="GB2312";

    /**
     * 用户类型
     */
    public static final String RADIO_BUTTON_LIST="学生";

    /**
     * 基础地址
     */
    public static final String BASE_URL ="http://42.247.7.170/";

    /**
     * 验证码URL
     */
    public static String CHECK_IMAGE_URL = "";

    /**
     * 登陆URL
     */
    public static final String LOGIN_URL ="default2.aspx";

    /**
     * 登陆后主页面URL
     */
    public static final String STUDENT_URL ="xs_main.aspx?xh=";
    /**
     * 取数据的key，验证码
     */
    public static final String CHECK="check";
    /**
     * 取数据的key，地址
     */
    public static final String JW_URL="jwurl";
    /**
     * 取数据的key，登陆成功
     */
    public static  final String LOGIN_SUCCESS="loginSuccess";

    /**
     * 取数据的key，登陆失败
     */
    public static  final String LOGIN_FAIL="loginfail";

    public void login(User user) {

    }
    public void setCHECK_IMAGE_URL(String CHECK_IMAGE_URL) {
        this.CHECK_IMAGE_URL = CHECK_IMAGE_URL;
    }

}
