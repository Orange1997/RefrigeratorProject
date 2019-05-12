package com.example.dc.refrigeratorproject.adapter.item;

/**
 * Created by DC on 2019/5/11.
 */

public class TitleItem extends BaseItem {
    public String title;
    public String more;
    public int titleType;

    public TitleItem(int type, String title, String more,int titleType) {
        super (type);
        this.title = title;
        this.more = more;
        this.titleType = titleType;
    }
}
