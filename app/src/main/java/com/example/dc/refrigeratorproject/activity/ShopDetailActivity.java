package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.util.DialogUtil;

/**
 * Created by DC on 2019/5/23.
 */

public class ShopDetailActivity extends BaseActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient;
    public BDLocationListener myListener = new MyLocationListener ();
    private boolean isFirstLoc = true;

    private double longitude;
    private double latitude;
    private String shopName;
    private String address;
    private double distance;
    private TextView tvShopName;
    private TextView tvAddress;
    private TextView tvLike;
    private LatLng startLatLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_shop_details);
        longitude = getIntent ().getDoubleExtra ("longitude",0);
        latitude = getIntent ().getDoubleExtra ("latitude",0);
        shopName = getIntent ().getStringExtra ("shopName");
        address = getIntent ().getStringExtra ("address");
        distance = getIntent ().getDoubleExtra ("distance",0);
        mMapView = (MapView) findViewById (R.id.bmapView);
        mBaiduMap = mMapView.getMap ();
        initView();
        initMap ();
    }

    private void initView() {
        Toolbar toolbar = findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View pView) {
                finish ();
            }
        });
        tvShopName = findViewById (R.id.tv_name);
        tvAddress = findViewById (R.id.tv_address);
        tvLike = findViewById (R.id.tv_fraction);
        tvShopName.setText (TextUtils.isEmpty (shopName)?"暂无信息":shopName);
        if (!TextUtils.isEmpty (address)){
            tvAddress.setText (address);
        }
        tvLike.setText ("距离你"+distance+"km");

        findViewById (R.id.iv_guide).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                DialogUtil.showNormalDialog (ShopDetailActivity.this, "确定要离开该界面，打开百度地图进入导航吗？", new DialogUtil.OnPositiveClickListener () {
                    @Override
                    public void onPositiveClick() {
                        //起终点位置
                        LatLng endPoint = new LatLng(latitude, longitude);

                        NaviParaOption para = new NaviParaOption()
                                .startPoint(startLatLng)
                                .endPoint(endPoint);
                        //调起百度地图
                        try {
                            BaiduMapNavigation.openBaiduMapWalkNavi(para, ShopDetailActivity.this);
                        } catch (BaiduMapAppNotSupportNaviException e) {
                            e.printStackTrace();
                            //调起失败的处理
                        }

                        //调起结束时及时调用finish方法以释放相关资源
                        BaiduMapNavigation.finish(ShopDetailActivity.this);
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });
            }
        });

    }

    private void initMap() {
        //获取地图控件引用
        mBaiduMap = mMapView.getMap ();
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_place);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration (MyLocationConfiguration.LocationMode.FOLLOWING,true,mCurrentMarker));
        UiSettings uiSettings = mBaiduMap.getUiSettings ();
        uiSettings.setRotateGesturesEnabled (false);
        uiSettings.setOverlookingGesturesEnabled (false);
        mMapView.removeViewAt (1);
        mMapView.showScaleControl (false);
        mBaiduMap.setMapType (BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled (true);
        mLocationClient = new LocationClient (getApplicationContext ());     //声明LocationClient类
        //配置定位SDK参数
        initLocation ();
    }

    //配置定位SDK参数
    private void initLocation() {
        LocationClientOption option = new LocationClientOption ();
        option.setLocationMode (LocationClientOption.LocationMode.Battery_Saving);
        option.setCoorType ("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setNeedDeviceDirect(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption (option);
        mLocationClient.registerLocationListener (myListener);    //注册监听函数
        //开启定位
        mLocationClient.start ();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null){
                return;
            }
            startLatLng = new LatLng (location.getLatitude (),location.getLongitude ());
            MyLocationData locData = new MyLocationData.Builder ()
                    .accuracy (location.getRadius ())
                    .direction (location.getDirection ())
                    .latitude (latitude)
                    .longitude (longitude)
                    .build ();
            mBaiduMap.setMyLocationData (locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(latitude, longitude);
                MapStatus.Builder builder = new MapStatus.Builder();
                //设置缩放中心点；缩放比例；
                builder.target(ll).zoom(18.5f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume ();
        mMapView.onResume ();
    }

    @Override
    public void onPause() {
        super.onPause ();
        mMapView.onPause ();
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        mMapView.onDestroy ();
    }
}
