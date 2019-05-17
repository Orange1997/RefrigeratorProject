package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.model.FoodBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */

class FoodVpAdapter extends RecyclerView.Adapter<FoodVpAdapter.ViewHolder> {
    private static int ITEM_GRID_COLUMNS;//每一页中GridView中item的数量

    private Context context;

    private List<FoodBean> data;


    public FoodVpAdapter(Context context) {

        this.context = context;

    }

    public void updateList(List<FoodBean> data) {
        this.data = data;
    }


    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from (context).inflate (R.layout.item_food, parent, false);

        return new ViewHolder (view);

    }


    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (mOnListItemClickListener!=null){
                    mOnListItemClickListener.onItemClick (data.get (position));
                }
            }
        });
    }


    @Override

    public int getItemCount() {
        if (data != null && data.size () > 0) {
            return data.size ();
        } else {
            return 0;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private SimpleDraweeView ivFood;

        public ViewHolder(View itemView) {

            super (itemView);
            ivFood = itemView.findViewById (R.id.iv_head);
        }

    }

    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener {
        void onItemClick(FoodBean foodBean);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        mOnListItemClickListener = onListItemClickListener;
    }
}
