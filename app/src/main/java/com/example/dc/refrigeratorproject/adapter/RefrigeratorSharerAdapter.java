package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorSharerModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by DC on 2019/5/1.
 */

public class RefrigeratorSharerAdapter extends RecyclerView.Adapter<RefrigeratorSharerAdapter.ViewHolder> {

    private Context context;

    private List<RefrigeratorSharerModel> data;

    public RefrigeratorSharerAdapter(Context context) {
        this.context = context;
    }

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from (context).inflate (R.layout.item_refrigerator_group_people, parent, false);

        return new ViewHolder (view);

    }


    @Override

    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final RefrigeratorSharerModel model = data.get (position);
        if (model.getSharerHeadUrl () != null) {
            holder.ivHead.setImageURI (Uri.parse (model.getSharerHeadUrl ()));
        }
        holder.tvAccount.setText (model.getSharerAccount () != 0 ? "(" + String.valueOf (model.getSharerAccount ()) + ")" : "");
        holder.tvName.setText (model.getSharerName () != null ? model.getSharerName () : "");
        if (model.isCreator ()) {
            holder.ivCreator.setVisibility (View.VISIBLE);
        } else {
            holder.ivCreator.setVisibility (View.GONE);
        }

        if (position != data.size () - 1) {
            holder.diver.setVisibility (View.VISIBLE);
        } else {
            holder.diver.setVisibility (View.GONE);
        }

        holder.tvDelete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (mOnDeleteClickListener != null) {
                    mOnDeleteClickListener.onDelete (model);
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

    public void updateList(List<RefrigeratorSharerModel> list) {
        this.data = list;
        notifyDataSetChanged ();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView ivHead;
        private TextView tvName;
        private TextView tvAccount;
        private ImageView ivCreator;
        private TextView tvDelete;
        private View diver;


        public ViewHolder(View itemView) {

            super (itemView);

            ivHead = itemView.findViewById (R.id.iv_head);
            tvName = itemView.findViewById (R.id.tv_name);
            tvAccount = itemView.findViewById (R.id.tv_account);
            ivCreator = itemView.findViewById (R.id.iv_is_creator);
            diver = itemView.findViewById (R.id.diver);
            tvDelete = itemView.findViewById (R.id.tv_delete);
        }

    }

    private OnDeleteClickListener mOnDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDelete(RefrigeratorSharerModel model);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        mOnDeleteClickListener = onDeleteClickListener;
    }

}


