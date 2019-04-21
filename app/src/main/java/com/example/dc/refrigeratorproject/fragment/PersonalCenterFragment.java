package com.example.dc.refrigeratorproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.activity.PersonalInfoActivity;
import com.example.dc.refrigeratorproject.adapter.MenuAdapter;
import com.example.dc.refrigeratorproject.model.MenuModel;

/**
 * Created by DC on 2019/3/8.
 */

public class PersonalCenterFragment extends Fragment {
    private static final String TAG = "MyFragment1";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_personal_center, container, false);
        initView (view);
        return view;
    }

    private void initView(View view) {
        RecyclerView rvMenu = (RecyclerView) view.findViewById (R.id.rv_menu);
        MenuAdapter menuAdapter = new MenuAdapter (getActivity ());
        rvMenu.setLayoutManager (new LinearLayoutManager (getActivity (), LinearLayout.VERTICAL, false));
        rvMenu.setAdapter (menuAdapter);
        menuAdapter.setOnItemClickListener (new MenuAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(MenuModel.Menu menu) {
                Intent intent;
                intent = new Intent (getActivity (),menu.getCls ());
                startActivity (intent);
            }
        });

        RelativeLayout rlTop = (RelativeLayout) view.findViewById (R.id.rl_top);
        rlTop.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                //跳转到个人中心页
                Intent intent = new Intent (getActivity (), PersonalInfoActivity.class);
                startActivity (intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        Log.i (TAG, "onDestroyView: 0000-------MyFragment1");
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        Log.i (TAG, "onDestroy: 0000--------MyFragment1");
    }

    public PersonalCenterFragment() {
    }
}
