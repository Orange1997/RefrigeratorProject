package com.example.dc.refrigeratorproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_refrigerator, container, false);
        initTitleBar(view);
        initView (view);
        return view;
    }

    private void initTitleBar(View view) {
        toolbar = view.findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                // TODO: 添加抽屉
            }
        });
    }

    private void initView(View view) {
        rvRefrigerator = (RecyclerView) view.findViewById (R.id.rv_refrigerator);
        refrigeratorAdapter = new RefrigeratorAdapter (getActivity ());
        rvRefrigerator.setLayoutManager (new LinearLayoutManager (getActivity (),LinearLayout.VERTICAL,false));
        rvRefrigerator.setAdapter (refrigeratorAdapter);
        updateList();
    }

    private void updateList(){
        List<RefrigeratorBean> list = new ArrayList<> ();
        List<FoodBean> foodBeanList = new ArrayList<> ();
        for (int i=0;i<10;i++){
            foodBeanList.add (new FoodBean (R.drawable.ic_qq));
        }

        for (int i = 0;i<4;i++){
            list.add (new RefrigeratorBean ("果蔬",foodBeanList));
        }
        refrigeratorAdapter.updateList (list);
        refrigeratorAdapter.notifyDataSetChanged ();
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
