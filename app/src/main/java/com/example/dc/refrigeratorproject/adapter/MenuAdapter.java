package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.model.MenuModel;

import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Context context;

    private List<MenuModel.Menu> data;

    private OnItemClickListener onItemClickListener;

    public MenuAdapter(Context context) {
        MenuModel menuModel = new MenuModel ();
        this.context = context;
        this.data = menuModel.getList ();

    }

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from (context).inflate (R.layout.item_menu, parent, false);

        return new ViewHolder (view);

    }


    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ivIcon.setBackgroundResource (data.get (position).getIcon ());
        holder.tvTitle.setText (data.get (position).getTitle ());
        holder.itemView.setOnClickListener (new View.OnClickListener () {

            @Override

            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick (data.get (position));
                }

            }

        });
        if (position < data.size () - 1) {
            holder.diver.setVisibility (View.VISIBLE);
        } else {
            holder.diver.setVisibility (View.GONE);
        }

    }


    @Override

    public int getItemCount() {

        return data.size ();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;
        private TextView tvTitle;
        private View diver;


        public ViewHolder(View itemView) {

            super (itemView);
            diver = itemView.findViewById (R.id.diver);
            ivIcon = itemView.findViewById (R.id.iv_menu_icon);
            tvTitle = itemView.findViewById (R.id.tv_menu_title);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(MenuModel.Menu menu);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

