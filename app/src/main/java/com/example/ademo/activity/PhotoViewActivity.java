package com.example.ademo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ademo.R;
import com.example.ademo.adapter.PhotoPagerAdapter;

import java.util.ArrayList;

public class PhotoViewActivity extends BaseActivity {
    public static final String PHOTO_LIST = "photo_list";
    /*ui*/
    private ViewPager mPager;
    private TextView mIndictorView;
    private ImageView mShareView;

    private PhotoPagerAdapter mAdapter;
    private ArrayList<String> mPhotoLists;
    private int currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mPhotoLists = intent.getStringArrayListExtra(PHOTO_LIST);
        mIndictorView.setText("1/"+mPhotoLists.size());

        mAdapter = new PhotoPagerAdapter(this,mPhotoLists,true);
        mPager.setAdapter(mAdapter);
    }

    private void initView() {
        mPager = findViewById(R.id.photo_pager);
        mIndictorView = findViewById(R.id.indictor_view);
        mShareView = findViewById(R.id.share_view);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position;
                mIndictorView.setText(String.valueOf(currentPos+1)+"/"+mPhotoLists.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
