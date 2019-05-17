package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.FoundAdapter;
import com.example.dc.refrigeratorproject.adapter.TabAdapter;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.ShopItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.activity.RefrigeratorInfoActivity.REQUEST_CODE;
import static com.example.dc.refrigeratorproject.activity.SearchActivity.KEY_SEARCH_INPUT;
import static com.example.dc.refrigeratorproject.activity.SearchActivity.RESULT_CODE_SEARCH;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_SHOP;

/**
 * Created by DC on 2019/5/15.
 */

public class NearByActivity extends BaseActivity {

    private RecyclerView rvTab, rvShop;
    private TabAdapter tabAdapter;
    private FoundAdapter shopAdapter;
    private LinearLayout llTab;

    private List<String> tabs = new ArrayList<> ();
    private List<BaseItem> baseItems = new ArrayList<> ();

    private OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener () {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            String uid = null;
            StringBuilder stringBuilder = new StringBuilder ();
            if (poiResult != null) {
                for (int i = 0; i < poiResult.getAllPoi ().size (); i++) {
                    if (i == 0) {
                        stringBuilder = stringBuilder.append (poiResult.getAllPoi ().get (0).getUid ());
                    } else {
                        stringBuilder = stringBuilder.append (",").append (poiResult.getAllPoi ().get (i).getUid ());
                    }
                }
                poiSearch.searchPoiDetail ((new PoiDetailSearchOption ())
                        .poiUids (stringBuilder.toString ()));

            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            if (poiDetailSearchResult != null) {
                List<PoiDetailInfo> list = poiDetailSearchResult.getPoiDetailInfoList ();
                if (list != null) {
                    updateList (list);
                }
            }

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_nearby);
        poiSearch.setOnGetPoiSearchResultListener (listener);
        initView ();
        showNearBy ("美食");
    }

    private void initView() {
        initTab ();
        rvShop = findViewById (R.id.rv_found);
        GridLayoutManager gridLayoutManager = new GridLayoutManager (NearByActivity.this, 1);
        rvShop.setLayoutManager (gridLayoutManager);
        shopAdapter = new FoundAdapter (NearByActivity.this);
        rvShop.setAdapter (shopAdapter);

        ImageView ivBack = findViewById (R.id.iv_back);
        LinearLayout llSearch = findViewById (R.id.ll_search);

        ivBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });

        llSearch.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (NearByActivity.this, SearchActivity.class);
                startActivityForResult (intent, REQUEST_CODE);
            }
        });
    }

    private void initTab() {
        llTab = findViewById (R.id.ll_tab);
        rvTab = findViewById (R.id.rv_tab);
        tabs.add ("全部");
        tabs.add ("菜市场");
        tabs.add ("水果店");
        tabs.add ("甜品店");
        tabs.add ("超市");

        tabAdapter = new TabAdapter (tabs, NearByActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager (NearByActivity.this);
        layoutManager.setOrientation (LinearLayoutManager.HORIZONTAL);

        tabAdapter.setDefaultCheckedItemPosition (0);
        rvTab.setLayoutManager (layoutManager);
        rvTab.setAdapter (tabAdapter);
        tabAdapter.setOnItemClickListener (new TabAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(View view, int position, long id) {
                tabAdapter.check (position);
                if (position != 0) {
                    showNearBy (tabs.get (position));
                } else {
                    showNearBy ("美食");
                }
                if (position == 3) {
                    rvTab.smoothScrollToPosition (tabs.size ());
                } else if (position == 1) {
                    rvTab.smoothScrollToPosition (0);
                }
            }
        });


    }

    public void showNearBy(String keyword) {
        poiSearch.searchNearby (new PoiNearbySearchOption ()
                .location (new LatLng (lat, lon))
                .radius (10000)
                .keyword (keyword)
                .pageNum (0)
                .pageCapacity (10));
    }


    private void updateList(List<PoiDetailInfo> detailInfo) {
        baseItems.clear ();
        for (PoiDetailInfo poiDetailInfo : detailInfo) {
            baseItems.add (new ShopItem (poiDetailInfo, TYPE_SHOP, lat, lon));
        }
        shopAdapter.updateList (baseItems);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent != null) {
            if (resultCode == RESULT_CODE_SEARCH) {
                String result = intent.getStringExtra (KEY_SEARCH_INPUT);
                showNearBy (result);
                llTab.setVisibility (View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        poiSearch.destroy ();
    }


}

