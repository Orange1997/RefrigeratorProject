package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.IAddFoodView;
import com.example.dc.refrigeratorproject.iView.IView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/17.
 */

public class AddFoodPresenter extends BasePresenter {
    private IAddFoodView iAddFoodView;

    public AddFoodPresenter(Context mContext, IView iView) {
        super (mContext);
        iAddFoodView = (IAddFoodView) iView;
    }

    public void addFood(String name, float amount, String unit, long outTime, long remindTime, String remind, int type) {
        mCompositeSubscription.add (manager.addFoodPost (name, amount, unit, outTime, remindTime, remind, type)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<String> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();

                    }

                    @Override
                    public void onNext(String s) {
                        iAddFoodView.onAddFoodSuccess (s);
                    }
                })
        );
    }

    public void updateFoodPost(String name, float amount, String unit, long outTime, long remindTime, String remind, int foodId, int type) {
        mCompositeSubscription.add (manager.updateFoodPost (name, amount, unit, outTime, remindTime, remind, foodId, type)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<String> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();

                    }

                    @Override
                    public void onNext(String s) {
                        iAddFoodView.onUpdateFoodSuccess (s);
                    }
                })
        );
    }

    @Override
    public void onStop() {
        super.onStop ();
    }

}
