package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.dc.refrigeratorproject.sqLite.DBOpenHelper;

/**
 * Created by DC on 2019/5/2.
 */

public class BaseActivity extends AppCompatActivity {
    protected DBOpenHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        dbOpenHelper = new DBOpenHelper (this);

    }
}
