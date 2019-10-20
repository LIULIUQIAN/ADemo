package com.example.ademo.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.ademo.R;
import com.example.ademo.module.recommand.RecommandBodyValue;
import com.example.sdk.adutil.Utils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CourseAdapter extends BaseAdapter {

    /**
     * Common
     */
    private static final int CARD_COUNT = 4;
    private static final int VIDOE_TYPE = 0x00;
    private static final int CARD_TYPE_ONE = 0x01;
    private static final int CARD_TYPE_TWO = 0x02;
    private static final int CARD_TYPE_THREE = 0x03;

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<RecommandBodyValue> mData;
    private ViewHolder mViewHolder;


    public CourseAdapter(Context context, ArrayList<RecommandBodyValue> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        return value.type;
    }

    @Override
    public int getViewTypeCount() {
        return CARD_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        if (convertView == null){
            mViewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_product_card_one_layout,parent,false);
            switch (type){
                case CARD_TYPE_ONE:
                    convertView = mInflater.inflate(R.layout.item_product_card_one_layout,parent,false);
                    mViewHolder.mLogoView = convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductLayout = convertView.findViewById(R.id.product_photo_layout);

                    break;
            }
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        switch (type){
            case CARD_TYPE_ONE:
                Glide.with(mContext).load(value.logo).into(mViewHolder.mLogoView);
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));
                mViewHolder.mProductLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                mViewHolder.mProductLayout.removeAllViews();
                //动态添加多个imageview
                for (String url : value.url) {
                    mViewHolder.mProductLayout.addView(createImageView(url));
                }
                break;
        }
        return convertView;
    }

    private ImageView createImageView(String url) {
        ImageView photoView = new ImageView(mContext);
        photoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin = Utils.dip2px(mContext,10);
        photoView.setLayoutParams(params);
        Glide.with(mContext).load(url).placeholder(R.drawable.bg_1_c_01).into(photoView);
        return photoView;
    }

    private static class ViewHolder{
        //所有Card共有属性
        private CircleImageView mLogoView;
        private TextView mTitleView;
        private TextView mInfoView;
        private TextView mFooterView;
        //Video Card特有属性
        private RelativeLayout mVieoContentLayout;
        private ImageView mShareView;

        //Video Card外所有Card具有属性
        private TextView mPriceView;
        private TextView mFromView;
        private TextView mZanView;
        //Card One特有属性
        private LinearLayout mProductLayout;
        //Card Two特有属性
        private ImageView mProductView;
        //Card Three特有属性
        private ViewPager mViewPager;
    }
}
