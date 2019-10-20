package com.example.clothrentapp;

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


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
