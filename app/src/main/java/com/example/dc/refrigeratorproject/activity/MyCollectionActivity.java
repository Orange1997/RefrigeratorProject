package com.example.dc.refrigeratorproject.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.MoreAdapter;
import com.example.dc.refrigeratorproject.adapter.item.ArticleOrRecipesItem;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.iView.INoticeDetailView;
import com.example.dc.refrigeratorproject.presenter.NoticeDetailPresenter;
import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_ARTICLE;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_RECIPES;
import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_URL;

/**
 * Created by DC on 2019/5/14.
 */

public class MyCollectionActivity extends BaseActivity implements INoticeDetailView {
    private MoreAdapter adapter;
    private List<BaseItem> baseItems = new ArrayList<> ();
    private NoticeDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_my_collection);
        presenter = new NoticeDetailPresenter (MyCollectionActivity.this, this);
        presenter.getCollectedNotice ();
        initView ();
    }

    private void initView() {
        Toolbar toolbar = findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
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
                Intent intent = new Intent (MyCollectionActivity.this, DetailActivity.class);
                intent.putExtra (KEY_FOUND_URL,articleOrRecipesItem.url);
                startActivity (intent);
            }
        });
        adapter.setOnLongItemClickListener (new MoreAdapter.OnLongItemClickListener () {
            @Override
            public void onLongItemClick(BaseItem item, int position) {
                showListDialog(position);
            }
        });
    }

    @Override
    public void onCollectSuccess(String s) {

    }

    @Override
    public void getCollects(List<NoticeRes> noticeResList) {
        baseItems.clear ();
        List<NoticeRes> recipes = new ArrayList<> ();
        List<NoticeRes> article = new ArrayList<> ();
        for (NoticeRes noticeRes:noticeResList){
            if (noticeRes.getNoticeType ()==2){
                recipes.add (noticeRes);
            }else if (noticeRes.getNoticeType ()==1){
                article.add (noticeRes);
            }
        }

        if (recipes.size ()>0){
            for (NoticeRes noticeRes:recipes){
                baseItems.add (new ArticleOrRecipesItem (noticeRes,TYPE_RECIPES));
            }
        }

        if (article.size ()>0){
            for (NoticeRes noticeRes:article){
                baseItems.add (new ArticleOrRecipesItem (noticeRes,TYPE_ARTICLE));
            }
        }
        adapter.updateList (baseItems);
    }

    @Override
    public void onError(String s) {

    }

    private void showListDialog(final int position) {
        final String[] items = {"取消收藏"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder (MyCollectionActivity.this);
        listDialog.setItems (items, new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                baseItems.remove (position);
                adapter.notifyItemRemoved (position);
                adapter.notifyItemRangeChanged (position,6);
            }
        });
        listDialog.show ();
    }

}
