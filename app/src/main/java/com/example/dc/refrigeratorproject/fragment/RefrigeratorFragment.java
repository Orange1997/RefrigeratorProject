package com.example.dc.refrigeratorproject.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.RefrigeratorAdapter;
import com.example.dc.refrigeratorproject.model.FoodBean;
import com.example.dc.refrigeratorproject.model.RefrigeratorBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */

public class RefrigeratorFragment extends Fragment {
    private RecyclerView rvRefrigerator;
    private RefrigeratorAdapter refrigeratorAdapter;
    private View emptyRefrigeratorView;
    private View emptyFoodView;
    private View inviteView;
    private TextView tvAdd;
    private TextView tvEmptyGuide;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_refrigerator, container, false);
        initTitleBar (view);
        initView (view);
        initData();

        return view;
    }

    private void initTitleBar(View view) {
        toolbar = view.findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View pView) {
                // TODO: 添加抽屉
            }
        });
    }

    private void initView(View view) {
        emptyRefrigeratorView = (View) view.findViewById (R.id.empty_refrigerator_view);
        emptyFoodView = (View) view.findViewById (R.id.empty_food_view);
        inviteView = (View)view.findViewById (R.id.rl_invite);
        tvAdd = (TextView) view.findViewById (R.id.tv_add_refrigerator);
        tvEmptyGuide = (TextView) view.findViewById (R.id.tv_guide);
        floatingActionButton = (FloatingActionButton) view.findViewById (R.id.floating_btn_add);
        rvRefrigerator = (RecyclerView) view.findViewById (R.id.rv_refrigerator);
        refrigeratorAdapter = new RefrigeratorAdapter (getActivity ());
        rvRefrigerator.setLayoutManager (new LinearLayoutManager (getActivity (), LinearLayout.VERTICAL, false));
        rvRefrigerator.setAdapter (refrigeratorAdapter);
    }

    private void initData() {
//        //todo:显示创建冰箱页面
//        if (本地不存在冰箱list){
//            emptyRefrigeratorView.setVisibility (View.VISIBLE);
//            rvRefrigerator.setVisibility (View.GONE);
//            toolbar.setVisibility (View.GONE);
//            floatingActionButton.hide();
//            tvAdd.setVisibility (View.VISIBLE);
//            tvAdd.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View v) {
//                    //todo:跳转到创建页
//                    ToastUtil.showShort (getContext (), "rrr");
//                }
//            });
//        }else {
//            //获取冰箱食物列表
//            emptyRefrigeratorView.setVisibility (View.GONE);
//            toolbar.setVisibility (View.VISIBLE);
//            floatingActionButton.show ();
//            tvAdd.setVisibility (View.GONE);
//            updateList ();
//        }

            emptyRefrigeratorView.setVisibility (View.GONE);
            toolbar.setVisibility (View.VISIBLE);
            floatingActionButton.show ();
            tvAdd.setVisibility (View.GONE);
            updateList ();

    }

    private void updateList() {
        List<RefrigeratorBean> list = new ArrayList<> ();
        List<FoodBean> foodBeanList = new ArrayList<> ();
        for (int i = 0; i < 10; i++) {
            foodBeanList.add (new FoodBean (R.drawable.ic_qq));
        }

        for (int i = 0; i < 4; i++) {
//            list.add (new RefrigeratorBean ("果蔬", foodBeanList));
        }

        if (list != null && list.size () > 0) {
            showEmptyView (false);
            refrigeratorAdapter.updateList (list);
            refrigeratorAdapter.notifyDataSetChanged ();
        } else {
            showEmptyView (true);
        }

    }

    private void showEmptyView(boolean isShowEmpty) {
        if (isShowEmpty) {
            emptyFoodView.setVisibility (View.VISIBLE);
            rvRefrigerator.setVisibility (View.GONE);
            if (getActivity ()!=null){
                tvEmptyGuide.setText (getActivity ().getResources ().getText (R.string.empty_food_guide));
                tvAdd.setVisibility (View.GONE);
            }

        } else {
            emptyFoodView.setVisibility (View.GONE);
            rvRefrigerator.setVisibility (View.VISIBLE);
//            if (当前冰箱不存在其他家人列表){
//                inviteView.setVisibility (View.VISIBLE);
//            }else {
//                inviteView.setVisibility (View.GONE);
//            }
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
