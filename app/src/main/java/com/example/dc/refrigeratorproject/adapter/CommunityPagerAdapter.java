package com.example.dc.refrigeratorproject.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.dc.refrigeratorproject.fragment.CommunityFragment;
import com.example.dc.refrigeratorproject.fragment.FoundFragment;
import com.example.dc.refrigeratorproject.fragment.InformationFragment;

/**
 * Created by DC on 2019/3/8.
 */

public class CommunityPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 2;
    private FoundFragment foundFragment;
    private CommunityFragment communityFragment;


    public CommunityPagerAdapter(FragmentManager fm) {
        super(fm);
        foundFragment = new FoundFragment();
        communityFragment = new CommunityFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case InformationFragment.PAGE_ONE:
                fragment = foundFragment;
                break;
            case InformationFragment.PAGE_TWO:
                fragment = communityFragment;
                break;
        }
        return fragment;
    }


}
