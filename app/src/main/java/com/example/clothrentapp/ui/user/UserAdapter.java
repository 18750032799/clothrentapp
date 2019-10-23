package com.example.clothrentapp.ui.user;

import android.widget.TextView;

import com.example.clothrentapp.R;
import com.example.clothrentapp.ui.entity.UserBean;
import com.example.clothrentapp.utils.BaseRecyclerViewAdapter;
import com.example.clothrentapp.utils.RecyclerViewHolder;

import java.util.List;

public class UserAdapter extends BaseRecyclerViewAdapter<UserBean> {


    public UserAdapter(List<UserBean> data) {
        super(data);
        mLayoutId = R.layout.item_user;
    }


    @Override
    protected void onBindData(RecyclerViewHolder holder, UserBean bean, int position) {
        TextView tvPhone = (TextView) holder.getView(R.id.tv_phone);
        TextView tvPassword = (TextView) holder.getView(R.id.tv_password);
        tvPassword.setText(bean.getPassword());
        tvPhone.setText(bean.getPhone());
    }

}
