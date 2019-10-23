package com.example.clothrentapp.ui.user;

import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothrentapp.R;
import com.example.clothrentapp.Result;
import com.example.clothrentapp.okhttp.Constant;
import com.example.clothrentapp.okhttp.OkCallback;
import com.example.clothrentapp.okhttp.OkHttp;
import com.example.clothrentapp.ui.entity.UserBean;
import com.example.clothrentapp.utils.BaseActivity;
import com.example.clothrentapp.utils.CustomToast;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class UserActivity extends BaseActivity {
    @BindView(R.id.recyclerview_user)
    RecyclerView mRecyclerviewUser;
    private UserAdapter mUserAdapter;
    private List<UserBean> mUserBeans = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_user;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("用户列表");
        loadData();
        mUserAdapter = new UserAdapter(mUserBeans);
        mRecyclerviewUser.setAdapter(mUserAdapter);
        mRecyclerviewUser.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerviewUser.setItemAnimator(new DefaultItemAnimator());
        mUserAdapter.notifyDataSetChanged();
    }

    public void loadData() {
        String url = Constant.user_select_all;
        OkHttp.post(getApplicationContext(), url, null, new OkCallback<List<UserBean>>() {
            @Override
            public void onResponse(Result<List<UserBean>> response) {
                if (null != response.getData() && response.getData().size() > 0) {
                    mUserBeans.addAll(jsonStringConvertToList(response.getData().toString(),UserBean[].class));
                    CustomToast.showToast(UserActivity.this, response.getMessage());
                }
            }

            @Override
            public void onFailure(String state, String msg) {
                CustomToast.showToast(UserActivity.this,"123");

                CustomToast.showToast(UserActivity.this, msg);
            }
        });

    }

    /**
     * 把一个json的字符串转换成为一个包含POJO对象的List
     *
     * @param string
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonStringConvertToList(String string, Class<T[]> cls) {
        Gson gson = new Gson();
        T[] array = gson.fromJson(string, cls);
        return Arrays.asList(array);
    }
}
