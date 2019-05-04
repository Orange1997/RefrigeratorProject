package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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

import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_ADDRESS;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_IS_TO_CREATE;
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
    private TextView tvReAddress, tvReName, tvCreator,tvEmpty;
    private Button btnBottom;
    private boolean isCreator = false;
    private boolean isNewCreate;

    public static final int REQUEST_CODE = 1;
    public static final int RESULT_CODE_EDIT_NAME = 101;
    public static final int RESULT_CODE_ADDRESS = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_refrigerator_info);

        refrigeratorModel = (RefrigeratorModel) getIntent ().getSerializableExtra (KEY_REFRIGERATOR_MODEL);
        isNewCreate = getIntent ().getBooleanExtra (KEY_REFRIGERATOR_IS_TO_CREATE, false);
        if (refrigeratorModel != null && refrigeratorModel.getCreator () != null) {
            if (refrigeratorModel.getCreator ().getAccount () == Config.getUserAccount (RefrigeratorInfoActivity.this)) {
                isCreator = true;
            }
        }

        initTitleBar ();
        initView ();
    }

    private void initTitleBar() {
        toolbar = findViewById (R.id.titlebar);
        TextView title = findViewById (R.id.title);
        if (isNewCreate) {
            title.setText ("创建冰箱");
        } else {
            title.setText ("冰箱信息");
            setSupportActionBar (toolbar);
            toolbar.setOnMenuItemClickListener (new Toolbar.OnMenuItemClickListener () {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId ()) {
                        case R.id.delete_refrigerator:
                            DialogUtil.showNormalDialog (RefrigeratorInfoActivity.this,
                                    "删除后所有共享该冰箱的账户都无法管理并清除该冰箱，确定要删除吗?", new DialogUtil.OnPositiveClickListener () {
                                        @Override
                                        public void onPositiveClick() {
                                            //todo:调用删除冰箱管理接口
                                            //删除或退出成功后将跳转到首页，并自动切换为冰箱列表的第一个冰箱，若无其他冰箱了，将会显示添加冰箱界面
                                            Intent intent = new Intent (RefrigeratorInfoActivity.this, MainActivity.class);
                                            startActivity (intent);
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                            break;
                        case R.id.exit_refrigerator:
                            DialogUtil.showNormalDialog (RefrigeratorInfoActivity.this,
                                    "退出后你将无法看到并管理该冰箱，确定要退出吗?", new DialogUtil.OnPositiveClickListener () {
                                        @Override
                                        public void onPositiveClick() {
                                            //todo:调用退出冰箱管理接口
                                            //删除或退出成功后将跳转到首页，并自动切换为冰箱列表的第一个冰箱，若无其他冰箱了，将会显示添加冰箱界面
                                            Intent intent = new Intent (RefrigeratorInfoActivity.this, MainActivity.class);
                                            startActivity (intent);
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                            break;
                    }
                    return true;
                }
            });
        }

        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View pView) {
                finish ();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isCreator) {
            getMenuInflater ().inflate (R.menu.menu_delete, menu);
        } else {
            getMenuInflater ().inflate (R.menu.menu_exit, menu);
        }

        return true;
    }

    private void initView() {
        tvReName = findViewById (R.id.et_re_name);
        tvReAddress = findViewById (R.id.et_address);
        tvCreator = findViewById (R.id.et_re_creator);
        btnBottom = findViewById (R.id.btn_bottom);
        tvEmpty = findViewById (R.id.empty);

        rvRefrigeratorSharer = findViewById (R.id.rv_refrigerator_sharer);
        LinearLayoutManager layoutManager = new LinearLayoutManager (this);
        layoutManager.setOrientation (OrientationHelper.VERTICAL);
        rvRefrigeratorSharer.setLayoutManager (layoutManager);
        adapter = new RefrigeratorSharerAdapter (RefrigeratorInfoActivity.this);
        rvRefrigeratorSharer.setAdapter (adapter);

        if (isNewCreate) {
            isCreator = true;
            tvReName.setText ("请输入冰箱名称");
            tvReAddress.setText ("请选择冰箱所在地区");
            findViewById (R.id.rl_creator).setVisibility (View.GONE);
            findViewById (R.id.rl_other_sharer).setVisibility (View.GONE);
            tvEmpty.setVisibility (View.GONE);
            rvRefrigeratorSharer.setVisibility (View.GONE);
            btnBottom.setVisibility (View.VISIBLE);
            btnBottom.setText ("完成");
        } else {
            findViewById (R.id.rl_creator).setVisibility (View.VISIBLE);
            rvRefrigeratorSharer.setVisibility (View.VISIBLE);
            adapter.setOnDeleteClickListener (new RefrigeratorSharerAdapter.OnDeleteClickListener () {
                @Override
                public void onDelete(RefrigeratorSharerModel model) {
                    DialogUtil.showNormalDialog (RefrigeratorInfoActivity.this, "确定要移除该账号吗？", new DialogUtil.OnPositiveClickListener () {
                        @Override
                        public void onPositiveClick() {
                            //todo:调用删除共享用户接口
                            ToastUtil.showShort (RefrigeratorInfoActivity.this, "确定");
                        }

                        @Override
                        public void onNegativeClick() {

                        }
                    });
                    rvRefrigeratorSharer.closeMenu ();
                }
            });

            if (isCreator) {
                btnBottom.setVisibility (View.VISIBLE);
                rvRefrigeratorSharer.setToSlide (true);
            } else {
                btnBottom.setVisibility (View.GONE);
                rvRefrigeratorSharer.setToSlide (false);
            }
            updateList (refrigeratorModel);
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
            if (model.getCreator () != null) {
                if (model.getCreator ().getAccount () != 0 && model.getCreator ().getName () != null) {
                    if (model.getCreator ().getAccount () == Config.getUserAccount (RefrigeratorInfoActivity.this)) {
                        tvCreator.setText ("我");
                    } else {
                        tvCreator.setText (model.getCreator ().getName ());
                    }
                }
            }

            if (model.getSharerModelList () != null && model.getSharerModelList ().size () > 0) {
                tvEmpty.setVisibility (View.GONE);
                rvRefrigeratorSharer.setVisibility (View.VISIBLE);
                adapter.updateList (model.getSharerModelList ());
            }else {
                tvEmpty.setVisibility (View.VISIBLE);
                rvRefrigeratorSharer.setVisibility (View.GONE);
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
                    Intent intent = new Intent (RefrigeratorInfoActivity.this, EditNameActivity.class);
                    if (refrigeratorModel!=null&& !TextUtils.isEmpty (refrigeratorModel.getName ())){
                        intent.putExtra (KEY_REFRIGERATOR_NAME, refrigeratorModel.getName ());
                    }else {
                        intent.putExtra (KEY_REFRIGERATOR_NAME, "");
                    }
                    startActivityForResult (intent, REQUEST_CODE);
                }
                break;
            case R.id.et_address:
                if (!isCreator) {
                    ToastUtil.showShort (RefrigeratorInfoActivity.this, "您没有权限编辑该冰箱");
                } else {
                    //跳转到地址编辑页
                    Intent intent = new Intent (RefrigeratorInfoActivity.this, AddressSelectorActivity.class);
                    startActivityForResult (intent, REQUEST_CODE);
                }
                break;
            case R.id.btn_bottom:

                if (isNewCreate){
                    ToastUtil.showShort (RefrigeratorInfoActivity.this, "冰箱创建成功");
                    Intent intent = new Intent (RefrigeratorInfoActivity.this,MainActivity.class);
                    startActivity (intent);
                }else {
                    //跳转到邀请好友页
                    Intent intent = new Intent (RefrigeratorInfoActivity.this, InviteActivity.class);
                    if (refrigeratorModel!=null&& !TextUtils.isEmpty (refrigeratorModel.getName ())){
                        intent.putExtra (KEY_REFRIGERATOR_NAME, refrigeratorModel.getName ());
                    }else {
                        intent.putExtra (KEY_REFRIGERATOR_NAME, "");
                    }
                    startActivity (intent);
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume ();
        //todo:更新数据
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent != null) {
            if (resultCode == RESULT_CODE_EDIT_NAME) {
                String result = intent.getStringExtra (KEY_REFRIGERATOR_NAME);
                tvReName.setText (result);
            } else if (resultCode == RESULT_CODE_ADDRESS) {
                String result = intent.getStringExtra (KEY_REFRIGERATOR_ADDRESS);
                tvReAddress.setText (result);
            }

        }
    }

}