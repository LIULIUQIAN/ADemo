package com.example.sdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.example.sdk.adutil.ResponseEntityToModule;
import com.example.sdk.okhttp.exception.OkHttpException;
import com.example.sdk.okhttp.listener.DisposeDataHandle;
import com.example.sdk.okhttp.listener.DisposeDataListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommonJsonCallback implements Callback {


    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie";

    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull final IOException e) {

        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new  OkHttpException(NETWORK_ERROR,e));
            }
        });

    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });


    }

    private void handleResponse(Object responseObj){
        if (responseObj == null || responseObj.toString().trim().equals("")){
            mListener.onFailure(new  OkHttpException(NETWORK_ERROR,EMPTY_MSG));
            return;
        }

        try {
            JSONObject result = new JSONObject(responseObj.toString());
            if (mClass == null){
                mListener.onSuccess(result);
            }else {
                Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                if (obj != null) {
                    mListener.onSuccess(obj);
                } else {
                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }

            }
        } catch (JSONException e) {
            mListener.onFailure(new  OkHttpException(NETWORK_ERROR,e.getMessage()));
            e.printStackTrace();
        }
    }
}
