package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.util.ToastUtil;

import static com.example.dc.refrigeratorproject.activity.RefrigeratorInfoActivity.RESULT_CODE_EDIT_NAME;
import static com.example.dc.refrigeratorproject.config.Config.KEY_EDIT_NAME;

/**
 * Created by DC on 2019/5/3.
 */

public class EditNameActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvCancel, tvSave, tvTitle;
    private EditText etName;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit_name);
        name = getIntent ().getStringExtra (KEY_EDIT_NAME);

        initTitleBar ();
        initView ();
//        updateList (refrigeratorModel);
    }

    private void initTitleBar() {
        tvCancel = findViewById (R.id.tv_cancel);
        tvSave = findViewById (R.id.tv_save);
        tvTitle = findViewById (R.id.tv_title);

        tvCancel.setOnClickListener (this);
        tvSave.setOnClickListener (this);
    }

    private void initView() {
        etName = findViewById (R.id.et_name);
        if (!TextUtils.isEmpty (name)) {
            etName.setText (name);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.tv_cancel:
                finish ();
                break;
            case R.id.tv_save:
                if (TextUtils.isEmpty (etName.getText ())) {
                    ToastUtil.showShort (EditNameActivity.this, getResources ().getString (R.string.info_no_empty));
                } else {
                    if (etName.getText ().toString ().length () > 20) {
                        ToastUtil.showShort (EditNameActivity.this, getResources ().getString (R.string.info_no_more_than_twenty));
                    } else {
                        ToastUtil.showShort (EditNameActivity.this, "更新成功");
                        Intent intent = new Intent ();
                        intent.putExtra (KEY_EDIT_NAME, etName.getText ().toString ());
                        setResult (RESULT_CODE_EDIT_NAME, intent);
                        finish ();
                    }
                }
                break;
        }
    }
}
