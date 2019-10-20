package com.example.ademo.view.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ademo.R;
import com.example.ademo.adapter.PhotoPagerAdapter;
import com.example.ademo.module.recommand.RecommandFooterValue;
import com.example.ademo.module.recommand.RecommandHeadValue;
import com.example.ademo.view.viewpagerindictor.CirclePageIndicator;

import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;

public class HomeHeaderLayout extends RelativeLayout {

    private Context mContext;

    private RelativeLayout mRootView;
    private AutoScrollViewPager mViewPager;
    private CirclePageIndicator mPagerIndictor;
    private TextView mHotView;
    private PhotoPagerAdapter mAdapter;
    private ImageView[] mImageViews = new ImageView[4];
    private LinearLayout mFootLayout;

    private RecommandHeadValue mHeaderValue;

    public HomeHeaderLayout(Context context) {
        super(context);
    }

    public HomeHeaderLayout(Context context, RecommandHeadValue headerValue) {
        this(context, null, headerValue);
    }

    public HomeHeaderLayout(Context context, AttributeSet attrs, RecommandHeadValue headerValue) {
        super(context, attrs);
        mContext = context;
        mHeaderValue = headerValue;
        initView();
    }

    private void initView() {
        mRootView = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.listview_home_head_layout,this);
        mViewPager = (AutoScrollViewPager) mRootView.findViewById(R.id.pager);
        mPagerIndictor = mRootView.findViewById(R.id.pager_indictor_view);
        mHotView = (TextView) mRootView.findViewById(R.id.zuixing_view);
        mImageViews[0] = (ImageView) mRootView.findViewById(R.id.head_image_one);
        mImageViews[1] = (ImageView) mRootView.findViewById(R.id.head_image_two);
        mImageViews[2] = (ImageView) mRootView.findViewById(R.id.head_image_three);
        mImageViews[3] = (ImageView) mRootView.findViewById(R.id.head_image_four);
        mFootLayout = (LinearLayout) mRootView.findViewById(R.id.content_layout);

        mAdapter = new PhotoPagerAdapter(mContext,mHeaderValue.ads);
        mViewPager.setAdapter(mAdapter);
        mViewPager.startAutoScroll(5000);
        mPagerIndictor.setViewPager(mViewPager);

        for (int i = 0; i < mImageViews.length;i++){
            Glide.with(mContext).load(mHeaderValue.middle.get(i)).into(mImageViews[i]);
        }

        for (RecommandFooterValue value : mHeaderValue.footer){
            HomeBottomItem item = new HomeBottomItem(mContext,value);
            mFootLayout.addView(item);
        }
        mHotView.setText(mContext.getString(R.string.today_zuixing));
    }
}
