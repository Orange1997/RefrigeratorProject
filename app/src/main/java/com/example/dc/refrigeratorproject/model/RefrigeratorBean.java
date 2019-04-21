package com.example.dc.refrigeratorproject.model;

import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */

public class RefrigeratorBean {
    private String kind;
    private List<FoodBean> foodBeanList;

    public RefrigeratorBean(String kind,List<FoodBean> foodBeanList){
        this.kind = kind;
        this.foodBeanList = foodBeanList;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<FoodBean> getFoodBeanList() {
        return foodBeanList;
    }

    public void setFoodBeanList(List<FoodBean> foodBeanList) {
        this.foodBeanList = foodBeanList;
    }
}
