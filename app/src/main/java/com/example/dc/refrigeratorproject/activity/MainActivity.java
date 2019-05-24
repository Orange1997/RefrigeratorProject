package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.MyFragmentPagerAdapter;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.event.UpdateFridgeEvent;
import com.example.dc.refrigeratorproject.fragment.RefrigeratorFragment;
import com.example.dc.refrigeratorproject.myView.NoScrollViewPager;
import com.example.dc.refrigeratorproject.resposeBean.User;
import com.example.dc.refrigeratorproject.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener ,View.OnClickListener{

    public static final int PAGE_ONE = 0;
    public static final int PAGE_THREE = 1;
    public static final int PAGE_FOUR = 2;

    private NoScrollViewPager viewpager;
    private RadioButton rbRefrigerator;
    private RadioButton rbInformation;
    private RadioButton rbPersonalCenter;
    private DrawerLayout mDlMain;
    private TextView tvMenuMyRef;
    private ImageView ivExit;
    private SimpleDraweeView ivHead;
    private TextView tvName;

    private MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView ();
    }

    private void initView() {
        RadioGroup rgTabBar = (RadioGroup) findViewById (R.id.rg_tab_bar);
        viewpager = (NoScrollViewPager) findViewById (R.id.vp_main);
        rbRefrigerator = (RadioButton) findViewById (R.id.rb_refrigerator);
        rbInformation = (RadioButton) findViewById (R.id.rb_information);
        rbPersonalCenter = (RadioButton) findViewById (R.id.rb_personal_center);
        mDlMain = (DrawerLayout) findViewById (R.id.dl_main);
        tvMenuMyRef = findViewById (R.id.tv_menu_my_ref);
        ivExit = findViewById (R.id.ic_exit);
        ivHead = findViewById (R.id.iv_head);
        tvName = findViewById (R.id.tv_name);
        User user = Config.getUser (MainActivity.this);
        if (user!=null){
            tvName.setText (user.getUserName ());
        }

        mAdapter = new MyFragmentPagerAdapter (getSupportFragmentManager ());

        viewpager.setAdapter (mAdapter);
        viewpager.setCurrentItem (0);
        viewpager.addOnPageChangeListener (this);
        rgTabBar.setOnCheckedChangeListener (this);
        ivExit.setOnClickListener (this);
        ivHead.setOnClickListener (this);

        tvMenuMyRef.setOnClickListener (this);

        openDrawerListener ();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_refrigerator:
                viewpager.setCurrentItem (PAGE_ONE);
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

    @Override
    public void onClick(View v){
        switch (v.getId ()){
            case R.id.tv_menu_my_ref:
                Intent intent = new Intent (MainActivity.this,RefrigeratorListActivity.class);
                startActivity (intent);
                break;
            case R.id.ic_exit:
                ToastUtil.showShort (MainActivity.this,"退出登录");
                Config.logout (MainActivity.this);
                finish ();
                break;
            case R.id.iv_head:
                Intent intent1 = new Intent (MainActivity.this,PersonalInfoActivity.class);
                startActivity (intent1);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateList(UpdateFridgeEvent messageEvent) {
        if (viewpager.getCurrentItem () == 0) {
            RefrigeratorFragment fragment = (RefrigeratorFragment) mAdapter.getItem (0);
            mDlMain.setDrawerLockMode (DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            fragment.setCurrentFridge (messageEvent.getMessage (),true);
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
