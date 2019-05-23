package com.example.dc.refrigeratorproject;

import android.app.Application;
import android.content.res.Configuration;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.dc.refrigeratorproject.config.Config;
import com.facebook.drawee.backends.pipeline.Fresco;

import static com.example.dc.refrigeratorproject.config.Config.KEY_LOCATION_LATITUDE;
import static com.example.dc.refrigeratorproject.config.Config.KEY_LOCATION_LONGITUDE;

/**
 * Created by DC on 2019/3/15.
 */

public class MyApplication extends Application {
    private LocationClient locationClient;
    private BDLocationListener mBDLocationListener;

    @Override
    public void onCreate() {
        super.onCreate ();
        Fresco.initialize (this);
        SDKInitializer.initialize (this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        mBDLocationListener = new MyBDLocationListener ();
        initLocationOption ();
    }

    private void initLocationOption() {
        locationClient = new LocationClient (getApplicationContext ());
        LocationClientOption locationOption = new LocationClientOption ();
        locationClient.registerLocationListener (mBDLocationListener);
        locationOption.setLocationMode (LocationClientOption.LocationMode.Hight_Accuracy);

        locationOption.setCoorType ("bd09ll");

        locationOption.setScanSpan (1000);

        locationOption.setOpenGps (true);
        locationOption.setLocationNotify (true);
        locationOption.setIgnoreKillProcess (false);
        locationOption.SetIgnoreCacheException (false);
        locationOption.setWifiCacheTimeOut (5 * 60 * 1000);
        locationOption.setEnableSimulateGps (false);
        locationOption.setIsNeedAddress(true);
        locationClient.setLocOption (locationOption);
        locationClient.start ();
    }

    private class MyBDLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location != null) {
                double latitude = location.getLatitude ();
                double longitude = location.getLongitude ();
                Config.putDouble (getBaseContext (), KEY_LOCATION_LATITUDE, latitude);
                Config.putDouble (getBaseContext (), KEY_LOCATION_LONGITUDE, longitude);
            }
        }
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate ();
        if (locationClient != null) {
            locationClient.unRegisterLocationListener (mBDLocationListener);
        }
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        super.onLowMemory ();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        super.onTrimMemory (level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged (newConfig);
    }
}
