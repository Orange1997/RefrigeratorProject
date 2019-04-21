package com.example.dc.refrigeratorproject;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by DC on 2019/3/15.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}
