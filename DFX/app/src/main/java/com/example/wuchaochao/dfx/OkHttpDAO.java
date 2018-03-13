package com.example.wuchaochao.dfx;

/**
 * Created by wuchaochao on 2018/1/26.
 */

public interface OkHttpDAO {
    /**
     * 初始化，主要用于收集cookie和viewState
     */
    public void init();
    /**
     * 获取验证码
     * @return 验证码图片
     */
    public void getCheckImg();

    /**
     * 登陆
     * @param user 用户信息
     */
    public void login(User user);

}
