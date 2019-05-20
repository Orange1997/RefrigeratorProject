package com.example.dc.refrigeratorproject.iView;

import com.example.dc.refrigeratorproject.resposeBean.User;

import java.util.List;

/**
 * Created by DC on 2019/5/16.
 */

public interface IAddFridgeView extends IView{
    void onAddFridgeSuccess(int id);
    void updateFridgeSuccess(int fridgeId);
    void onUpdateShareUser(List<User> userList,int creatorId);
}
