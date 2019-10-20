package com.example.ademo.view.fragment;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ademo.R;
import com.example.ademo.adapter.CourseAdapter;
import com.example.ademo.module.recommand.BaseRecommandModel;
import com.example.ademo.util.GetJsonDataUtil;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    /*
    * UI
    * */
    private View mContentView;
    private ListView mListView;
    private ImageView mQrcodeView;
    private ImageView mCategoryView;
    private ImageView mLoadingView;
    private TextView mSearchView;

    /*data*/
    private BaseRecommandModel mRecommandData;
    private CourseAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContentView = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        initView();
        requestData();
        return mContentView;
    }

    private void initView(){
        mListView = mContentView.findViewById(R.id.list_view);
        mQrcodeView = mContentView.findViewById(R.id.qrcode_view);
        mCategoryView = mContentView.findViewById(R.id.category_view);
        mLoadingView = mContentView.findViewById(R.id.loading_view);
        mSearchView = mContentView.findViewById(R.id.search_view);

        mQrcodeView.setOnClickListener(this);
        mCategoryView.setOnClickListener(this);
        mSearchView.setOnClickListener(this);
        AnimationDrawable amin = (AnimationDrawable) mLoadingView.getDrawable();
        amin.start();

    }

    /*请求网络数据*/
    private void requestData(){

        String jsonData = GetJsonDataUtil.getJson(getActivity(),"home_data.json");
        Gson gson = new Gson();
        mRecommandData = gson.fromJson(jsonData, BaseRecommandModel.class);

        if (mRecommandData.data.list != null && mRecommandData.data.list.size() > 0){
            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mAdapter = new CourseAdapter(mContext,mRecommandData.data.list);
            mListView.setAdapter(mAdapter);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.qrcode_view:
                System.out.println("扫一扫");
                break;
            case R.id.search_view:
                System.out.println("搜索");
                break;
            case R.id.category_view:
                System.out.println("更多");
                break;
        }

    }
}
