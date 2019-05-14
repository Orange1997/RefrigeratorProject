package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.dc.refrigeratorproject.iView.ITestIView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.netWork.DataManager;
import com.example.dc.refrigeratorproject.resposeBean.UserModel;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by DC on 2019/5/13.
 */

public class TestPresenter implements BasePresenter{
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private ITestIView mBookView;
    private List<UserModel> userModel;

    public TestPresenter (Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(IView view) {
        mBookView = (ITestIView)view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }
    public void login(String acocunt,String passwordd){
        mCompositeSubscription.add(manager.login (acocunt,passwordd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserModel>> () {
                    @Override
                    public void onCompleted() {
                        if (userModel != null){
                            mBookView.onSuccess(userModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(List<UserModel> book) {
                        userModel = book;
                    }
                })
        );
    }
}
