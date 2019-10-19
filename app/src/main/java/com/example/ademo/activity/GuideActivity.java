package com.example.ademo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.ademo.R;
import com.example.ademo.view.fragment.GuideFragment;

public class GuideActivity extends BaseActivity {

    private static final int NUM_PAGES = 5;

    /*UI*/
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        changeStatusBarColor(R.color.transparent);
        mViewPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),0);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == NUM_PAGES - 1){
                    startActivity(new Intent(GuideActivity.this,MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**/
    public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{


        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            GuideFragment tp = null;
            switch (position){
                case 0:
                    tp = GuideFragment.newInstance(R.layout.fragment_welcome_one);
                    break;
                case 1:
                    tp = GuideFragment.newInstance(R.layout.fragment_welcome_two);
                    break;
                case 2:
                    tp = GuideFragment.newInstance(R.layout.fragment_welcome_three);
                    break;
                case 3:
                    tp = GuideFragment.newInstance(R.layout.fragment_welcome_four);
                    break;
                case 4:
                    tp = GuideFragment.newInstance(R.layout.fragment_welcome_five);
                    break;
            }
            return tp;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
