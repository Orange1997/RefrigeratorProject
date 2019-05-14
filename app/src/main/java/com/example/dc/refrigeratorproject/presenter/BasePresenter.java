package com.example.dc.refrigeratorproject.presenter;

import android.content.Intent;

import com.example.dc.refrigeratorproject.iView.IView;

/**
 * Created by DC on 2019/5/13.
 */

public interface BasePresenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(IView view);

    void attachIncomingIntent(Intent intent);//暂时没用到
}
