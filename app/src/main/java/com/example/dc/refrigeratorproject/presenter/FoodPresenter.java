package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.IFoodView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.resposeBean.FoodDetailRes;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/23.
 */

public class FoodPresenter extends BasePresenter {
    private IFoodView iFoodView;

    public FoodPresenter(Context mContext, IView iView){
        super(mContext);
        iFoodView = (IFoodView)iView;
    }

    public void getFoodDetail(int fooId){
        mCompositeSubscription.add(manager.getFoodDetail (fooId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodDetailRes> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(FoodDetailRes res) {
                        if (res!=null){
                            iFoodView.getFoodDetail (res);
                        }
                    }
                })
        );
    }

    public void deleteFood(int fooId){
        mCompositeSubscription.add(manager.deleteFood (fooId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(String res) {
                        if (res!=null){
                            iFoodView.deleteSuccess (res);
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
