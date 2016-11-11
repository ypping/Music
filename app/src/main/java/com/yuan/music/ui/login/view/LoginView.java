package com.yuan.music.ui.login.view;

/**
 * Created by YUAN on 2016/10/24
 */

public interface LoginView {
    /**
     * 登陆成功的接口
     */
    void loginSuccessful(Object o);

    /**
     * 登录失败的接口
     */
    void loginFailure(String msg);
}