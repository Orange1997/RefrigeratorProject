package com.example.dc.refrigeratorproject.adapter.item;

import com.example.dc.refrigeratorproject.model.ShopModel;

/**
 * Created by DC on 2019/5/12.
 */

public class ShopItem extends BaseItem {
    public String name;
    public int likes;
    public float discount;
    public String img;
    public String location;

    public ShopItem(ShopModel shopModel,int type){
        super(type);
        if (shopModel!=null){
            this.name = shopModel.getName ();
            this.likes = shopModel.getLikes ();
            this.discount = shopModel.getDiscount ();
            this.img = shopModel.getImg ();
            this.location = shopModel.getLocation ();
        }
    }
}
