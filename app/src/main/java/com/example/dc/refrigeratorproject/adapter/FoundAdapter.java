package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.item.ArticleOrRecipesItem;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.BaseViewHolder;
import com.example.dc.refrigeratorproject.adapter.item.ShopItem;
import com.example.dc.refrigeratorproject.adapter.item.TitleItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by DC on 2019/5/11.
 */

public class FoundAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ARTICLE = 10;
    public static final int TYPE_RECIPES = 11;
    public static final int TYPE_SHOP = 12;
    public static final int TYPE_HEAD = 13;

    private Context context;

    private List<BaseItem> items;

    public FoundAdapter(Context context) {
        this.context = context;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ARTICLE) {
            View view = LayoutInflater.from (context).inflate (R.layout.item_article, parent, false);
            return new ArticleViewHolder (view);
        } else if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from (context).inflate (R.layout.item_title, parent, false);
            return new TitleViewHolder (view);
        } else if (viewType == TYPE_RECIPES) {
            View view = LayoutInflater.from (context).inflate (R.layout.item_recipes, parent, false);
            return new RecipesViewHolder (view);
        } else if (viewType == TYPE_SHOP) {
            View view = LayoutInflater.from (context).inflate (R.layout.item_shop, parent, false);
            return new ShopViewHolder (view);
        } else {
            View view = LayoutInflater.from (context).inflate (R.layout.item_article, parent, false);
            return new BaseViewHolder (view);
        }
    }


    @Override

    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        BaseItem baseItem = items.get (position);
        if (baseItem.type == TYPE_ARTICLE && holder instanceof ArticleViewHolder) {
            final ArticleOrRecipesItem articleOrRecipesItem = (ArticleOrRecipesItem) baseItem;
            ((ArticleViewHolder) holder).imgArticle.setImageURI (Uri.parse (articleOrRecipesItem.img));
            ((ArticleViewHolder) holder).tvTitle.setText (articleOrRecipesItem.title != null ? articleOrRecipesItem.title : "");
            ((ArticleViewHolder) holder).tvDes.setText (articleOrRecipesItem.des != null ? articleOrRecipesItem.des : "");
            ((ArticleViewHolder) holder).llContent.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    if (mOnFoundItemClickListener != null) {
                        mOnFoundItemClickListener.onItemClick (articleOrRecipesItem);
                    }
                }
            });


        } else if (baseItem.type == TYPE_HEAD && holder instanceof TitleViewHolder) {
            final TitleItem titleItem = (TitleItem) baseItem;
            ((TitleViewHolder) holder).tvTitle.setText (titleItem.title);
            ((TitleViewHolder) holder).tvMore.setText (titleItem.more);
            ((TitleViewHolder) holder).tvMore.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    if (mOnMoreClickListener != null) {
                        mOnMoreClickListener.onItemClick (titleItem.titleType);
                    }
                }
            });
        } else if (baseItem.type == TYPE_RECIPES && holder instanceof RecipesViewHolder) {
            final ArticleOrRecipesItem recipesItem = (ArticleOrRecipesItem) baseItem;
            ((RecipesViewHolder) holder).tvTitle.setText (recipesItem.title != null ? recipesItem.title : "");
            ((RecipesViewHolder) holder).tvLikes.setText (String.valueOf (recipesItem.likes) + "人已赞");
            ((RecipesViewHolder) holder).img.setImageURI (Uri.parse (recipesItem.img));
            ((RecipesViewHolder) holder).llContent.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    if (mOnFoundItemClickListener != null) {
                        mOnFoundItemClickListener.onItemClick (recipesItem);
                    }
                }
            });
        } else if (baseItem.type == TYPE_SHOP && holder instanceof ShopViewHolder){
            ShopItem shopItem = (ShopItem)baseItem;
            ((ShopViewHolder) holder).img.setImageURI (Uri.parse (shopItem.img));
            ((ShopViewHolder) holder).tvName.setText (shopItem.name!=null?shopItem.name:"");
            ((ShopViewHolder) holder).tvLikes.setText (String.valueOf (shopItem.likes)+"人已赞");
            ((ShopViewHolder) holder).tvAddress.setText (shopItem.location!=null?shopItem.location:"未知");
            ((ShopViewHolder) holder).tvDiscount.setText (String.valueOf (shopItem.discount)+"km");
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
        if (baseItem.type == TYPE_ARTICLE) {
            return TYPE_ARTICLE;
        } else if (baseItem.type == TYPE_HEAD) {
            return TYPE_HEAD;
        } else if (baseItem.type == TYPE_RECIPES) {
            return TYPE_RECIPES;
        } else if (baseItem.type == TYPE_SHOP) {
            return TYPE_SHOP;
        }
        return 0;
    }

    public void updateList(List<BaseItem> items) {
        this.items = items;
        notifyDataSetChanged ();
    }

    private class ArticleViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView imgArticle;
        private TextView tvTitle;
        private TextView tvDes;
        private LinearLayout llContent;

        private ArticleViewHolder(View itemView) {
            super (itemView);
            llContent = itemView.findViewById (R.id.ll_content);
            imgArticle = itemView.findViewById (R.id.img_article);
            tvTitle = itemView.findViewById (R.id.tv_title_article);
            tvDes = itemView.findViewById (R.id.tv_des_article);
        }

    }

    private class RecipesViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView img;
        private TextView tvTitle;
        private TextView tvLikes;
        private LinearLayout llContent;

        private RecipesViewHolder(View itemView) {
            super (itemView);
            img = itemView.findViewById (R.id.img_recipes);
            tvTitle = itemView.findViewById (R.id.tv_title);
            tvLikes = itemView.findViewById (R.id.tv_likes);
            llContent = itemView.findViewById (R.id.ll_content);
        }

    }

    private class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvMore;

        private TitleViewHolder(View itemView) {
            super (itemView);
            tvTitle = itemView.findViewById (R.id.tv_title);
            tvMore = itemView.findViewById (R.id.tv_more);
        }
    }

    private class ShopViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView tvName;
        private TextView tvLikes;
        private TextView tvAddress;
        private TextView tvDiscount;
        private RelativeLayout llContent;

        private ShopViewHolder(View itemView) {
            super (itemView);
            img = itemView.findViewById (R.id.img_shop);
            tvName = itemView.findViewById (R.id.tv_name);
            tvLikes = itemView.findViewById (R.id.tv_fraction);
            llContent = itemView.findViewById (R.id.ll_content);
            tvAddress = itemView.findViewById (R.id.tv_address);
            tvDiscount = itemView.findViewById (R.id.tv_discount);
        }

    }

    private OnFoundItemClickListener mOnFoundItemClickListener;

    public interface OnFoundItemClickListener {
        void onItemClick(BaseItem item);
    }

    public void setOnFoundItemClickListener(OnFoundItemClickListener onFoundItemClickListener) {
        mOnFoundItemClickListener = onFoundItemClickListener;
    }

    private OnMoreClickListener mOnMoreClickListener;

    public interface OnMoreClickListener {
        void onItemClick(int type);
    }

    public void setOnMoreClickListener(OnMoreClickListener onMoreClickListener) {
        mOnMoreClickListener = onMoreClickListener;
    }

}

