package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dc.refrigeratorproject.R;

/**
 * project: RefrigeratorProject
 * author : Android
 * date : 2019/4/24
 * time : 19:35
 * email : 企业邮箱
 * note : 邀请加入界面
 */
public class JoinActivity extends BaseActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        initView();
        initTitleBar();
    }

    private void initView() {
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

}
