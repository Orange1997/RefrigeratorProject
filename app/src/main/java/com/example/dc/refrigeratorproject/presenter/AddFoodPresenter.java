package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.IAddFoodView;
import com.example.dc.refrigeratorproject.iView.IView;

/**
 * Created by DC on 2019/5/17.
 */

public class AddFoodPresenter extends BasePresenter {
    private IAddFoodView iAddFoodView;

    public AddFoodPresenter(Context mContext, IView iView){
        super(mContext);
        iAddFoodView = (IAddFoodView)iView;
    }

    public void login(String account,String password){
//        mCompositeSubscription.add(manager.getCurrentFood (account,password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<LoginRes> () {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        iloginView.onError("请求失败！！");
//                    }
//
//                    @Override
//                    public void onNext(LoginRes loginRes) {
//                        if (loginRes != null){
//                            if (loginRes.getStatus ()==0){
//                                iloginView.onError("账号密码不匹配！");
//                            }else if (loginRes.getStatus ()==1&&loginRes.getData ()!=null){
//                                iloginView.onSuccess(loginRes.getData ().get (0));
//                            }
//                        }
//                    }
//                })
//        );
    }

    @Override
    public void onStop() {
        super.onStop ();
    }

}
