package com.example.dc.refrigeratorproject.adapter.item;

import com.example.dc.refrigeratorproject.resposeBean.GetFoodListRes;

/**
 * Created by DC on 2019/5/23.
 */

public class FoodItem extends BaseItem {
    public GetFoodListRes food;

    public FoodItem(int type, GetFoodListRes food) {
        super (type);
        if (food != null) {
            this.food = food;
        }
    }
}
