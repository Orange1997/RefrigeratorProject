package com.example.dc.refrigeratorproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.model.FoodBean;

import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */

public class FoodGvAdapter extends BaseAdapter {
    private List<FoodBean> dataList;

    public FoodGvAdapter(List<FoodBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }
    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (itemView == null) {
            mHolder = new ViewHolder();
            itemView= LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_food, viewGroup, false);
            itemView.setTag(mHolder);
        }
        else {
            mHolder = (ViewHolder) itemView.getTag();
        }

        FoodBean bean = dataList.get(i);
        if (bean != null) {

        }
        return itemView;
    }
    private class ViewHolder {
        private ImageView iv_img;
    }

}
