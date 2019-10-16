package com.example.ademo.activity;

import android.os.Bundle;

import com.example.ademo.R;
import com.example.ademo.view.fragment.HomeFragment;
import com.example.ademo.view.fragment.MessageFragment;
import com.example.ademo.view.fragment.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private Fragment mCurrent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(homeFragment);
                    return true;
                case R.id.navigation_msg:
                    showFragment(messageFragment);
                    return true;
                case R.id.navigation_mine:
                    showFragment(mineFragment);
                    return true;
            }
            return false;
        }
    };

    private void showFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.hide(homeFragment);
        fragmentTransaction.hide(messageFragment);
        fragmentTransaction.hide(mineFragment);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        homeFragment = new HomeFragment();
        messageFragment = new MessageFragment();
        mineFragment = new MineFragment();

        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.content_layout,homeFragment);
        fragmentTransaction.add(R.id.content_layout,messageFragment);
        fragmentTransaction.add(R.id.content_layout,mineFragment);
//        fragmentTransaction.replace(R.id.content_layout,homeFragment);
        fragmentTransaction.commit();

        showFragment(homeFragment);

    }

}
