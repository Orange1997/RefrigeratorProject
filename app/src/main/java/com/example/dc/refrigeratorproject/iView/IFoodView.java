package com.example.dc.refrigeratorproject.iView;

import com.example.dc.refrigeratorproject.resposeBean.FoodDetailRes;

/**
 * Created by DC on 2019/5/23.
 */

public interface IFoodView extends IView{
    void getFoodDetail(FoodDetailRes foodDetailRes);
    void deleteSuccess(String s);
}
