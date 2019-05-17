package com.example.dc.refrigeratorproject.adapter.item;

import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.example.dc.refrigeratorproject.util.MapHelper;

import java.math.BigDecimal;

/**
 * Created by DC on 2019/5/12.
 */

public class ShopItem extends BaseItem {
    public String name;
    public String  likes;
    public double discount;
    public String img;
    public String location;

    public ShopItem(PoiDetailInfo shopModel, int type, double lat, double lon) {
        super (type);
        if (shopModel != null) {
            this.name = shopModel.getName ();
            this.likes = String.valueOf (shopModel.overallRating);
            BigDecimal b = new BigDecimal (MapHelper.getDistance (lat, lon, shopModel.getLocation ().latitude, shopModel.getLocation ().longitude));
            double dis = b.setScale (2, BigDecimal.ROUND_HALF_UP).doubleValue ();
            this.discount = dis;
            this.img = " ";
            this.location = shopModel.getAddress ();
        }
    }
}
