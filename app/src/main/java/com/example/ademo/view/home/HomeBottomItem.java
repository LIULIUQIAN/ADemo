package com.example.ademo.view.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ademo.R;
import com.example.ademo.module.recommand.RecommandFooterValue;

public class HomeBottomItem extends RelativeLayout {

    private Context mContext;

    private RelativeLayout mRootView;
    private TextView mTitleView;
    private TextView mInfoView;
    private TextView mInterestingView;
    private ImageView mImageView;

    private RecommandFooterValue mData;

    public HomeBottomItem(Context context) {
        super(context);
    }

    public HomeBottomItem(Context context, RecommandFooterValue data) {
        this(context, null, data);
    }

    public HomeBottomItem(Context context, AttributeSet attrs, RecommandFooterValue data) {
        super(context, attrs);
        mContext = context;
        mData = data;
        initView();
    }

    private void initView() {
        mRootView = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.item_home_recommand_layout,this);
        mTitleView = mRootView.findViewById(R.id.title_view);
        mInfoView =  mRootView.findViewById(R.id.info_view);
        mInterestingView = mRootView.findViewById(R.id.interesting_view);
        mImageView = mRootView.findViewById(R.id.icon_1);

        mTitleView.setText(mData.title);
        mInfoView.setText(mData.info);
        mInterestingView.setText(mData.from);
        Glide.with(mContext).load(mData.imageTwo).into(mImageView);
    }
}
