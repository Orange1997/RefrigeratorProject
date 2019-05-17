package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dc.refrigeratorproject.R;

/**
 * Created by DC on 2019/5/16.
 */

public class FoodDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_food_detail);
        initView ();
    }

    private void initView() {
        Toolbar mToolbar = findViewById (R.id.titlebar);
        mToolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });


    }
}
