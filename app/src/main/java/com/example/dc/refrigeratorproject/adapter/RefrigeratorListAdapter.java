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
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorModel;
import com.example.dc.refrigeratorproject.util.ScreenUtils;

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
        if (model.getName () != null && TextUtils.isEmpty (model.getName ())) {
            holder.tvName.setText (model.getName ());
        }

//        if (model.getAddress () != null && TextUtils.isEmpty (model.getAddress ())) {
//            holder.tvAddress.setText (model.getAddress ());
//        }

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
        int maxWidth = holder.tvCurrent.getMeasuredWidth () + 2 * context.getResources ().getDimensionPixelOffset (R.dimen.margin_18) + context.getResources ().getDimensionPixelOffset (R.dimen.margin_tiny);
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
//        private TextView tvAddress;
        private TextView tvCurrent;
        private View diver;
        private View rlRef;


        public ViewHolder(View itemView) {

            super (itemView);

            tvName = itemView.findViewById (R.id.tv_name);
//            tvAddress = itemView.findViewById (R.id.tv_address);
            diver = itemView.findViewById (R.id.diver);
            tvCurrent = itemView.findViewById (R.id.tv_current);
            rlRef = itemView.findViewById (R.id.rl_ref);
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
