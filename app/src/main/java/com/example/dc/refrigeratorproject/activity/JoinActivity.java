package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.iView.IJoinView;
import com.example.dc.refrigeratorproject.presenter.SharePresenter;
import com.example.dc.refrigeratorproject.util.DialogUtil;
import com.example.dc.refrigeratorproject.util.ToastUtil;

/**
 * project: RefrigeratorProject
 * author : Android
 * date : 2019/4/24
 * time : 19:35
 * email : 企业邮箱
 * note : 邀请加入界面
 */
public class JoinActivity extends BaseActivity implements IJoinView{
    private Toolbar toolbar;
    private SharePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SharePresenter (JoinActivity.this,this);
        setContentView(R.layout.activity_join);
        initView();
        initTitleBar();
    }

    private void initTitleBar() {
        toolbar = findViewById(R.id.titlebar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                finish ();
            }
        });
    }

    private void initView() {
        final EditText etInput = findViewById (R.id.et_input);
        findViewById (R.id.btn_confirm).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty (etInput.getText ())){
                    ToastUtil.showShort (JoinActivity.this,getResources ().getString (R.string.info_no_empty));
                }else {
                    DialogUtil.showNormalDialog (JoinActivity.this, "确认要加入该冰箱吗？", new DialogUtil.OnPositiveClickListener () {
                        @Override
                        public void onPositiveClick() {
                            presenter.addSharedFridge (etInput.getText ().toString (), Config.getUserId (JoinActivity.this));
                        }

                        @Override
                        public void onNegativeClick() {

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onJoinSuccess(String s){
        Config.setSharedFridgeIds (JoinActivity.this,s);
        Intent intent = new Intent (JoinActivity.this,MainActivity.class);
        startActivity (intent);
        finish ();
    }

    @Override
    public void onError(String s){
        ToastUtil.showShort (JoinActivity.this,s);
    }

}
