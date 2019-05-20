package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.IJoinView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.resposeBean.AddShareFridgeRes;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/16.
 */

public class SharePresenter extends BasePresenter{
    private IJoinView iJoinView;

    public SharePresenter(Context mContext, IView iView){
        super(mContext);
        iJoinView = (IJoinView)iView;
    }

    public void addSharedFridge(String code, final int userID){
        mCompositeSubscription.add(manager.addSharedFridge(code,userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddShareFridgeRes> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        iJoinView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(AddShareFridgeRes res) {
                        if (res.getData ().equals ("已经存在")){
                            iJoinView.onError (res.getData ());
                        }else if (res.getData ().equals ("冰箱不存在")){
                            iJoinView.onError (res.getData ());
                        }else {
                            iJoinView.onJoinSuccess (res.getData ());
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
