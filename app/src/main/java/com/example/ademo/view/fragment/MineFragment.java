package com.example.ademo.view.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ademo.R;
import com.example.ademo.activity.LoginActivity;
import com.example.ademo.manager.UserManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {


    /*UI*/
    private View mContentView;
    private CircleImageView mPhotoView;
    private TextView mLoginInfoView;
    private TextView mLoginView;
    private TextView mVideoPlayerView;
    private TextView mShareView;
    private TextView mQrCodeView;
    private TextView mUpdateView;

    private LoginBroadcastReceiver receiver = new LoginBroadcastReceiver();


    /**/
    private void registerBroadcast(){
        IntentFilter filter = new IntentFilter(LoginActivity.LOGIN_ACTION);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver,filter);
    }

    /**/
    private void unregisterBroadcast(){
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBroadcast();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        return mContentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }

    private void initView() {
        mPhotoView = mContentView.findViewById(R.id.photo_view);
        mLoginInfoView = mContentView.findViewById(R.id.login_info_view);
        mLoginView = mContentView.findViewById(R.id.login_view);
        mVideoPlayerView = mContentView.findViewById(R.id.video_setting_view);
        mShareView = mContentView.findViewById(R.id.share_imooc_view);
        mQrCodeView = mContentView.findViewById(R.id.my_qrcode_view);
        mUpdateView = mContentView.findViewById(R.id.update_view);

        mLoginView.setOnClickListener(this);
        mVideoPlayerView.setOnClickListener(this);
        mShareView.setOnClickListener(this);
        mQrCodeView.setOnClickListener(this);
        mUpdateView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_view:
                toLogin();
                break;
            case R.id.video_setting_view:
                break;
            case R.id.share_imooc_view:
                break;
            case R.id.my_qrcode_view:
                break;
            case R.id.update_view:
                break;

        }
    }
    /*跳转登录*/
    private void toLogin(){
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
    }

    /*接受登录广播*/
    private class LoginBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (UserManager.getInstance().hasLogined()){
                mLoginView.setVisibility(View.GONE);
                mLoginInfoView.setText(UserManager.getInstance().getUser().data.name);
                Glide.with(mContext).load(UserManager.getInstance().getUser().data.photoUrl).into(mPhotoView);
            }
        }
    }

}
