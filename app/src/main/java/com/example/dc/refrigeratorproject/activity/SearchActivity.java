package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.view.View;

import com.example.dc.refrigeratorproject.R;

/**
 * Created by DC on 2019/5/12.
 */

public class SearchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_search);
        initView ();
    }

    private void initView() {
        findViewById (R.id.iv_back).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
    }
}
