package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/17.
 */

public class GetFoodListRes implements Serializable {

    private String imgData;
    private int foodId;
    private String foodName;
    private int foodType;
    private float foodCount;
    private String foodUnit;
    private String outlineTime;
    private String remindTime;
    private Object createTime;
    private Object remindCount;
    private String remark;
    private int userId;
    private int fridgeId;

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
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

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
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

    public String getOutlineTime() {
        return outlineTime;
    }

    public void setOutlineTime(String outlineTime) {
        this.outlineTime = outlineTime;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getRemindCount() {
        return remindCount;
    }

    public void setRemindCount(Object remindCount) {
        this.remindCount = remindCount;
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
}
