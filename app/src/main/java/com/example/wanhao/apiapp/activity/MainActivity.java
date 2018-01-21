package com.example.wanhao.apiapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.wanhao.apiapp.R;
import com.example.wanhao.apiapp.adapter.PagerAdapter;
import com.example.wanhao.apiapp.fragment.MainFragment;
import com.example.wanhao.apiapp.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ViewPager viewPager;
    TabLayout tabLayout;
    MainFragment mainFragment;
    OtherFragment otherFragment;
    List<Fragment> list = new ArrayList<>();

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();

    }
    //  -----------------------------初始化view的位置------------------------------------------------

    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.pager);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        //getSupportActionBar().hide();

        mainFragment = new MainFragment();
        otherFragment = new OtherFragment();
        list.add(mainFragment);
        list.add(otherFragment);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),list));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initEvent() {

    }

}
