package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.IAddFridgeView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.resposeBean.AddFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.UpdateUserRes;
import com.example.dc.refrigeratorproject.resposeBean.User;

import java.util.List;

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


    public void updateFridge(String createByFridgeIds, int userId, final int fridgeId) {
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
                                iAddFridgeView.updateFridgeSuccess (fridgeId);
                            }
                        }
                    }
                })
        );
    }

    public void getUserShareFridge(int fridgeId, final int creatorId) {
        mCompositeSubscription.add (manager.getUserShareFridge (fridgeId)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<List<User>> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();
                        iAddFridgeView.onError ("请求失败！！");
                    }

                    @Override
                    public void onNext(List<User> userList) {
                        if (userList != null) {
                            iAddFridgeView.onUpdateShareUser (userList,creatorId);
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
