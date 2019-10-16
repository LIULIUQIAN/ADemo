package com.example.ademo.view.fragment;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ademo.R;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContentView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
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
