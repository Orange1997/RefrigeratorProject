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
import com.example.dc.refrigeratorproject.adapter.item.ArticleOrRecipesItem;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_ARTICLE;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_RECIPES;

/**
 * Created by DC on 2019/5/11.
 */

public class MoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<BaseItem> items;

    public MoreAdapter(Context context) {
        this.context = context;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_details, parent, false);
        return new ListViewHolder (view);

    }


    @Override

    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final BaseItem baseItem = items.get (position);
        if (baseItem.type == TYPE_ARTICLE && holder instanceof ListViewHolder) {
            final ArticleOrRecipesItem articleOrRecipesItem = (ArticleOrRecipesItem) baseItem;
            ((ListViewHolder) holder).img.setImageURI (Uri.parse (articleOrRecipesItem.img));
            ((ListViewHolder) holder).tvTitle.setText (articleOrRecipesItem.title != null ? articleOrRecipesItem.title : "");
            ((ListViewHolder) holder).tvAuthor.setText (articleOrRecipesItem.author);
            ((ListViewHolder) holder).rlItem.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    if (mOnListItemClickListener != null) {
                        mOnListItemClickListener.onItemClick (articleOrRecipesItem);
                    }
                }
            });
        } else if (baseItem.type == TYPE_RECIPES && holder instanceof ListViewHolder) {
            final ArticleOrRecipesItem articleOrRecipesItem = (ArticleOrRecipesItem) baseItem;
            ((ListViewHolder) holder).img.setImageURI (Uri.parse (articleOrRecipesItem.img));
            ((ListViewHolder) holder).tvTitle.setText (articleOrRecipesItem.title != null ? articleOrRecipesItem.title : "");
            ((ListViewHolder) holder).tvAuthor.setText (articleOrRecipesItem.likes + "人已赞");
            ((ListViewHolder) holder).rlItem.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    if (mOnListItemClickListener != null) {
                        mOnListItemClickListener.onItemClick (articleOrRecipesItem);
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


    public void updateList(List<BaseItem> items) {
        this.items = items;
        notifyDataSetChanged ();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView img;
        private TextView tvTitle, tvAuthor;
        private View rlItem;


        private ListViewHolder(View itemView) {

            super (itemView);
            img = itemView.findViewById (R.id.img);
            tvTitle = itemView.findViewById (R.id.tv_title);
            tvAuthor = itemView.findViewById (R.id.tv_author);
            rlItem = itemView.findViewById (R.id.rl_content);
        }

    }

    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener {
        void onItemClick(BaseItem item);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        mOnListItemClickListener = onListItemClickListener;
    }

}

