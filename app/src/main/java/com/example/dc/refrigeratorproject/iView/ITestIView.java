package com.example.dc.refrigeratorproject.iView;

import com.example.dc.refrigeratorproject.resposeBean.UserModel;

import java.util.List;

/**
 * Created by DC on 2019/5/13.
 */

public interface ITestIView extends IView {
    void onSuccess(List<UserModel> mBook);
}