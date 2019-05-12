package com.example.dc.refrigeratorproject.adapter.item;

import com.example.dc.refrigeratorproject.model.ArticleOrRecipesModel;

/**
 * Created by DC on 2019/5/11.
 */

public class ArticleOrRecipesItem extends BaseItem {
    public String img;
    public String title;
    public String des;
    public String url;
    public String author;
    public String time;
    public int likes;
    public int collections;

    public ArticleOrRecipesItem(ArticleOrRecipesModel articleOrRecipesModel, int type){
        super(type);
        if (articleOrRecipesModel !=null){
            this.img = articleOrRecipesModel.getImg ();
            this.title = articleOrRecipesModel.getTitle ();
            this.des = articleOrRecipesModel.getDes ();
            this.url = articleOrRecipesModel.getUrl ();
            this.author = articleOrRecipesModel.getAuthor ();
            this.time = articleOrRecipesModel.getTime ();
            this.likes = articleOrRecipesModel.getLikes ();
            this.collections = articleOrRecipesModel.getCollects ();
        }
    }
}
