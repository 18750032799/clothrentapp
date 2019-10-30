package com.example.clothrentapp.ui.yyh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.clothrentapp.MainActivity;
import com.example.clothrentapp.R;
import com.example.clothrentapp.Result;
import com.example.clothrentapp.okhttp.Constant;
import com.example.clothrentapp.okhttp.OkCallback;
import com.example.clothrentapp.okhttp.OkHttp;
import com.example.clothrentapp.utils.BaseActivity;
import com.example.clothrentapp.utils.CustomToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity {
    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.edit_password)
    EditText mEditPassword;
    @BindView(R.id.btn_regist)
    Button mBtnRegist;

    @Override
    protected int getContentView() {
        return R.layout.activity_regist;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("注册");
    }

    @Override
    protected boolean hasBack() {
        return false;
    }

    @OnClick(R.id.btn_regist)
    public void onViewClicked() {
        if (TextUtils.isEmpty(mEditPhone.getText().toString())) {
            CustomToast.showToast(getApplicationContext(), "手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEditPassword.getText().toString())) {
            CustomToast.showToast(getApplicationContext(), "密码不能为空");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("phone", mEditPhone.getText().toString());
        map.put("password", mEditPassword.getText().toString());
        OkHttp.post(getApplicationContext(), Constant.user_regist, map, new OkCallback<String>() {
            @Override
            public void onResponse(Result<String> response) {
                CustomToast.showToast(getApplicationContext(), response.getMessage());
                startActivity(new Intent(RegistActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(String state, String msg) {
                CustomToast.showToast(getApplicationContext(), msg);
            }
        });
    }
}
