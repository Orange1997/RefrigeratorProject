package com.example.dc.refrigeratorproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.activity.AddFoodActivity;
import com.example.dc.refrigeratorproject.activity.FoodDetailActivity;
import com.example.dc.refrigeratorproject.activity.JoinActivity;
import com.example.dc.refrigeratorproject.adapter.RefrigeratorAdapter;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.FoodItem;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.iView.IRefrigeratorView;
import com.example.dc.refrigeratorproject.presenter.RefrigeratorPresenter;
import com.example.dc.refrigeratorproject.resposeBean.GetFoodListRes;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorListRes;
import com.example.dc.refrigeratorproject.resposeBean.User;
import com.example.dc.refrigeratorproject.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.RefrigeratorAdapter.TYPE_FOOD;

/**
 * Created by DC on 2019/3/8.
 */

public class RefrigeratorFragment extends Fragment implements IRefrigeratorView, View.OnClickListener {
    private RecyclerView rvRefrigerator, rvRefrigerator2, rvRefrigerator3, rvRefrigerator4;
    private RefrigeratorAdapter refrigeratorAdapter, refrigeratorAdapter2, refrigeratorAdapter3, refrigeratorAdapter4;
    private View emptyRefrigeratorView;
    private ScrollView scrollView;
    private View emptyFoodView;
    private TextView tvAdd;
    private TextView tvJoin;
    private TextView tvEmptyGuide;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    private RefrigeratorPresenter presenter;
    private List<BaseItem> baseItems1 = new ArrayList<> ();
    private List<BaseItem> baseItems2 = new ArrayList<> ();
    private List<BaseItem> baseItems3 = new ArrayList<> ();
    private List<BaseItem> baseItems4 = new ArrayList<> ();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new RefrigeratorPresenter (getActivity (), this);
        View view = inflater.inflate (R.layout.fragment_refrigerator, container, false);
        initTitleBar (view);
        initView (view);
        setCurrentFridge (0, false);
        setHasOptionsMenu (true);
        return view;
    }

    private OnDrawerOpenListener mOnDrawerOpenListener;

    public interface OnDrawerOpenListener {
        void onDrawerOpen();
    }

    public void setOnDrawerOpenListener(OnDrawerOpenListener onDrawerOpenListener) {
        mOnDrawerOpenListener = onDrawerOpenListener;
    }

    private void initTitleBar(View view) {
        toolbar = view.findViewById (R.id.titlebar);
        if (((AppCompatActivity) getActivity ()) != null) {
            ((AppCompatActivity) getActivity ()).setSupportActionBar (toolbar);

        }
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View pView) {
                if (mOnDrawerOpenListener != null) {
                    mOnDrawerOpenListener.onDrawerOpen ();
                }
            }
        });

        toolbar.setOnMenuItemClickListener (new Toolbar.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.show_ref_list:
                        String ids = Config.getAllFridges (getActivity ());
                        presenter.getFridgeList (ids);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate (R.menu.menu_ref_list, menu);
        super.onCreateOptionsMenu (menu, inflater);
    }

    private void initView(View view) {
        scrollView = view.findViewById (R.id.sl_ref);
        emptyRefrigeratorView = view.findViewById (R.id.empty_refrigerator_view);
        emptyFoodView = view.findViewById (R.id.empty_food_view);
        tvAdd = view.findViewById (R.id.tv_add_refrigerator);
        tvJoin = view.findViewById (R.id.tv_join_refrigerator);
        tvEmptyGuide = view.findViewById (R.id.tv_guide);
        floatingActionButton = view.findViewById (R.id.floating_btn_add);
        rvRefrigerator = view.findViewById (R.id.rv_refrigerator_1);
        rvRefrigerator2 = view.findViewById (R.id.rv_refrigerator_2);
        rvRefrigerator3 = view.findViewById (R.id.rv_refrigerator_3);
        rvRefrigerator4 = view.findViewById (R.id.rv_refrigerator_4);
        refrigeratorAdapter = new RefrigeratorAdapter (getActivity ());
        refrigeratorAdapter2 = new RefrigeratorAdapter (getActivity ());
        refrigeratorAdapter3 = new RefrigeratorAdapter (getActivity ());
        refrigeratorAdapter4 = new RefrigeratorAdapter (getActivity ());
        LinearLayoutManager ms = new LinearLayoutManager (getActivity ());
        ms.setOrientation (LinearLayoutManager.HORIZONTAL);
        rvRefrigerator.setLayoutManager (ms);
        LinearLayoutManager ms2 = new LinearLayoutManager (getActivity ());
        ms2.setOrientation (LinearLayoutManager.HORIZONTAL);
        rvRefrigerator2.setLayoutManager (ms2);
        LinearLayoutManager ms3 = new LinearLayoutManager (getActivity ());
        ms3.setOrientation (LinearLayoutManager.HORIZONTAL);
        rvRefrigerator3.setLayoutManager (ms3);
        LinearLayoutManager ms4 = new LinearLayoutManager (getActivity ());
        ms4.setOrientation (LinearLayoutManager.HORIZONTAL);
        rvRefrigerator4.setLayoutManager (ms4);
        rvRefrigerator.setAdapter (refrigeratorAdapter);
        rvRefrigerator2.setAdapter (refrigeratorAdapter2);
        rvRefrigerator3.setAdapter (refrigeratorAdapter3);
        rvRefrigerator4.setAdapter (refrigeratorAdapter4);

        refrigeratorAdapter.setOnListItemClickListener (new RefrigeratorAdapter.OnListItemClickListener () {
            @Override
            public void onItemClick(GetFoodListRes foodBean) {
                Intent intent = new Intent (getActivity (), FoodDetailActivity.class);
                intent.putExtra ("foodId", foodBean.getFoodId ());
                startActivity (intent);
            }
        });
        refrigeratorAdapter2.setOnListItemClickListener (new RefrigeratorAdapter.OnListItemClickListener () {
            @Override
            public void onItemClick(GetFoodListRes foodBean) {
                Intent intent = new Intent (getActivity (), FoodDetailActivity.class);
                intent.putExtra ("foodId", foodBean.getFoodId ());
                startActivity (intent);
            }
        });
        refrigeratorAdapter3.setOnListItemClickListener (new RefrigeratorAdapter.OnListItemClickListener () {
            @Override
            public void onItemClick(GetFoodListRes foodBean) {
                Intent intent = new Intent (getActivity (), FoodDetailActivity.class);
                intent.putExtra ("foodId", foodBean.getFoodId ());
                startActivity (intent);
            }
        });
        refrigeratorAdapter4.setOnListItemClickListener (new RefrigeratorAdapter.OnListItemClickListener () {
            @Override
            public void onItemClick(GetFoodListRes foodBean) {
                Intent intent = new Intent (getActivity (), FoodDetailActivity.class);
                intent.putExtra ("foodId", foodBean.getFoodId ());
                startActivity (intent);
            }
        });

        floatingActionButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity (), AddFoodActivity.class);
                startActivity (intent);
            }
        });

        presenter.getFoodList (Config.getCurrentFridgeId (getActivity ()));
        tvAdd.setOnClickListener (this);

        tvJoin.setOnClickListener (this);
    }

    public void setCurrentFridge(int id, boolean isUpdate) {
        User user = Config.getUser (getActivity ());

        if (user != null && TextUtils.isEmpty (user.getCreateByFridgeIds ())) {
            emptyRefrigeratorView.setVisibility (View.VISIBLE);
            toolbar.setVisibility (View.GONE);
            floatingActionButton.hide ();
            tvAdd.setVisibility (View.VISIBLE);
            tvJoin.setVisibility (View.VISIBLE);
        } else {
            //获取冰箱食物列表
            emptyRefrigeratorView.setVisibility (View.GONE);
            toolbar.setVisibility (View.VISIBLE);
            floatingActionButton.show ();
            tvAdd.setVisibility (View.GONE);
            tvJoin.setVisibility (View.GONE);
            if (isUpdate && id != 0) {
                presenter.getFoodList (id);
            } else {
                presenter.getFoodList (Config.getCurrentFridgeId (getActivity ()));
            }

        }
    }

    @Override
    public void updateFridgeList(List<RefrigeratorListRes> refrigeratorModelList) {
        DialogUtil.showSingleChoiceDialog (getContext (), refrigeratorModelList, new DialogUtil.OnSingleChoiceClickListener () {
            @Override
            public void onSingleChoiceClick(RefrigeratorListRes model, int which) {
                Config.setCurrentFridgeId (getActivity (), model.getFridgeId ());
                setCurrentFridge(model.getFridgeId (),true);
            }
        });
    }

    @Override
    public void updateFoodList(List<GetFoodListRes> foodListResList) {
        updateList (foodListResList);
    }

    @Override
    public void onError(String result) {

    }

    public void updateList(List<GetFoodListRes> list) {
        List<GetFoodListRes> foodVeg = new ArrayList<> ();  //水果蔬菜，type==1
        List<GetFoodListRes> foodMeat = new ArrayList<> (); //海鲜肉类，type==2
        List<GetFoodListRes> foodCake = new ArrayList<> ();  //熟食糕点，type==3
        List<GetFoodListRes> foodOther = new ArrayList<> ();  //其他，type==4

        if (list != null && list.size () > 0) {
            baseItems1.clear ();
            baseItems2.clear ();
            baseItems3.clear ();
            baseItems4.clear ();
            showEmptyView (false);
            for (GetFoodListRes foodListRes : list) {
                if (foodListRes.getFoodType () == 1) {
                    foodVeg.add (foodListRes);
                } else if (foodListRes.getFoodType () == 2) {
                    foodMeat.add (foodListRes);
                } else if (foodListRes.getFoodType () == 3) {
                    foodCake.add (foodListRes);
                } else if (foodListRes.getFoodType () == 4) {
                    foodOther.add (foodListRes);
                }
            }
            if (foodVeg.size () > 0) {
                for (GetFoodListRes foodListRes : foodVeg) {
                    baseItems1.add (new FoodItem (TYPE_FOOD, foodListRes));
                }
                refrigeratorAdapter.updateList (baseItems1);
            }
            if (foodMeat.size () > 0) {
                for (GetFoodListRes foodListRes : foodMeat) {
                    baseItems2.add (new FoodItem (TYPE_FOOD, foodListRes));
                }
                refrigeratorAdapter2.updateList (baseItems2);
            }

            if (foodCake.size () > 0) {
                for (GetFoodListRes foodListRes : foodCake) {
                    baseItems3.add (new FoodItem (TYPE_FOOD, foodListRes));
                }
                refrigeratorAdapter3.updateList (baseItems3);
            }

            if (foodOther.size () > 0) {
                for (GetFoodListRes foodListRes : foodOther) {
                    baseItems4.add (new FoodItem (TYPE_FOOD, foodListRes));
                }
                refrigeratorAdapter4.updateList (baseItems4);
            }
        } else {
            showEmptyView (true);
        }
    }

    private void showEmptyView(boolean isShowEmpty) {
        if (isShowEmpty) {
            emptyFoodView.setVisibility (View.VISIBLE);
            scrollView.setVisibility (View.GONE);
            if (getActivity () != null) {
                tvEmptyGuide.setText (getActivity ().getResources ().getText (R.string.empty_food_guide));
                tvAdd.setVisibility (View.GONE);
            }

        } else {
            emptyFoodView.setVisibility (View.GONE);
            scrollView.setVisibility (View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId ()) {
            case R.id.tv_add_refrigerator:
                intent = new Intent (getActivity (), JoinActivity.class);
                startActivity (intent);
                break;
            case R.id.tv_join_refrigerator:
                intent = new Intent (getActivity (), JoinActivity.class);
                startActivity (intent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
    }

    public RefrigeratorFragment() {
    }

}
