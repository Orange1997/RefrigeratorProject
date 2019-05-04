package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.util.ToastUtil;

import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_NAME;

/**
 * Created by DC on 2019/5/4.
 */

public class InviteActivity extends BaseActivity implements View.OnClickListener{
    private String refName;
    private Button btnWe,btnQQ,btnMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        refName = getIntent ().getStringExtra (KEY_REFRIGERATOR_NAME);
        initView();
    }

    private void initView() {
        Toolbar mToolbar = findViewById(R.id.titlebar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tvTip = findViewById (R.id.tip);
        tvTip.setText (String.format(getResources().getString(R.string.info_invite), refName));

        btnWe = findViewById (R.id.tv_invite_by_we);
        btnQQ = findViewById (R.id.tv_invite_by_qq);
        btnMsg = findViewById (R.id.tv_invite_by_msg);

        btnWe.setOnClickListener (this);
        btnQQ.setOnClickListener (this);
        btnMsg.setOnClickListener (this);


    }

    @Override
    public void onClick(View v){
        switch (v.getId ()){
            case R.id.tv_invite_by_we:
                ToastUtil.showShort (InviteActivity.this, "微信邀请");
                break;
            case R.id.tv_invite_by_qq:
                ToastUtil.showShort (InviteActivity.this, "QQ邀请");
                break;
            case R.id.tv_invite_by_msg:
                ToastUtil.showShort (InviteActivity.this, "短信邀请");
                break;
        }
    }
}
