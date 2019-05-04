package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorModel;
import com.example.dc.refrigeratorproject.util.ScreenUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by DC on 2019/5/2.
 */

public class RefrigeratorListAdapter extends RecyclerView.Adapter<RefrigeratorListAdapter.ViewHolder> {

    private Context context;

    private List<RefrigeratorModel> data;

    public RefrigeratorListAdapter(Context context) {
        this.context = context;
    }

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from (context).inflate (R.layout.item_list_refrigerator, parent, false);

        return new ViewHolder (view);

    }


    @Override

    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final RefrigeratorModel model = data.get (position);
        if (model.getName () != null && !TextUtils.isEmpty (model.getName ())) {
            Log.e ("333333",model.getName ());
            holder.tvName.setText (model.getName ());
        }

        if (model.getAddress () != null && !TextUtils.isEmpty (model.getAddress ())) {
            holder.tvAddress.setText (model.getAddress ());
        }

        if (model.getCreator () != null) {
            if (model.getCreator ().getHead () != null) {
                holder.ivHead.setImageURI (Uri.parse (model.getCreator ().getHead ()));
            }
            if (model.getCreator ().getAccount () != 0 && model.getCreator ().getAccount () == Config.getUserAccount (context)) {
                holder.ivOwner.setVisibility (View.VISIBLE);
            } else {
                holder.ivOwner.setVisibility (View.GONE);
            }
        }

        if (model.isCurrentRefrigerator) {
            holder.tvCurrent.setVisibility (View.VISIBLE);
        } else {
            holder.tvCurrent.setVisibility (View.GONE);
        }

        if (position != data.size () - 1) {
            holder.diver.setVisibility (View.VISIBLE);
        } else {
            holder.diver.setVisibility (View.GONE);
        }

        holder.rlRef.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (mOnRefItemClickListener != null) {
                    mOnRefItemClickListener.onItemClick (model);
                }
            }
        });
        int w = View.MeasureSpec.makeMeasureSpec (0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec (0, View.MeasureSpec.UNSPECIFIED);
        holder.tvCurrent.measure (w, h);
        int ScreenWidth = ScreenUtils.getScreenWidth (context);
        int maxWidth = holder.tvCurrent.getMeasuredWidth () + 3 * context.getResources ().getDimensionPixelOffset (R.dimen.margin_18) + context.getResources ().getDimensionPixelOffset (R.dimen.margin_tiny) + context.getResources ().getDimensionPixelOffset (R.dimen.margin_huge);
        holder.tvName.setMaxWidth (ScreenWidth - maxWidth);

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
        private TextView tvAddress;
        private TextView tvCurrent;
        private ImageView ivOwner;
        private SimpleDraweeView ivHead;
        private View diver;
        private View rlRef;


        public ViewHolder(View itemView) {

            super (itemView);

            tvName = itemView.findViewById (R.id.tv_name);
            tvAddress = itemView.findViewById (R.id.tv_address);
            diver = itemView.findViewById (R.id.diver);
            tvCurrent = itemView.findViewById (R.id.tv_current);
            rlRef = itemView.findViewById (R.id.rl_ref);
            ivOwner = itemView.findViewById (R.id.iv_owner);
            ivHead = itemView.findViewById (R.id.iv_head);
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
