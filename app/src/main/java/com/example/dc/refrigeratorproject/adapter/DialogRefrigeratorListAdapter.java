package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorModel;

import java.util.List;

/**
 * Created by DC on 2019/5/2.
 */

public class DialogRefrigeratorListAdapter extends RecyclerView.Adapter<DialogRefrigeratorListAdapter.ViewHolder> {

    private Context context;

    private List<RefrigeratorModel> data;

    public DialogRefrigeratorListAdapter(Context context) {
        this.context = context;
    }

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from (context).inflate (R.layout.item_dialog_ref_list, parent, false);

        return new ViewHolder (view);

    }


    @Override

    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final RefrigeratorModel model = data.get (position);
        if (model.getName () != null && !TextUtils.isEmpty (model.getName ())) {
            holder.tvName.setText (model.getName ());
        }

        if (model.isCurrentRefrigerator()){
            holder.ivChecked.setVisibility (View.VISIBLE);
        }else {
            holder.ivChecked.setVisibility (View.GONE);
        }


        if (position != data.size () - 1) {
            holder.diver.setVisibility (View.VISIBLE);
        } else {
            holder.diver.setVisibility (View.GONE);
        }

        holder.tvName.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (mOnRefItemClickListener!=null){
                    mOnRefItemClickListener.onItemClick (model);
                }
            }
        });

    }


    @Override

    public int getItemCount() {
        if (data != null) {
            return data.size ();
        }
        return 0;

    }

    public void updateList(List<RefrigeratorModel> list) {
        this.data = list;
        notifyDataSetChanged ();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private View diver;
        private ImageView ivChecked;


        public ViewHolder(View itemView) {

            super (itemView);
            ivChecked = itemView.findViewById (R.id.iv_check);
            tvName = itemView.findViewById (R.id.tv_name);
            diver = itemView.findViewById (R.id.diver);
        }

    }

    private OnRefItemClickListener mOnRefItemClickListener;

    public interface OnRefItemClickListener {
        void onItemClick(RefrigeratorModel model);
    }

    public void setOnRefItemClickListener(OnRefItemClickListener onRefItemClickListener) {
        mOnRefItemClickListener = onRefItemClickListener;
    }

}
