package com.example.dc.refrigeratorproject.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.sqLite.DBOpenHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by DC on 2019/5/2.
 */

public class BaseActivity extends AppCompatActivity {
    protected DBOpenHelper dbOpenHelper;
    protected PoiSearch poiSearch;
    protected double lat;
    protected double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        //透明状态栏
        getWindow ().addFlags (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        dbOpenHelper = new DBOpenHelper (this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission (this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断
                if (ActivityCompat.shouldShowRequestPermissionRationale (this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Toast.makeText (this, "KARL-Dujinyang 是否需要打开位置权限", Toast.LENGTH_SHORT).show ();
                }
                //请求权限
                ActivityCompat.requestPermissions (this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);
            }
        }

        poiSearch = PoiSearch.newInstance ();
        lat = Config.getDouble (BaseActivity.this, Config.KEY_LOCATION_LATITUDE);
        lon = Config.getDouble (BaseActivity.this, Config.KEY_LOCATION_LONGITUDE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        poiSearch.destroy ();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
