package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.model.FoodBean;
import com.example.dc.refrigeratorproject.model.RefrigeratorBean;

import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */


public class RefrigeratorAdapter extends RecyclerView.Adapter<RefrigeratorAdapter.ViewHolder> {

    private Context context;

    private List<RefrigeratorBean> data;

    private FoodVpAdapter foodVpAdapter;


    public RefrigeratorAdapter(Context context) {

        this.context = context;

    }

    public void updateList(List<RefrigeratorBean> data) {
        this.data = data;
    }

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from (context).inflate (R.layout.item_refrigerator, parent, false);

        return new ViewHolder (view);

    }


    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvKind.setText (data.get (position).getKind ());
        if (data.get (position).getFoodBeanList ()!=null){
            foodVpAdapter.updateList (data.get (position).getFoodBeanList ());
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


    public class ViewHolder extends RecyclerView.ViewHolder {


        private RecyclerView rvItem;
        private TextView tvKind;


        public ViewHolder(View itemView) {

            super (itemView);
            tvKind = itemView.findViewById (R.id.tv_kind);
            rvItem = itemView.findViewById (R.id.vp_food);
            foodVpAdapter = new FoodVpAdapter (context);
            rvItem.setLayoutManager (new LinearLayoutManager (context,
                    LinearLayout.HORIZONTAL, false));
            rvItem.setAdapter (foodVpAdapter);
            foodVpAdapter.setOnListItemClickListener (new FoodVpAdapter.OnListItemClickListener () {
                @Override
                public void onItemClick(FoodBean foodBean) {
                    if (mOnListItemClickListener!=null){
                        mOnListItemClickListener.onItemClick (foodBean);
                    }
                }
            });
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

