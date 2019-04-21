package com.example.dc.refrigeratorproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */

class FoodVpAdapter extends PagerAdapter {
    private List<GridView> gridList;

    FoodVpAdapter() {
        gridList = new ArrayList<> ();
    }

    void add(List<GridView> datas) {
        if (gridList.size() > 0) {
            gridList.clear();
        }
        gridList.addAll(datas);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return gridList.size();
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(gridList.get(position));
        return gridList.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
