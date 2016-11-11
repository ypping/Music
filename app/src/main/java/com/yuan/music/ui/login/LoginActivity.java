package com.yuan.music.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yuan.music.R;
import com.yuan.music.base.BaseActivity;
import com.yuan.music.bean.request.User;
import com.yuan.music.ui.home.HomeActivity;
import com.yuan.music.ui.login.presenter.LoginPresenter;
import com.yuan.music.ui.login.presenter.LoginPresenterImpl;
import com.yuan.music.ui.login.view.LoginView;
import com.yuan.music.utils.UtilLog;

import java.io.IOException;

/**
 * 登陆界面
 *
 * @author YUAN
 */
public class LoginActivity extends BaseActivity implements LoginView {
    private final String TAG = "LoginActivity";
    private TextInputEditText name, password;
    private Button determine;
    private LoginPresenter loginPresenter = new LoginPresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        name = (TextInputEditText) findViewById(R.id.loginName);
        password = (TextInputEditText) findViewById(R.id.loginPassword);
        determine = (Button) findViewById(R.id.loginBtnDetermine);
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authentication()) {
                    try {
                        loginPresenter.setLogin(name.getText().toString().trim(), password.getText().toString().trim());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 验证输入的字符是否合格
     *
     * @return
     */
    private boolean authentication() {
        if (!TextUtils.isEmpty(name.getText().toString().trim()) && !TextUtils.isEmpty(password.getText().toString().trim())) {
            return true;
        }
        return false;
    }

    @Override
    public void loginSuccessful(Object o) {
        UtilLog.e(TAG, TAG + "o: " + ((User) o).getLoginName() + "  o:" + o.toString());
        handler.sendEmptyMessage(1);
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void loginFailure(String msg) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("msg", msg);
        message.setData(bundle);
        handler.sendMessage(message);
        UtilLog.d(TAG, TAG + "msg" + msg);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(LoginActivity.this, msg.getData().getString("msg"), Toast.LENGTH_SHORT).show();
        }
    };
}
