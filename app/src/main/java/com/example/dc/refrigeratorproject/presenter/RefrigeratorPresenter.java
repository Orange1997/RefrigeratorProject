package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.IRefrigeratorView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.resposeBean.GetFoodListRes;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorListRes;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/16.
 */

public class RefrigeratorPresenter extends BasePresenter{
    private IRefrigeratorView iRefrigeratorView;

    public RefrigeratorPresenter(Context mContext, IView iView) {
        super (mContext);
        iRefrigeratorView = (IRefrigeratorView) iView;
    }

    public void getFridgeList(String ids) {
        mCompositeSubscription.add (manager.getFridgeList (ids)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<List<RefrigeratorListRes>> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();
                        iRefrigeratorView.onError ("请求失败！！");
                    }

                    @Override
                    public void onNext(List<RefrigeratorListRes> listRes) {
                        iRefrigeratorView.updateFridgeList (listRes);
                    }
                })
        );
    }

    public void getFoodList(int id) {
        mCompositeSubscription.add (manager.getCurrentFood (id)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<List<GetFoodListRes>> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();
                        iRefrigeratorView.onError ("请求失败！！");
                    }

                    @Override
                    public void onNext(List<GetFoodListRes> listRes) {
                        iRefrigeratorView.updateFoodList (listRes);
                    }
                })
        );
    }



    @Override
    public void onStop() {
        super.onStop ();
    }

}
