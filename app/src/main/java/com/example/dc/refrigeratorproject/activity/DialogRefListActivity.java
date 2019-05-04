package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.dc.refrigeratorproject.Mock;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.DialogRefrigeratorListAdapter;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorModel;

import java.util.List;

/**
 * Created by DC on 2019/5/4.
 */

public class DialogRefListActivity extends BaseActivity {

    private RecyclerView rvRef;
    private DialogRefrigeratorListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_ref_list);

        initView();
        List<RefrigeratorModel> list = Mock.getRefrigeratorList ();
        updateList(list);
    }

    private void initView() {
        rvRef = findViewById (R.id.rv_ref);
        LinearLayoutManager layoutManager = new LinearLayoutManager (this);
        layoutManager.setOrientation (OrientationHelper.VERTICAL);
        rvRef.setLayoutManager (layoutManager);
        adapter = new DialogRefrigeratorListAdapter (DialogRefListActivity.this);
        rvRef.setAdapter (adapter);
    }

    private void updateList( List<RefrigeratorModel> list) {
        if (list!=null&&list.size ()>0){
            adapter.updateList (list);
        }
    }
}
