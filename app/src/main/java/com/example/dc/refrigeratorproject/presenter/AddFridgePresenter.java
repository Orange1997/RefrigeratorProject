package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.IAddFridgeView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.resposeBean.AddFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.UpdateUserRes;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/16.
 */

public class AddFridgePresenter extends BasePresenter {
    private IAddFridgeView iAddFridgeView;

    public AddFridgePresenter(Context mContext, IView iView) {
        super (mContext);
        iAddFridgeView = (IAddFridgeView) iView;
    }

    public void addFridge(String fridgeName,String address,int userId,String code) {
        mCompositeSubscription.add (manager.addFridge (fridgeName, address, userId, code)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<AddFridgeRes> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();
                        iAddFridgeView.onError ("请求失败！！");
                    }

                    @Override
                    public void onNext(AddFridgeRes addFridgeRes) {
                        iAddFridgeView.onAddFridgeSuccess (addFridgeRes.getFridgeId ());
                    }
                })
        );
    }


    public void updateFridge(String createByFridgeIds, int userId,int fridgeId) {
        mCompositeSubscription.add (manager.updateFridge (createByFridgeIds,userId,fridgeId)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<UpdateUserRes> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();
                        iAddFridgeView.onError ("请求失败！！");
                    }

                    @Override
                    public void onNext(UpdateUserRes updateUserRes) {
                        if (updateUserRes != null) {
                            if (updateUserRes.getStatus ()==1){
                                iAddFridgeView.onAddFridgeSuccess ();
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
