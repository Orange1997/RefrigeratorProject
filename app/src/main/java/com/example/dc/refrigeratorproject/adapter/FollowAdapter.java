package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.FollowItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by DC on 2019/5/11.
 */

public class FollowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<BaseItem> items;

    public static final int TYPE_FOLLOW = 2001;

    public FollowAdapter(Context context) {
        this.context = context;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_follow, parent, false);
        return new FollowViewHolder (view);
    }


    @Override

    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final BaseItem baseItem = items.get (position);
        final FollowItem followItem = (FollowItem) baseItem;
        ((FollowViewHolder) holder).img.setImageURI (followItem.img != null ? Uri.parse (followItem.img) : Uri.parse ("https://dpic.tiankong.com/38/fv/QJ6942396456.jpg?x-oss-process=style/670ws"));
        ((FollowViewHolder) holder).tvName.setText (followItem.name != null ? followItem.name : "");
        ((FollowViewHolder) holder).tvIntro.setText (followItem.intro != null ? followItem.intro : "");
        if (position < items.size () - 1) {
            ((FollowViewHolder) holder).diver.setVisibility (View.VISIBLE);
        } else {
            ((FollowViewHolder) holder).diver.setVisibility (View.GONE);
        }

        if (followItem.isFollow) {
            ((FollowViewHolder) holder).tvFollow.setVisibility (View.VISIBLE);
            ((FollowViewHolder) holder).tvFollowAgain.setVisibility (View.GONE);
        } else {
            ((FollowViewHolder) holder).tvFollow.setVisibility (View.GONE);
            ((FollowViewHolder) holder).tvFollowAgain.setVisibility (View.VISIBLE);
        }
        ((FollowViewHolder) holder).tvFollow.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (mOnCancelFollowClickListener != null) {
                    mOnCancelFollowClickListener.onCancelFollow (followItem, position);
                }
            }
        });

        ((FollowViewHolder) holder).tvFollowAgain.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (mOnCancelFollowClickListener != null) {
                    mOnCancelFollowClickListener.onCancelFollow (followItem, position);
                }
            }
        });

    }


    @Override

    public int getItemCount() {
        if (items != null) {
            return items.size ();
        }
        return 0;
    }


    public void updateList(List<BaseItem> items) {
        this.items = items;
        notifyDataSetChanged ();
    }

    private class FollowViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView img;
        private TextView tvName, tvIntro, tvFollow, tvFollowAgain;
        private View diver;


        private FollowViewHolder(View itemView) {

            super (itemView);
            img = itemView.findViewById (R.id.iv_head_follow);
            tvName = itemView.findViewById (R.id.tv_name);
            tvIntro = itemView.findViewById (R.id.tv_into);
            diver = itemView.findViewById (R.id.diver);
            tvFollow = itemView.findViewById (R.id.tv_follow);
            tvFollowAgain = itemView.findViewById (R.id.tv_follow_again);
        }

    }

    private OnCancelFollowClickListener mOnCancelFollowClickListener;

    public interface OnCancelFollowClickListener {
        void onCancelFollow(FollowItem item, int position);
    }

    public void setOnCancelFollowClickListener(OnCancelFollowClickListener onCancelFollowClickListener) {
        mOnCancelFollowClickListener = onCancelFollowClickListener;
    }


}

