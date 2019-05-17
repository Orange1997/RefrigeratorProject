package com.example.dc.refrigeratorproject.model;

import com.baidu.mapapi.search.core.PoiDetailInfo;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/12.
 */

public class ShopModel implements Serializable {
    private int likes;
    private String img;
    private PoiDetailInfo poiDetailInfo;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public PoiDetailInfo getPoiDetailInfo() {
        return poiDetailInfo;
    }

    public void setPoiDetailInfo(PoiDetailInfo poiDetailInfo) {
        this.poiDetailInfo = poiDetailInfo;
    }
}
