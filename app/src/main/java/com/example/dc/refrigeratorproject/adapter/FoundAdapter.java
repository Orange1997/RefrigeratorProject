package com.example.dc.refrigeratorproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DC on 2019/5/5.
 */

public class FoundAdapter extends RecyclerView.Adapter<FoundAdapter.ViewHolder> {
    public static final int TYPE_TITLE_MORE = 1;
    public static final int TYPE_ARTICLE = 11;
    public static final int TYPE_RECIPE = 12;
    public static final int TYPE_STORE = 13;


    private List<Object> dataItems = new ArrayList<> ();

    public void setDataItems(List<Object> dataItems) {
        this.dataItems = dataItems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvContent;
        TextView tvSource;
        TextView tvTime;

        public ViewHolder(View view) {
            super(view);

        }
    }

}
