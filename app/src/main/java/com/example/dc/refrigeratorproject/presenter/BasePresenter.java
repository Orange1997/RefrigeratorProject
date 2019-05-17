package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.netWork.DataManager;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by DC on 2019/5/13.
 */

public class BasePresenter {
    protected DataManager manager;
    protected CompositeSubscription mCompositeSubscription;
    private Context mContext;

    public BasePresenter(Context context){
        this.mContext = context;
        manager = new DataManager (mContext);
        mCompositeSubscription = new CompositeSubscription ();
    }

    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

}
