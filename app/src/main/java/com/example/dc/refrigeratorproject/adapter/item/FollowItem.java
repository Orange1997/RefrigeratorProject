package com.example.dc.refrigeratorproject.adapter.item;

import com.example.dc.refrigeratorproject.model.FollowModel;

/**
 * Created by DC on 2019/5/14.
 */

public class FollowItem extends BaseItem {
    public String name;
    public String intro;
    public boolean isFollow;
    public String img;
    public FollowModel model;

    public FollowItem(FollowModel model,int type){
        super(type);
        if (model!=null){
            this.model = model;
            this.name = model.getName ();
            this.intro = model.getIntro ();
            this.isFollow = model.isFollow ();
            this.img = model.getImg ();
        }
    }
}
