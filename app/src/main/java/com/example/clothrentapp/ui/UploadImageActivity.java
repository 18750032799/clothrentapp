package com.example.clothrentapp.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.clothrentapp.BuildConfig;
import com.example.clothrentapp.R;
import com.example.clothrentapp.Result;
import com.example.clothrentapp.okhttp.Constant;
import com.example.clothrentapp.okhttp.OkCallback;
import com.example.clothrentapp.okhttp.OkHttp;
import com.example.clothrentapp.utils.BaseActivity;
import com.example.clothrentapp.utils.CustomToast;
import com.example.clothrentapp.utils.MyGlideEngine;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

public class UploadImageActivity extends BaseActivity {

    private static final int REQUEST_CODE_CHOOSE = 1;//定义请求码常量
    private List<String> mSelected=new ArrayList<>();
    @BindView(R.id.tv_upload_image)
    QMUIRoundButton mTvUploadImage;
    @BindView(R.id.iv_upload_image)
    QMUIRadiusImageView mIvUploadImage;

    @Override
    protected int getContentView() {
        return R.layout.activity_upload_image;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("上传图片");

    }

    @OnClick(R.id.tv_upload_image)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_upload_image:
                CustomToast.showToast(this, "上传照片");
                Matisse.from(this)
                        .choose(MimeType.ofAll())//资源的类型，比如现在这个设置是照片视频全部显示
                        .countable(true)//显示选择图片的数量
                        .capture(true)//使用拍照
                        .maxSelectable(1)//最多选择几张图片
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 图像选择和预览活动所需的方向。
                        .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".file_provider"))
                        .thumbnailScale(0.85f)//缩放比例
                        .imageEngine(new MyGlideEngine())//图片加载类，需要重写框架自带的不然会报错
                        .forResult(REQUEST_CODE_CHOOSE);//请求码
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected.addAll(Matisse.obtainPathResult(data));
            Glide.with(mIvUploadImage.getContext()).load(mSelected.get(0)).into(mIvUploadImage);
            OkHttp.upload(this, Constant.user_select_all, null, mSelected, new OkCallback() {
                @Override
                public void onResponse(Result response) {

                }

                @Override
                public void onFailure(String state, String msg) {

                }
            });

        }
    }
}
