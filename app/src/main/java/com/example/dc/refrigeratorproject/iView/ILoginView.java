package com.example.dc.refrigeratorproject.iView;

import com.example.dc.refrigeratorproject.resposeBean.User;

/**
 * Created by DC on 2019/5/13.
 */

public interface ILoginView extends IView {
    void onSuccess(User user);
    void onLoginByQqSuccess(User s);
    void onLoginByQqFailure(String info,String openId);
}