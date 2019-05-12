package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dc.refrigeratorproject.Mock;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.MoreAdapter;
import com.example.dc.refrigeratorproject.adapter.item.ArticleOrRecipesItem;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.model.ArticleOrRecipesModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_ARTICLE;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_RECIPES;
import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_TYPE;
import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_URL;
import static com.example.dc.refrigeratorproject.fragment.FoundFragment.TITLE_TYPE_ARTICLE;
import static com.example.dc.refrigeratorproject.fragment.FoundFragment.TITLE_TYPE_RECIPES;

/**
 * Created by DC on 2019/5/12.
 */

public class MoreActivity extends BaseActivity {
    private MoreAdapter adapter;
    private List<BaseItem> baseItems = new ArrayList<> ();
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_more);
        type = getIntent ().getIntExtra (KEY_FOUND_TYPE, -1);
        initView ();
        updateList ();
    }

    private void initView() {
        ImageView ivBack = findViewById (R.id.iv_back);
        LinearLayout llSearch = findViewById (R.id.ll_search);

        ivBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });

        llSearch.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MoreActivity.this, SearchActivity.class);
                startActivity (intent);
            }
        });

        RecyclerView recyclerView = findViewById (R.id.rv_detail);
        LinearLayoutManager layoutManager = new LinearLayoutManager (this);
        layoutManager.setOrientation (OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager (layoutManager);
        adapter = new MoreAdapter (this);
        recyclerView.setAdapter (adapter);
        adapter.setOnListItemClickListener (new MoreAdapter.OnListItemClickListener () {
            @Override
            public void onItemClick(BaseItem item) {
                ArticleOrRecipesItem articleOrRecipesItem = (ArticleOrRecipesItem)item;
                Intent intent = new Intent (MoreActivity.this, DetailActivity.class);
                intent.putExtra (KEY_FOUND_URL,articleOrRecipesItem.url);
                startActivity (intent);
            }
        });
    }

    private void updateList() {
        baseItems.clear ();
        if (type == TITLE_TYPE_ARTICLE) {
            for (ArticleOrRecipesModel articleOrRecipesModel : Mock.getArticleModels ()) {
                baseItems.add (new ArticleOrRecipesItem (articleOrRecipesModel, TYPE_ARTICLE));
            }
        } else if (type == TITLE_TYPE_RECIPES) {
            for (ArticleOrRecipesModel articleOrRecipesModel : Mock.getRecipesModels ()) {
                baseItems.add (new ArticleOrRecipesItem (articleOrRecipesModel, TYPE_RECIPES));
            }
        }

        adapter.updateList (baseItems);
    }
}