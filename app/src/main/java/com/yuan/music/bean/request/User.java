package com.yuan.music.bean.request;

import java.io.Serializable;

/**
 * Created by YUAN on 2016/10/24.
 * <p>
 * </P>
 * 用户信息数据类
 */

public class User implements Serializable {
    private String LoginName;
    private String Password;

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "LoginName='" + LoginName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
