package com.example.dc.refrigeratorproject.adapter.item;

import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;

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
    public NoticeRes noticeRes;

    public ArticleOrRecipesItem(NoticeRes noticeRes, int type){
        super(type);
        if (noticeRes !=null){
            this.img = noticeRes.getNoticeImgUr ();
            this.title = noticeRes.getNoticeTitle ();
            this.des = noticeRes.getDes ();
            this.url = noticeRes.getNoticeUrl ();
            this.author = noticeRes.getAuthor ();
            this.time = noticeRes.getCreateTime ();
            this.likes = 11;
            this.collections = 11;
            this.noticeRes = noticeRes;
        }
    }
}
