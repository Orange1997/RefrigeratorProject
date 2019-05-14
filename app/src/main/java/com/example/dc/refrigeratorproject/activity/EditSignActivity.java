package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.util.ToastUtil;

import static com.example.dc.refrigeratorproject.config.Config.KEY_EDIT_SIGN_CONTENT;

/**
 * Created by DC on 2019/5/12.
 */

public class EditSignActivity extends BaseActivity {
    private EditText etInput;
    private TextView tvSize;
    public static final int RESULT_CODE_EDIT_SIGN = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit_sign);
        initView ();
    }

    private void initView() {
        findViewById (R.id.tv_cancel).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        etInput = findViewById (R.id.et_input);
        tvSize = findViewById (R.id.tv_size);
        TextView tvTitle = findViewById (R.id.tv_title);
        etInput.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length () == 50) {
                    ToastUtil.showShort (EditSignActivity.this, "字数不能超过50");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                tvSize.setText (s.length () + "/50");
            }
        });
        findViewById (R.id.tv_send).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty (etInput.getText ().toString ().trim ())) {
                    Intent intent = new Intent ();
                    intent.putExtra (KEY_EDIT_SIGN_CONTENT,etInput.getText ().toString ().trim ());
                    setResult (RESULT_CODE_EDIT_SIGN, intent);
                    finish ();
                } else {
                    finish ();
                }
            }
        });
    }
}
