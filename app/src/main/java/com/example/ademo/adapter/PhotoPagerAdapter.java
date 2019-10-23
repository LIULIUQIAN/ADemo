package com.example.ademo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class PhotoPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<String> mData;
    private  boolean mIsMatch;

    public PhotoPagerAdapter(Context mContext, ArrayList<String> mData, boolean mIsMatch) {
        this.mContext = mContext;
        this.mData = mData;
        this.mIsMatch = mIsMatch;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView photoView;
        if (mIsMatch){
            photoView = new PhotoView(mContext);
        }else {
            photoView = new ImageView(mContext);
            photoView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        Glide.with(mContext).load(mData.get(position)).into(photoView);
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        return photoView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
