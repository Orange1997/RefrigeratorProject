package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.MyFragmentPagerAdapter;
import com.example.dc.refrigeratorproject.fragment.RefrigeratorFragment;
import com.example.dc.refrigeratorproject.view.NoScrollViewPager;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private NoScrollViewPager viewpager;
    private RadioButton rbRefrigerator;
    private RadioButton rbInformation;
    private RadioButton rbPersonalCenter;
    private RadioButton rbList;
    private DrawerLayout mDlMain;

    private MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        initView ();
    }

    private void initView() {
        RadioGroup rgTabBar = (RadioGroup) findViewById (R.id.rg_tab_bar);
        viewpager = (NoScrollViewPager) findViewById (R.id.vp_main);
        rbRefrigerator = (RadioButton) findViewById (R.id.rb_refrigerator);
        rbInformation = (RadioButton) findViewById (R.id.rb_information);
        rbPersonalCenter = (RadioButton) findViewById (R.id.rb_personal_center);
        rbList = (RadioButton) findViewById (R.id.rb_list);
        mDlMain = (DrawerLayout) findViewById (R.id.dl_main);

        mAdapter = new MyFragmentPagerAdapter (getSupportFragmentManager ());

        viewpager.setAdapter (mAdapter);
        viewpager.setCurrentItem (0);
        viewpager.addOnPageChangeListener (this);
        rgTabBar.setOnCheckedChangeListener (this);

        openDrawerListener ();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_refrigerator:
                viewpager.setCurrentItem (PAGE_ONE);
                break;
            case R.id.rb_list:
                viewpager.setCurrentItem (PAGE_TWO);
                break;
            case R.id.rb_information:
                viewpager.setCurrentItem (PAGE_THREE);
                break;
            case R.id.rb_personal_center:
                viewpager.setCurrentItem (PAGE_FOUR);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        int currentItemPosition = viewpager.getCurrentItem ();
        switch (currentItemPosition) {
            case PAGE_ONE:
                rbRefrigerator.setChecked (true);
                //允许手指滑动
                mDlMain.setDrawerLockMode (DrawerLayout.LOCK_MODE_UNLOCKED);
                openDrawerListener ();
                break;
            case PAGE_TWO:
                rbList.setChecked (true);
                //禁止手指滑动弹出
                mDlMain.setDrawerLockMode (DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case PAGE_THREE:
                rbInformation.setChecked (true);
                mDlMain.setDrawerLockMode (DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case PAGE_FOUR:
                rbPersonalCenter.setChecked (true);
                mDlMain.setDrawerLockMode (DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
        }
    }

    private void openDrawerListener() {
        if (viewpager.getCurrentItem () == 0) {
            RefrigeratorFragment fragment = (RefrigeratorFragment) mAdapter.getItem (0);
            fragment.setOnDrawerOpenListener (new RefrigeratorFragment.OnDrawerOpenListener () {
                @Override
                public void onDrawerOpen() {
                    mDlMain.openDrawer (Gravity.START);
                }
            });
        }
    }


}
