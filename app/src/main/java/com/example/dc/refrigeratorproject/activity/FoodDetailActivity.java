package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.event.UpdateFridgeEvent;
import com.example.dc.refrigeratorproject.iView.IFoodView;
import com.example.dc.refrigeratorproject.presenter.FoodPresenter;
import com.example.dc.refrigeratorproject.resposeBean.FoodDetailRes;
import com.example.dc.refrigeratorproject.util.DialogUtil;
import com.example.dc.refrigeratorproject.util.InputUtils;
import com.example.dc.refrigeratorproject.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by DC on 2019/5/16.
 */

public class FoodDetailActivity extends BaseActivity implements IFoodView {
    private int foodId;
    private FoodPresenter presenter;
    private TextView tvName, tvAmount, tvOutTime, tvRemindTime, tvRemark, tvDeadLine,tvTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_food_detail);
        presenter = new FoodPresenter (FoodDetailActivity.this, this);
        foodId = getIntent ().getIntExtra ("foodId", -1);
        initView ();
    }

    private void initView() {
        Toolbar mToolbar = findViewById (R.id.titlebar);
        setSupportActionBar (mToolbar);
        mToolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        mToolbar.setOnMenuItemClickListener (new Toolbar.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.delete_food:
                        DialogUtil.showNormalDialog (FoodDetailActivity.this,
                                "确定要删除吗?", new DialogUtil.OnPositiveClickListener () {
                                    @Override
                                    public void onPositiveClick() {
                                        //删除或退出成功后将跳转到首页，并自动切换为冰箱列表的第一个冰箱，若无其他冰箱了，将会显示添加冰箱界面
                                        presenter.deleteFood (foodId);
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


        tvName = findViewById (R.id.tv_name);
        tvAmount = findViewById (R.id.tv_amount);
        tvOutTime = findViewById (R.id.tv_deadline_time);
        tvRemindTime = findViewById (R.id.tv_remind_time);
        tvRemark = findViewById (R.id.tv_text);
        tvDeadLine = findViewById (R.id.tv_deadline);
        tvTip = findViewById (R.id.tv_tip);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_delete_food, menu);
        return true;
    }

    @Override
    public void getFoodDetail(final FoodDetailRes res) {
        int daynumber = (int) InputUtils.dateDiff (res.getOutlineTime (), "yyyy-MM-dd");
        tvName.setText (TextUtils.isEmpty (res.getFoodName ()) ? "---" : res.getFoodName ());
        if (res.getFoodCount () < 0) {
            tvAmount.setVisibility (View.GONE);
        } else {
            tvAmount.setVisibility (View.VISIBLE);
            tvAmount.setText ("X " + res.getFoodCount ());
        }

        if (!TextUtils.isEmpty (res.getFoodUnit ())) {
            tvAmount.setText (tvAmount.getText () + res.getFoodUnit ());
        }

        if (res.getOutlineTime () > 0) {
            tvOutTime.setText (InputUtils.getDateToString (res.getOutlineTime (), "yyyy-MM-dd"));
        } else {
            tvOutTime.setText ("---");
        }

        if (res.getRemindTime () > 0) {
            tvRemindTime.setText (InputUtils.getDateToString (res.getRemindTime (), "yyyy-MM-dd HH:mm"));
        } else {
            tvRemindTime.setText ("---");
        }
        if (daynumber<0){
            tvTip.setText ("已过期");
            tvTip.setTextColor (Color.RED);
            findViewById (R.id.tv_day).setVisibility (View.GONE);
        }else {
            tvTip.setText ("距离过期还有");
            tvTip.setTextColor (getResources ().getColor (R.color.tv_color_primary_title));
            tvDeadLine.setText (String.valueOf (daynumber));
            findViewById (R.id.tv_day).setVisibility (View.VISIBLE);
        }

        tvRemark.setText (TextUtils.isEmpty (res.getRemark ()) ? "---" : res.getRemark ());

        findViewById (R.id.btn_edit_food).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (FoodDetailActivity.this,AddFoodActivity.class);
                intent.putExtra ("foodDetail",res);
                startActivity (intent);
            }
        });
    }

    @Override
    public void deleteSuccess(String s){
        ToastUtil.showShort (FoodDetailActivity.this,s);
        EventBus.getDefault ().post (new UpdateFridgeEvent (Config.getCurrentFridgeId (FoodDetailActivity.this)));
        finish ();
    }


    @Override
    public void onResume(){
        super.onResume ();
        presenter.getFoodDetail (foodId);
    }

    @Override
    public void onError(String s) {

    }

}
