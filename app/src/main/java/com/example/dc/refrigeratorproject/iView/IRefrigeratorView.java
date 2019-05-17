package com.example.dc.refrigeratorproject.iView;

import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorListRes;

import java.util.List;

/**
 * Created by DC on 2019/5/16.
 */

public interface IRefrigeratorView extends IView{
    void updateFridgeList(List<RefrigeratorListRes> refrigeratorListRes);
//    void updateFoodList(List<RefrigeratorListRes> refrigeratorListRes);
}
