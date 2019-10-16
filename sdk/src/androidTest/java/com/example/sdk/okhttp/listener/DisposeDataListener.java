package com.example.sdk.okhttp.listener;

public interface DisposeDataListener {

    public void onSuccess(Object responseObj);
    public void onFailure(Object reasonObj);
}
