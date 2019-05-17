package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.ShopItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_SHOP;

/**
 * Created by DC on 2019/5/12.
 */

public class SearchActivity extends BaseActivity {
    public static final int RESULT_CODE_SEARCH = 2003;
    public static final String KEY_SEARCH_INPUT= "key_search_input";

    private RecyclerView  rvShop;
    private FoundAdapter shopAdapter;
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
        setContentView (R.layout.activity_search);
        poiSearch.setOnGetPoiSearchResultListener (listener);
        initView ();
    }

    private void initView() {
        findViewById (R.id.iv_back).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        final EditText etInput = findViewById (R.id.searchView);
        findViewById (R.id.tv_search).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty (etInput.getText ().toString ())){
                    showNearBy (etInput.getText ().toString ().trim ());
                }
            }
        });

        rvShop = findViewById (R.id.rv_found);
        GridLayoutManager gridLayoutManager = new GridLayoutManager (SearchActivity.this, 1);
        rvShop.setLayoutManager (gridLayoutManager);
        shopAdapter = new FoundAdapter (SearchActivity.this);
        rvShop.setAdapter (shopAdapter);
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
}
