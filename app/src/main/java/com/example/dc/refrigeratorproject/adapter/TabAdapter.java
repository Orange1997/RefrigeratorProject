package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.util.ScreenUtils;

import java.util.List;

/**
 * Created by DC on 2019/5/15.
 */

public class TabAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> list;
    private int chooseIndex;
    private OnItemClickListener onItemClickListener;

    public TabAdapter(List<String> list, Context context) {
        this.mContext = context;
        this.list = list;
        chooseIndex = -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from (mContext).inflate (R.layout.item_nearby_tab, parent, false);
        return new ContractTabViewHolder (view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((ContractTabViewHolder) holder).rbContractTab.setText (list.get (position));
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick (v, position, getItemId (position));
                }
            }
        });

        if (chooseIndex == position) {
            ((ContractTabViewHolder) holder).rbContractTab.setChecked (true);
            ((ContractTabViewHolder) holder).rbContractTab.setTextSize (16);
        } else {
            ((ContractTabViewHolder) holder).rbContractTab.setChecked (false);
            ((ContractTabViewHolder) holder).rbContractTab.setTextSize (14);
        }

        RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) ((ContractTabViewHolder) holder).rbContractTab.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

        linearParams.width = ScreenUtils.getScreenWidth (mContext)/list.size ();

        ((ContractTabViewHolder) holder).rbContractTab.setLayoutParams(linearParams);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size () <= 0) {
            return 0;
        } else {
            return list.size ();
        }
    }

    public void setDefaultCheckedItemPosition(int position) {
        chooseIndex = position;
    }

    public int getCheckedItemPosition() {
        return chooseIndex;
    }

    public void check(int position) {
        setDefaultCheckedItemPosition (position);
        notifyDataSetChanged ();
    }

    public void setOnItemClickListener(@NonNull OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    class ContractTabViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbContractTab;
        int position;

        ContractTabViewHolder(View itemView) {
            super (itemView);
            rbContractTab = itemView.findViewById (R.id.rb_contract_tab);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, long id);
    }
}