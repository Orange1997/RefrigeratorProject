package com.example.dc.refrigeratorproject.adapter.item;

import com.example.dc.refrigeratorproject.model.ListModel;

/**
 * Created by DC on 2019/5/11.
 */

public class ListItem extends BaseItem {
    public Long time;
    public String title;

    public ListItem(ListModel listModel,int type){
        super(type);
        this.time = listModel.getTime ();
        this.title = listModel.getTitle ();
    }
}
