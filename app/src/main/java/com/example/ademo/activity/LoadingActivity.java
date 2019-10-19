package com.example.ademo.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ademo.R;
import com.example.ademo.db.SPManager;
import com.example.ademo.module.advertising.AdInstance;
import com.example.ademo.module.advertising.AdValue;
import com.example.ademo.network.http.HttpConstants;
import com.example.ademo.network.http.RequestCenter;
import com.example.ademo.util.GetJsonDataUtil;
import com.example.sdk.okhttp.listener.DisposeDataListener;
import com.google.gson.Gson;

public class LoadingActivity extends BaseActivity {

    private ImageView mAdLayout;
    private RelativeLayout mCopyLayout;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            if (msg.what == 1) {
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
            } else if (msg.what == 2) {
                startActivity(new Intent(LoadingActivity.this, GuideActivity.class));
                SPManager.getInstance().putBoolean(SPManager.IS_SHOW_GUIDE,true);
            }

            finish();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initView();
        initData();
        boolean isShowGuide = SPManager.getInstance().getBoolean(SPManager.IS_SHOW_GUIDE,false);
        handler.sendEmptyMessageDelayed(isShowGuide?1:2, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private void initView() {
        mAdLayout = findViewById(R.id.ad_content_view);
        mCopyLayout = findViewById(R.id.content_layout);
    }

    private void initData() {


        String jsonData = GetJsonDataUtil.getJson(this, "splash_ad_data.json");
        Gson gson = new Gson();
        AdInstance model = gson.fromJson(jsonData, AdInstance.class);
        AdValue adValue = model.values.get(0);
        Glide.with(this)
                .load(adValue.resource)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        mCopyLayout.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        mCopyLayout.setVisibility(View.VISIBLE);
                        return false;
                    }


                }).into(mAdLayout);



    }


}