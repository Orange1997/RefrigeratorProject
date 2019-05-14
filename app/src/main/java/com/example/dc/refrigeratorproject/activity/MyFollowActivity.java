package com.example.dc.refrigeratorproject.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dc.refrigeratorproject.Mock;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.FollowAdapter;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.FollowItem;
import com.example.dc.refrigeratorproject.model.FollowModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.FollowAdapter.TYPE_FOLLOW;

/**
 * Created by DC on 2019/5/14.
 */

public class MyFollowActivity extends BaseActivity {
    private FollowAdapter adapter;
    private List<BaseItem> baseItems = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_my_follow);

        initView ();
        updateList ();
    }

    private void initView() {
        Toolbar toolbar = findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        RecyclerView recyclerView = findViewById (R.id.rv_detail);
        LinearLayoutManager layoutManager = new LinearLayoutManager (this);
        layoutManager.setOrientation (OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager (layoutManager);
        adapter = new FollowAdapter (this);
        recyclerView.setAdapter (adapter);
        adapter.setOnCancelFollowClickListener (new FollowAdapter.OnCancelFollowClickListener () {
            @Override
            public void onCancelFollow(FollowItem item, int position) {
                if (!item.isFollow){
                    item.isFollow=true;
                    item.model.setFollow (true);
                    adapter.notifyItemChanged (position);
                }else {
                    showNormalDialog(item,position);
                }
            }
        });

    }

    private void updateList() {
        baseItems.clear ();
        for (FollowModel followModel : Mock.getFollowModels ()) {
            baseItems.add (new FollowItem (followModel, TYPE_FOLLOW));
        }
        adapter.updateList (baseItems);
    }

    private void showNormalDialog(final FollowItem item, final int pos){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MyFollowActivity.this);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("确定要取消关注吗?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        item.model.setFollow (false);
                        item.isFollow = false;
                        adapter.notifyItemChanged (pos);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                });
        // 显示
        normalDialog.show();
    }
}
