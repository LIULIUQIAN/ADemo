package com.example.ademo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.ademo.R;
import com.example.ademo.module.recommand.RecommandBodyValue;

import java.util.ArrayList;

public class HotSalePagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<RecommandBodyValue> mData;
    private LayoutInflater mInflater;

    public HotSalePagerAdapter(Context context, ArrayList<RecommandBodyValue> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(mContext);
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
        RecommandBodyValue value = mData.get(position);
        View rootView = mInflater.inflate(R.layout.item_hot_product_pager_layout,null);
        TextView titleView = rootView.findViewById(R.id.title_view);
        TextView infoView = rootView.findViewById(R.id.info_view);
        TextView gonggaoView = rootView.findViewById(R.id.gonggao_view);
        TextView saleView = rootView.findViewById(R.id.sale_num_view);
        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = rootView.findViewById(R.id.image_one);
        imageViews[1] = rootView.findViewById(R.id.image_two);
        imageViews[2] = rootView.findViewById(R.id.image_three);

        titleView.setText(value.title);
        infoView.setText(value.price);
        gonggaoView.setText(value.info);
        saleView.setText(value.text);
        for (int i = 0; i < imageViews.length; i++){
            Glide.with(mContext).load(value.url.get(i)).into(imageViews[i]);
        }
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
