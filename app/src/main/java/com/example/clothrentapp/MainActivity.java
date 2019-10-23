package com.example.clothrentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clothrentapp.okhttp.Constant;
import com.example.clothrentapp.okhttp.OkCallback;
import com.example.clothrentapp.okhttp.OkHttp;
import com.example.clothrentapp.ui.entity.UserBean;
import com.example.clothrentapp.ui.user.UserActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private TextView mTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final EditText phone = (EditText) findViewById(R.id.edit_phone);
        final EditText password = (EditText) findViewById(R.id.edit_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Constant.user_login;
                Map<String, String> map = new HashMap<>();
                map.put("phone", phone.getText().toString());
                map.put("password", password.getText().toString());

                OkHttp.post(getApplicationContext(), url, map, new OkCallback<UserBean>() {
                    @Override
                    public void onResponse(Result<UserBean> response) {
                        Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @OnClick(R.id.btn_select_all_user)
    public void onViewClicked() {
        startActivity(new Intent(this, UserActivity.class));
    }
}
