package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/23.
 */

public class FoodDetailRes implements Serializable{

    /**
     * imgData : null
     * foodId : 8
     * foodName : 牛肉
     * foodCount : -1
     * foodUnit :
     * outlineTime : 1561219200000
     * remindTime : 1558617070920
     * remark :
     * userId : 1
     * fridgeId : 24
     * foodType : 2
     */

    private Object imgData;
    private int foodId;
    private String foodName;
    private float foodCount;
    private String foodUnit;
    private long outlineTime;
    private long remindTime;
    private String remark;
    private int userId;
    private int fridgeId;
    private int foodType;

    public Object getImgData() {
        return imgData;
    }

    public void setImgData(Object imgData) {
        this.imgData = imgData;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public float getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(float foodCount) {
        this.foodCount = foodCount;
    }

    public String getFoodUnit() {
        return foodUnit;
    }

    public void setFoodUnit(String foodUnit) {
        this.foodUnit = foodUnit;
    }

    public long getOutlineTime() {
        return outlineTime;
    }

    public void setOutlineTime(long outlineTime) {
        this.outlineTime = outlineTime;
    }

    public long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(long remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }
}
