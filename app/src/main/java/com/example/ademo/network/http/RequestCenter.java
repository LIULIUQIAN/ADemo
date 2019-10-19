package com.example.ademo.network.http;

import com.example.ademo.module.advertising.AdInstance;
import com.example.sdk.okhttp.CommonOkHttpClient;
import com.example.sdk.okhttp.listener.DisposeDataHandle;
import com.example.sdk.okhttp.listener.DisposeDataListener;
import com.example.sdk.okhttp.request.CommonRequest;

public class RequestCenter {

    /**
     * 发送广告请求
     */
    public static void sendImageAdRequest(String url, DisposeDataListener listener) {

        CommonOkHttpClient.post(CommonRequest.createPostRequest(url, null),
                new DisposeDataHandle(listener, AdInstance.class));
    }


}
