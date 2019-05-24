package com.example.dc.refrigeratorproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.activity.DetailActivity;
import com.example.dc.refrigeratorproject.activity.MoreActivity;
import com.example.dc.refrigeratorproject.activity.NearByActivity;
import com.example.dc.refrigeratorproject.activity.ShopDetailActivity;
import com.example.dc.refrigeratorproject.adapter.FoundAdapter;
import com.example.dc.refrigeratorproject.adapter.item.ArticleOrRecipesItem;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.ShopItem;
import com.example.dc.refrigeratorproject.adapter.item.TitleItem;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.iView.INoticeView;
import com.example.dc.refrigeratorproject.presenter.NoticePresenter;
import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_ARTICLE;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_HEAD;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_RECIPES;
import static com.example.dc.refrigeratorproject.adapter.FoundAdapter.TYPE_SHOP;
import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_TYPE;
import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_URL;

/**
 * Created by DC on 2019/5/5.
 */

public class FoundFragment extends Fragment {
    public static final int TITLE_TYPE_ARTICLE = 1;
    public static final int TITLE_TYPE_RECIPES = 2;
    public static final int TITLE_TYPE_SHOP = 3;

    protected PoiSearch poiSearch;
    protected double lat;
    protected double lon;

    private RecyclerView rvFound;
    private FoundAdapter adapter;
    private List<BaseItem> baseItems = new ArrayList<> ();
    private List<PoiDetailInfo> poiDetailInfoList = new ArrayList<> ();
    private NoticePresenter presenter;

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
                    poiDetailInfoList = list;
                    updatePosList ();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_found, container, false);
        poiSearch = PoiSearch.newInstance ();
        poiSearch.setOnGetPoiSearchResultListener (listener);
        lat = Config.getDouble (getActivity (), Config.KEY_LOCATION_LATITUDE);
        lon = Config.getDouble (getActivity (), Config.KEY_LOCATION_LONGITUDE);
        presenter = new NoticePresenter (getContext (), iNoticeView);
        initView (view);
        presenter.getNotice ();
        return view;
    }

    private void initView(View view) {
        rvFound = view.findViewById (R.id.rv_found);
        GridLayoutManager gridLayoutManager = new GridLayoutManager (getContext (), 2);
        gridLayoutManager.setSpanSizeLookup (new GridLayoutManager.SpanSizeLookup () {
            @Override
            public int getSpanSize(int postition) {
                int type = adapter.getItemViewType (postition);
                if (type == TYPE_HEAD || type == TYPE_ARTICLE || type == TYPE_SHOP) {
                    return 2;
                } else if (type == TYPE_RECIPES) {
                    return 1;
                }
                return 1;
            }
        });
        rvFound.setLayoutManager (gridLayoutManager);
        adapter = new FoundAdapter (getActivity ());
        rvFound.setAdapter (adapter);
        adapter.setOnMoreClickListener (new FoundAdapter.OnMoreClickListener () {
            @Override
            public void onItemClick(int type) {
                if (type == TITLE_TYPE_SHOP) {
                    Intent intent = new Intent (getActivity (), NearByActivity.class);
                    startActivity (intent);
                } else {
                    Intent intent = new Intent (getActivity (), MoreActivity.class);
                    intent.putExtra (KEY_FOUND_TYPE, type);
                    startActivity (intent);
                }
            }
        });
        adapter.setOnFoundItemClickListener (new FoundAdapter.OnFoundItemClickListener () {
            @Override
            public void onItemClick(BaseItem item) {
                if (item instanceof ArticleOrRecipesItem) {
                    ArticleOrRecipesItem articleOrRecipesItem = (ArticleOrRecipesItem) item;
                    Intent intent = new Intent (getActivity (), DetailActivity.class);
                    intent.putExtra (KEY_FOUND_URL, articleOrRecipesItem.url);
                    intent.putExtra ("notice",articleOrRecipesItem.noticeRes);
                    startActivity (intent);
                } else if (item instanceof ShopItem) {
                    ShopItem shopItem = (ShopItem) item;
                    Intent intent = new Intent (getActivity (), ShopDetailActivity.class);
                    intent.putExtra ("latitude", shopItem.latitude);
                    intent.putExtra ("longitude", shopItem.longitude);
                    intent.putExtra ("distance", shopItem.distance);
                    intent.putExtra ("address", shopItem.location);
                    intent.putExtra ("shopName", shopItem.name);
                    startActivity (intent);
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

    private INoticeView iNoticeView = new INoticeView () {
        @Override
        public void getNoticeSuccess(List<NoticeRes> s) {
            updateList (s);
        }
    };

    private void updateList(List<NoticeRes> s) {
        baseItems.clear ();
        List<NoticeRes> article = new ArrayList<> ();
        List<NoticeRes> recipes = new ArrayList<> ();
        for (NoticeRes noticeRes : s) {
            if (noticeRes.getNoticeType () == 1) {
                article.add (noticeRes);
            } else if (noticeRes.getNoticeType () == 2) {
                recipes.add (noticeRes);
            }
        }
        baseItems.add (new TitleItem (TYPE_HEAD, "今日推荐", "更多", 1));
        if (article.size () > 0) {
            baseItems.add (new ArticleOrRecipesItem (article.get (0), TYPE_ARTICLE));
        }

        baseItems.add (new TitleItem (TYPE_HEAD, "热门食谱", "更多", 2));
        if (recipes.size ()>1){
            baseItems.add (new ArticleOrRecipesItem (recipes.get (0), TYPE_RECIPES));
            baseItems.add (new ArticleOrRecipesItem (recipes.get (1), TYPE_RECIPES));
        }
        baseItems.add (new TitleItem (TYPE_HEAD, "附近店铺", "更多", 3));
        adapter.updateList (baseItems);
    }

    private void updatePosList(){
        List<BaseItem> baseItemsOld = new ArrayList<> ();
        for (BaseItem baseItem:baseItems){
            if (baseItem.type==TYPE_SHOP){
                baseItemsOld.add (baseItem);
            }
        }
        baseItems.removeAll (baseItemsOld);
        for (PoiDetailInfo poiDetailInfo : poiDetailInfoList) {
            baseItems.add (new ShopItem (poiDetailInfo, TYPE_SHOP, lat, lon));
        }

        adapter.updateList (baseItems);
    }


    @Override
    public void onResume() {
        super.onResume ();
        showNearBy ("超市");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView ();
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
    }

    public FoundFragment() {
    }
}
