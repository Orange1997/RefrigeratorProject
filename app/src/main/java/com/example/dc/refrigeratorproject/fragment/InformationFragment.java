package com.example.dc.refrigeratorproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.CommunityPagerAdapter;
import com.example.dc.refrigeratorproject.view.NoScrollViewPager;

/**
 * Created by DC on 2019/3/8.
 */

public class InformationFragment extends Fragment implements ViewPager.OnPageChangeListener,RadioGroup.OnCheckedChangeListener{
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;

    private NoScrollViewPager viewpager;
    private RadioButton rbFound;
    private RadioButton rbCommunity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RadioGroup rgTabBar = (RadioGroup) view.findViewById (R.id.rg_tab_bar);
        viewpager = (NoScrollViewPager) view.findViewById (R.id.vp_main);
        rbFound = (RadioButton) view.findViewById (R.id.rb_found);
        rbCommunity = (RadioButton) view.findViewById (R.id.rb_community);

        if (getActivity ()!=null){
            CommunityPagerAdapter adapter = new CommunityPagerAdapter (getChildFragmentManager ());
            viewpager.setAdapter (adapter);
        }



        viewpager.setCurrentItem (0);
        viewpager.addOnPageChangeListener (this);
        rgTabBar.setOnCheckedChangeListener (this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_found:
                viewpager.setCurrentItem (PAGE_ONE);
                rbFound.setTextSize (17);
                rbCommunity.setTextSize (16);
                break;
            case R.id.rb_community:
                viewpager.setCurrentItem (PAGE_TWO);
                rbCommunity.setTextSize (17);
                rbFound.setTextSize (16);
                break;
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
                rbFound.setChecked (true);

                break;
            case PAGE_TWO:
                rbCommunity.setChecked (true);

                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public InformationFragment() {
    }
}
