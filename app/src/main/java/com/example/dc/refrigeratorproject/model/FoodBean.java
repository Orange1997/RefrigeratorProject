package com.example.dc.refrigeratorproject.model;

/**
 * Created by DC on 2019/3/8.
 */

public class FoodBean {
    public int getImgId() {
        return imgId;
    }

    public FoodBean(int imgId){
        this.imgId = imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    private int imgId;
}
