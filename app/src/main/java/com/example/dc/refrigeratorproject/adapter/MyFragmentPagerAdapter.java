package com.example.dc.refrigeratorproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.dc.refrigeratorproject.activity.MainActivity;
import com.example.dc.refrigeratorproject.fragment.InformationFragment;
import com.example.dc.refrigeratorproject.fragment.PersonalCenterFragment;
import com.example.dc.refrigeratorproject.fragment.RefrigeratorFragment;

/**
 * Created by DC on 2019/3/8.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 3;
    private RefrigeratorFragment refrigeratorFragment;
    private InformationFragment informationFragment;
    private PersonalCenterFragment personalCenterFragment;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        refrigeratorFragment = new RefrigeratorFragment();
        informationFragment = new InformationFragment();
        personalCenterFragment = new PersonalCenterFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = refrigeratorFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = informationFragment;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = personalCenterFragment;
                break;
        }
        return fragment;
    }


}
