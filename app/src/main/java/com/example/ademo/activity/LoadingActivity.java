package com.example.ademo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.ademo.R;

public class LoadingActivity extends BaseActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            if (msg.what == 1){
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
            }else if (msg.what == 2){
                startActivity(new Intent(LoadingActivity.this, GuideActivity.class));
            }

            finish();
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        handler.sendEmptyMessageDelayed(2,2000);
    }
}
