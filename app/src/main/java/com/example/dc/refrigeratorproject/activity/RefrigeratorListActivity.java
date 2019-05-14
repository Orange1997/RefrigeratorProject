package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.Mock;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.RefrigeratorListAdapter;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorModel;
import com.example.dc.refrigeratorproject.myView.NoScrollRecyclerView;

import java.util.List;

import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_IS_TO_CREATE;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_MODEL;

/**
 * Created by DC on 2019/5/2.
 */

public class RefrigeratorListActivity extends BaseActivity {
    private Toolbar toolbar;
    private NoScrollRecyclerView rvMyRefList;
    private RefrigeratorListAdapter adapter;
    private TextView tvJoin, tvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_refrigerator_list);


        initTitleBar ();
        initView ();
        List<RefrigeratorModel> refrigeratorModel = Mock.getRefrigeratorList ();
        updateList (refrigeratorModel);
    }

    private void initTitleBar() {
        toolbar = findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View pView) {
                finish ();
            }
        });

    }

    private void initView() {
        rvMyRefList = findViewById (R.id.rv_refrigerator_list);
        tvJoin = findViewById (R.id.tv_join);
        tvAdd = findViewById (R.id.tv_add);
        LinearLayoutManager layoutManager = new LinearLayoutManager (this);
        layoutManager.setOrientation (OrientationHelper.VERTICAL);
        rvMyRefList.setLayoutManager (layoutManager);
        adapter = new RefrigeratorListAdapter (RefrigeratorListActivity.this);
        rvMyRefList.setAdapter (adapter);

        adapter.setOnRefItemClickListener (new RefrigeratorListAdapter.OnRefItemClickListener () {
            @Override
            public void onItemClick(RefrigeratorModel model) {
                Intent intent = new Intent (RefrigeratorListActivity.this, RefrigeratorInfoActivity.class);
                intent.putExtra (KEY_REFRIGERATOR_MODEL, model);
                startActivity (intent);
            }
        });

        tvAdd.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //TODO:跳转到添加冰箱页
                Intent intent = new Intent (RefrigeratorListActivity.this, RefrigeratorInfoActivity.class);
                intent.putExtra (KEY_REFRIGERATOR_IS_TO_CREATE,true);
                startActivity (intent);
            }
        });

        tvJoin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (RefrigeratorListActivity.this, JoinActivity.class);
                startActivity (intent);
            }
        });

    }

    private void updateList(List<RefrigeratorModel> refrigeratorModelList) {
        adapter.updateList (refrigeratorModelList);
    }
}
