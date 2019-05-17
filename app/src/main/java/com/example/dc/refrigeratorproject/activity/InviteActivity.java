package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;

import static com.example.dc.refrigeratorproject.config.Config.KEY_EDIT_NAME;

/**
 * Created by DC on 2019/5/4.
 */

public class InviteActivity extends BaseActivity implements View.OnClickListener{
    private String refName,inviteCode;
    private TextView code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        refName = getIntent ().getStringExtra (KEY_EDIT_NAME);
        inviteCode = getIntent ().getStringExtra ("invite_code");
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

        code = findViewById (R.id.tv_invite_code);
        code.setText (inviteCode);
        code.setTextIsSelectable(true);
    }

    @Override
    public void onClick(View v){

    }
}
