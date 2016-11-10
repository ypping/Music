package com.yuan.mymusic.ui.login.presenter;


import com.yuan.mymusic.bean.request.User;
import com.yuan.mymusic.net.URlRequest;
import com.yuan.mymusic.ui.login.view.LoginView;
import com.yuan.mymusic.utils.UtilLog;
import com.yuan.mymusic.utils.okHttpUtils.OkHttpUtils;

/**
 * Created by YUAN on 2016/10/24
 */

public class LoginPresenterImpl implements LoginPresenter {
    private final String TAG = "LoginPresenterImpl";
    private LoginView loginView;
    private String name, password;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void setLogin(String name, String password) {
        UtilLog.setDebugMode(true);
        final User user = new User();

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.post(URlRequest.TEST_SERVICES_URL, null, User.class, new OkHttpUtils.ResponseCallBack() {
                    @Override
                    public void onSuccess(Object response) {
                        loginView.loginSuccessful(response);
                    }

                    @Override
                    public void onFailure(String e) {
                        loginView.loginSuccessful(e.toString());
                    }
                });
            }
        }).start();
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.getRequest(url, "handler", "value", User.class, new OkHttpUtils.ResponseCallBack() {
                    @Override
                    public void onSuccess(Object response) {
                        loginView.loginSuccessful(response);
                    }

                    @Override
                    public void onFailure(String e) {
                        loginView.loginSuccessful(e.toString());
                    }
                });
            }
        }).start();*/
    }

}