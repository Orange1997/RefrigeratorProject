package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.model.FoodBean;

import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {



    private Context context;

    private List<FoodBean> data;



    public FoodAdapter(Context context){

        this.context = context;

    }

    public void updateList(List<FoodBean> data){
        this.data = data;
        notifyDataSetChanged ();
    }

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_food,parent,false);

        return new ViewHolder(view);

    }



    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ivFood.setBackgroundResource (data.get (position).getImgId ());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


            }

        });



    }



    @Override

    public int getItemCount() {

        return data.size();

    }



    public class ViewHolder extends RecyclerView.ViewHolder{



        private ImageView ivFood;



        public ViewHolder(View itemView) {

            super(itemView);

        }

    }

}

