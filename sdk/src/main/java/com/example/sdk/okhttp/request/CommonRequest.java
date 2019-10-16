package com.example.sdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;

public class CommonRequest {

    public static Request createPostRequest(String url, RequestParams params, RequestParams headers){

        FormBody.Builder formBodybuilder = new FormBody.Builder();
        if (params != null){
            for (Map.Entry<String,String> entry : params.urlParams.entrySet()){
                formBodybuilder.add(entry.getKey(),entry.getValue());
            }
        }

        Headers.Builder headersBuilder = new Headers.Builder();
        if (headers != null){
            for (Map.Entry<String,String> entry : headers.urlParams.entrySet()){
                headersBuilder.add(entry.getKey(),entry.getValue());
            }
        }

        FormBody mFormBody = formBodybuilder.build();
        Headers mHeaders = headersBuilder.build();

        Request request = new Request.Builder().url(url).post(mFormBody).headers(mHeaders).build();
        return request;
    }

    public static Request createPostRequest(String url, RequestParams params){
        return createPostRequest(url,params,null);
    }

    public static Request createGetRequest(String url, RequestParams params, RequestParams headers){

        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null){
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()){
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        Headers.Builder mHeadersBuilder = new Headers.Builder();
        if (headers != null){
            for (Map.Entry<String,String> entry : headers.urlParams.entrySet()){
                mHeadersBuilder.add(entry.getKey(),entry.getValue());
            }
        }
        Headers mHeaders = mHeadersBuilder.build();

        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1)).get().headers(mHeaders).build();
    }
}


