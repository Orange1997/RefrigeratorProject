package com.example.dc.refrigeratorproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dc.refrigeratorproject.Mock;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.activity.DetailActivity;
import com.example.dc.refrigeratorproject.activity.MoreActivity;
import com.example.dc.refrigeratorproject.adapter.FoundAdapter;
import com.example.dc.refrigeratorproject.adapter.item.ArticleOrRecipesItem;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.ShopItem;
import com.example.dc.refrigeratorproject.adapter.item.TitleItem;
import com.example.dc.refrigeratorproject.model.ArticleOrRecipesModel;
import com.example.dc.refrigeratorproject.model.ShopModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_ARTICLE;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_HEAD;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_RECIPES;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_SHOP;
import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_TYPE;
import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_URL;

/**
 * Created by DC on 2019/5/5.
 */

public class FoundFragment extends Fragment {
    public static final int TITLE_TYPE_ARTICLE = 1;
    public static final int TITLE_TYPE_RECIPES = 2;
    public static final int TITLE_TYPE_SHOP = 3;

    private RecyclerView rvFound;
    private FoundAdapter adapter;
    List<BaseItem> baseItems = new ArrayList<> ();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_found, container, false);
        initView (view);
        updateList ();
        return view;
    }

    private void initView(View view) {
        rvFound = view.findViewById (R.id.rv_found);
        GridLayoutManager gridLayoutManager = new GridLayoutManager (getContext (), 2);
        gridLayoutManager.setSpanSizeLookup (new GridLayoutManager.SpanSizeLookup () {
            @Override
            public int getSpanSize(int postition) {
                int type = adapter.getItemViewType (postition);
                if (type == TYPE_HEAD || type == TYPE_ARTICLE || type == TYPE_SHOP) {
                    return 2;
                } else if (type == TYPE_RECIPES) {
                    return 1;
                }
                return 1;
            }
        });
        rvFound.setLayoutManager (gridLayoutManager);
        adapter = new FoundAdapter (getActivity ());
        rvFound.setAdapter (adapter);
        adapter.setOnMoreClickListener (new FoundAdapter.OnMoreClickListener () {
            @Override
            public void onItemClick(int type) {
                Intent intent = new Intent (getActivity (), MoreActivity.class);
                intent.putExtra (KEY_FOUND_TYPE, type);
                startActivity (intent);
            }
        });
        adapter.setOnFoundItemClickListener (new FoundAdapter.OnFoundItemClickListener () {
            @Override
            public void onItemClick(BaseItem item) {
                ArticleOrRecipesItem articleOrRecipesItem = (ArticleOrRecipesItem)item;
                Intent intent = new Intent (getActivity (), DetailActivity.class);
                intent.putExtra (KEY_FOUND_URL,articleOrRecipesItem.url);
                startActivity (intent);
            }
        });
    }

    private void updateList() {
        baseItems.clear ();
        baseItems.add (new TitleItem (TYPE_HEAD, "今日推荐", "更多", 1));
        baseItems.add (new ArticleOrRecipesItem (Mock.getArticleModels ().get (0), TYPE_ARTICLE));
        baseItems.add (new TitleItem (TYPE_HEAD, "热门食谱", "更多", 2));
        List<ArticleOrRecipesModel> recipesModelList = Mock.getRecipesModels ();
        baseItems.add (new ArticleOrRecipesItem (recipesModelList.get (0), TYPE_RECIPES));
        baseItems.add (new ArticleOrRecipesItem (recipesModelList.get (1), TYPE_RECIPES));
        baseItems.add (new TitleItem (TYPE_HEAD, "附近店铺", "更多", 3));
        List<ShopModel> shopModelList = Mock.getShopModelList ();
        for (ShopModel shopModel : shopModelList) {
            baseItems.add (new ShopItem (shopModel, TYPE_SHOP));
        }
        adapter.updateList (baseItems);
    }

    @Override
    public void onResume() {
        super.onResume ();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView ();
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
    }

    public FoundFragment() {
    }
}
