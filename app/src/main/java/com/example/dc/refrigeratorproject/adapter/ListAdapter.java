package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.BaseViewHolder;
import com.example.dc.refrigeratorproject.adapter.item.ListItem;

import java.util.List;

/**
 * Created by DC on 2019/5/11.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_LIST = 1;

    private Context context;

    private List<BaseItem> items;

    public ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_HEAD){
            View view = LayoutInflater.from (context).inflate (R.layout.head_list, parent, false);
            return new BaseViewHolder (view);
        }

        View view = LayoutInflater.from (context).inflate (R.layout.item_list, parent, false);

        return new ListViewHolder (view);

    }


    @Override

    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        BaseItem baseItem = items.get (position);

        if (baseItem.type==TYPE_HEAD&&holder instanceof BaseViewHolder){

        }else if (baseItem.type==TYPE_LIST&&holder instanceof ListViewHolder){
            final ListItem listItem = (ListItem) baseItem;
            if (listItem.time != null) {
               ((ListViewHolder) holder).tvName.setText (String.valueOf (listItem.time));
            }
            if (!TextUtils.isEmpty (listItem.title)){
                ((ListViewHolder) holder).tvName.setText (String.valueOf (listItem.title));
            }
            ((ListViewHolder) holder).rlItem.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    if (mOnListItemClickListener!=null){
                        mOnListItemClickListener.onItemClick (listItem);
                    }
                }
            });
        }

    }


    @Override

    public int getItemCount() {
        if (items != null) {
            return items.size ();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        BaseItem baseItem = items.get (position);
        if (baseItem.type==TYPE_LIST){
            return TYPE_LIST;
        }else {
            return TYPE_HEAD;
        }
    }

    public void updateList(List<BaseItem> items) {
        this.items = items;
        notifyDataSetChanged ();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvTime;
        private View rlItem;


        public ListViewHolder(View itemView) {

            super (itemView);

            tvName = itemView.findViewById (R.id.tv_name);
            tvTime = itemView.findViewById (R.id.tv_time);
            rlItem = itemView.findViewById (R.id.rl_list_item);
        }

    }

    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener {
        void onItemClick(ListItem item);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        mOnListItemClickListener = onListItemClickListener;
    }

}

