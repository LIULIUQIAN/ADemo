package com.example.ademo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.ademo.R;
import com.example.ademo.manager.UserManager;
import com.example.ademo.module.user.User;
import com.example.ademo.util.GetJsonDataUtil;
import com.google.gson.Gson;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final String LOGIN_ACTION = "com.example.ademo.LOGIN_ACTION";
    /*ui*/
    private EditText mUserNameView;
    private EditText mPasswordView;
    private TextView mLoginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        mUserNameView = findViewById(R.id.associate_email_input);
        mPasswordView = findViewById(R.id.login_input_password);
        mLoginView = findViewById(R.id.login_button);
        mLoginView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:
                login();
                break;
        }
    }

    private void login() {
        String name = mUserNameView.getText().toString().trim();
        String pwd = mPasswordView.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        String json = GetJsonDataUtil.getJson(this,"user_info.json");
        Gson gson = new Gson();
        User user = gson.fromJson(json,User.class);
        user.data.name = name;
        UserManager.getInstance().setUser(user);

        sendLoginBroadcast();

        finish();

    }

    /*发送登录广播*/
    private void sendLoginBroadcast(){
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN_ACTION));
    }
}
