package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.FoodItem;
import com.example.dc.refrigeratorproject.resposeBean.GetFoodListRes;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */


public class RefrigeratorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_TITLE = 11;
    public static final int TYPE_FOOD = 12;

    private Context context;

    private List<BaseItem> data;


    public RefrigeratorAdapter(Context context) {

        this.context = context;

    }

    public void updateList(List<BaseItem> data) {
        this.data = data;
        notifyDataSetChanged ();
    }

    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_food, parent, false);

        return new FoodViewHolder (view);

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        BaseItem baseItem = data.get (position);
        if (baseItem.type == TYPE_FOOD && holder instanceof FoodViewHolder) {
            final FoodItem foodItem = (FoodItem) baseItem;
            if (foodItem.food.getFoodType ()==1){
                ((FoodViewHolder) holder).img.setImageURI (Uri.parse("res://com.example.dc.refrigeratorproject/" + R.drawable.ic_veg));
            }else if (foodItem.food.getFoodType ()==2){
                ((FoodViewHolder) holder).img.setImageURI (Uri.parse("res://com.example.dc.refrigeratorproject/" + R.drawable.ic_meat));
            }else if (foodItem.food.getFoodType ()==3){
                ((FoodViewHolder) holder).img.setImageURI (Uri.parse("res://com.example.dc.refrigeratorproject/" + R.drawable.ic_meat));
            }else if (foodItem.food.getFoodType ()==4){
                ((FoodViewHolder) holder).img.setImageURI (Uri.parse("res://com.example.dc.refrigeratorproject/" + R.drawable.ic_other));
            }
            ((FoodViewHolder) holder).tvName.setText (foodItem.food.getFoodName ());
            holder.itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    if (mOnListItemClickListener != null) {
                        mOnListItemClickListener.onItemClick (foodItem.food);
                    }
                }
            });
        }

    }


    @Override

    public int getItemCount() {
        if (data != null && data.size () > 0) {
            return data.size ();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        BaseItem baseItem = data.get (position);
        if (baseItem.type == TYPE_FOOD) {
            return TYPE_FOOD;
        }
        return 0;
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder {


        private SimpleDraweeView img;
        private TextView tvName;


        public FoodViewHolder(View itemView) {

            super (itemView);
            img = itemView.findViewById (R.id.iv_head);
            tvName = itemView.findViewById (R.id.tv_name);
        }

    }

    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener {
        void onItemClick(GetFoodListRes foodBean);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        mOnListItemClickListener = onListItemClickListener;
    }
}

