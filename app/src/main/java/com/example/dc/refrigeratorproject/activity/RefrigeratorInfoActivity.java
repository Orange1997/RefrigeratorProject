package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.RefrigeratorSharerAdapter;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorModel;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorSharerModel;
import com.example.dc.refrigeratorproject.util.DialogUtil;
import com.example.dc.refrigeratorproject.util.ToastUtil;
import com.example.dc.refrigeratorproject.view.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.activity.EditNameActivity.REQUEST_CODE;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_MODEL;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_NAME;

/**
 * Created by DC on 2019/5/2.
 */

public class RefrigeratorInfoActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private SlideRecyclerView rvRefrigeratorSharer;
    private RefrigeratorSharerAdapter adapter;
    private RefrigeratorModel refrigeratorModel;
    private TextView tvReAddress, tvReName, tvInvite, tvCreator;
    private Button btnBottom;
    private boolean isCreator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_refrigerator_info);

        refrigeratorModel = (RefrigeratorModel) getIntent ().getSerializableExtra (KEY_REFRIGERATOR_MODEL);
        if (refrigeratorModel.getCreatorAccount () == Config.getUserAccount (RefrigeratorInfoActivity.this)) {
            isCreator = true;
        }

        initTitleBar ();
        initView ();
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
        tvReName = findViewById (R.id.et_re_name);
        tvReAddress = findViewById (R.id.et_address);
        tvInvite = findViewById (R.id.tv_to_invite);
        tvCreator = findViewById (R.id.et_re_creator);
        btnBottom = findViewById (R.id.btn_bottom);

        rvRefrigeratorSharer = findViewById (R.id.rv_refrigerator_sharer);
        LinearLayoutManager layoutManager = new LinearLayoutManager (this);
        layoutManager.setOrientation (OrientationHelper.VERTICAL);
        rvRefrigeratorSharer.setLayoutManager (layoutManager);
        adapter = new RefrigeratorSharerAdapter (RefrigeratorInfoActivity.this);
        rvRefrigeratorSharer.setAdapter (adapter);

        adapter.setOnDeleteClickListener (new RefrigeratorSharerAdapter.OnDeleteClickListener () {
            @Override
            public void onDelete(RefrigeratorSharerModel model) {
                DialogUtil.showNormalDialog (RefrigeratorInfoActivity.this, "确定要移除该账号吗？", new DialogUtil.OnPositiveClickListener () {
                    @Override
                    public void onPositiveClick() {
                        //todo:调用删除共享用户接口
                        ToastUtil.showShort (RefrigeratorInfoActivity.this,"确定");
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });
                rvRefrigeratorSharer.closeMenu ();
            }
        });

        if (isCreator) {
            btnBottom.setText ("删除冰箱");
            tvInvite.setVisibility (View.VISIBLE);
            tvInvite.setOnClickListener (this);
            rvRefrigeratorSharer.setToSlide (true);
        } else {
            btnBottom.setText ("退出管理");
            tvInvite.setVisibility (View.GONE);
            rvRefrigeratorSharer.setToSlide (false);
        }

        tvReName.setOnClickListener (this);
        tvReAddress.setOnClickListener (this);
        btnBottom.setOnClickListener (this);
    }

    private void updateList(RefrigeratorModel model) {
        if (model != null) {
            if (model.getName () != null) {
                tvReName.setText (model.getName ());
            }
            if (model.getAddress () != null) {
                tvReAddress.setText (model.getAddress ());
            }
            if (model.getCreatorAccount () != 0 && model.getCreatorName () != null) {
                if (model.getCreatorAccount () == Config.getUserAccount (RefrigeratorInfoActivity.this)) {
                    tvCreator.setText ("我");
                } else {
                    tvCreator.setText (model.getCreatorName ());
                }
            }
            if (model.getSharerModelList () != null && model.getSharerModelList ().size () > 0) {
                List<RefrigeratorSharerModel> list = model.getSharerModelList ();
                List<RefrigeratorSharerModel> newList = new ArrayList<> ();
                for (RefrigeratorSharerModel sharerModel : list) {
                    //去除本人数据
                    if (sharerModel.getSharerAccount () != Config.getUserAccount (RefrigeratorInfoActivity.this)) {
                        newList.add (sharerModel);
                    }
                }
                for (RefrigeratorSharerModel sharerModel : newList) {
                    //排序，创建者放第一个
                    if (sharerModel.isCreator ()) {
                        newList.remove (sharerModel);
                        newList.add (0, sharerModel);
                    }
                }
                adapter.updateList (newList);
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.et_re_name:
                if (!isCreator) {
                    ToastUtil.showShort (RefrigeratorInfoActivity.this, "您没有权限编辑该冰箱");
                } else {
                    //todo:跳转到名称编辑页
                    Intent intent = new Intent (RefrigeratorInfoActivity.this,EditNameActivity.class);
//                    intent.putExtra (KEY_REFRIGERATOR_NAME,refrigeratorModel.getName());
                    intent.putExtra (KEY_REFRIGERATOR_NAME,tvReName.getText ());
                    startActivityForResult (intent,REQUEST_CODE);
                }
                break;
            case R.id.et_address:
                if (!isCreator) {
                    ToastUtil.showShort (RefrigeratorInfoActivity.this, "您没有权限编辑该冰箱");
                } else {
                    //todo:跳转到地址编辑页
                    ToastUtil.showShort (RefrigeratorInfoActivity.this, "跳转到地址编辑页");
                }
                break;
            case R.id.tv_to_invite:
                //todo:跳转到邀请好友页
                ToastUtil.showShort (RefrigeratorInfoActivity.this, "跳转到邀请好友页");
                break;
            case R.id.btn_bottom:
                //todo:调用退出/删除接口
                if (isCreator) {
                    DialogUtil.showNormalDialog (RefrigeratorInfoActivity.this,
                            "删除后所有共享该冰箱的账户都无法管理并清除该冰箱，确定要删除吗?", new DialogUtil.OnPositiveClickListener () {
                                @Override
                                public void onPositiveClick() {
                                    //todo:调用删除冰箱管理接口
                                    //删除或退出成功后将跳转到首页，并自动切换为冰箱列表的第一个冰箱，若无其他冰箱了，将会显示添加冰箱界面
                                    ToastUtil.showShort (RefrigeratorInfoActivity.this, REQUEST_CODE);
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                } else {
                    DialogUtil.showNormalDialog (RefrigeratorInfoActivity.this,
                            "退出后你将无法看到并管理该冰箱，确定要退出吗?", new DialogUtil.OnPositiveClickListener () {
                        @Override
                        public void onPositiveClick() {
                            //todo:调用退出冰箱管理接口
                            //删除或退出成功后将跳转到首页，并自动切换为冰箱列表的第一个冰箱，若无其他冰箱了，将会显示添加冰箱界面
                            ToastUtil.showShort (RefrigeratorInfoActivity.this, "确认");
                        }

                        @Override
                        public void onNegativeClick() {

                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume ();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent!=null){
            String result = intent.getStringExtra (KEY_REFRIGERATOR_NAME);//得到新Activity 关闭后返回的数据
            tvReName.setText (result);
        }
    }

}