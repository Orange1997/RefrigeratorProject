package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.dc.refrigeratorproject.R;

/**
 * Created by DC on 2019/5/11.
 */

public class AddListActivity extends BaseActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_food);
        initView ();
    }

    private void initView() {

    }
}
