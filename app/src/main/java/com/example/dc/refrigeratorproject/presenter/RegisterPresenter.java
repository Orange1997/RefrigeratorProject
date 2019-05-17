package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.IRegisterView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.resposeBean.RegisterRes;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/16.
 */

public class RegisterPresenter extends BasePresenter {
    private IRegisterView iRegisterView;

    public RegisterPresenter(Context mContext, IView iView) {
        super (mContext);
        iRegisterView = (IRegisterView) iView;
    }

    public void register(String account, String password) {
        mCompositeSubscription.add (manager.register (account, password)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<RegisterRes> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();
                        iRegisterView.onError ("请求失败！！");
                    }

                    @Override
                    public void onNext(RegisterRes registerRes) {
                        if (registerRes != null) {
                            if (registerRes.getStatus () == 0) {
                                iRegisterView.onError ("账号已存在！");
                            } else if (registerRes.getStatus () == 1 && registerRes.getData () != null) {
                                iRegisterView.onRegisterSuccess (registerRes.getData ().get (0));
                            }
                        }
                    }
                })
        );
    }

    @Override
    public void onStop() {
        super.onStop ();
    }
}
