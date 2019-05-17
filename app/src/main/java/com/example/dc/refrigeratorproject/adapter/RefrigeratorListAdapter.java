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
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorListRes;
import com.example.dc.refrigeratorproject.resposeBean.User;
import com.example.dc.refrigeratorproject.util.ScreenUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by DC on 2019/5/2.
 */

public class RefrigeratorListAdapter extends RecyclerView.Adapter<RefrigeratorListAdapter.ViewHolder> {

    private Context context;

    private List<RefrigeratorListRes> data;

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
        final RefrigeratorListRes model = data.get (position);
        if (model.getFridgeName () != null && !TextUtils.isEmpty (model.getFridgeName ())) {
            holder.tvName.setText (model.getFridgeName ());
        }

        if (model.getAddress () != null && !TextUtils.isEmpty (model.getAddress ())) {
            holder.tvAddress.setText (model.getAddress ());
        }
//
        if (model.getUserId () == Config.getUserId (context)) {
            holder.ivOwner.setVisibility (View.VISIBLE);
        } else {
            holder.ivOwner.setVisibility (View.GONE);
        }

        User user = Config.getUser (context);
        if (user != null) {
            if (model.getFridgeId () == user.getCurrentFridgeId ()) {
                holder.tvCurrent.setVisibility (View.VISIBLE);
            } else {
                holder.tvCurrent.setVisibility (View.GONE);
            }
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

    public void updateList(List<RefrigeratorListRes> list) {
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
        void onItemClick(RefrigeratorListRes model);
    }

    public void setOnRefItemClickListener(OnRefItemClickListener onRefItemClickListener) {
        mOnRefItemClickListener = onRefItemClickListener;
    }

}
