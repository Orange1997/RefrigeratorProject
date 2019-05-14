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

import static com.example.dc.refrigeratorproject.activity.CommentActivity.KEY_CHILD_POS;
import static com.example.dc.refrigeratorproject.activity.CommentActivity.KEY_GROUP_POS;
import static com.example.dc.refrigeratorproject.config.Config.KYE_COMMENT_FROM_WHERE;
import static com.example.dc.refrigeratorproject.config.Config.VALUE_COMMENT_TO_SEND;

/**
 * Created by DC on 2019/5/12.
 */

public class ReportCommentActivity extends BaseActivity {
    private EditText etInput;
    private TextView tvSize;
    private String fromWhere;
    private int commentChildPos;
    private int commentGroupPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_report_comment);
        fromWhere = getIntent ().getStringExtra (KYE_COMMENT_FROM_WHERE);
        commentChildPos = getIntent ().getIntExtra (KEY_CHILD_POS,-1);
        commentGroupPos = getIntent ().getIntExtra (KEY_GROUP_POS,-1);
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
        if (fromWhere.equals (VALUE_COMMENT_TO_SEND)){
            tvTitle.setText ("写评论");
        }else {
            tvTitle.setText ("回复评论");
        }
        etInput.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length () == 150) {
                    ToastUtil.showShort (ReportCommentActivity.this, "字数不能超过150");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                tvSize.setText (s.length () + "/150");
            }
        });
        findViewById (R.id.tv_send).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty (etInput.getText ().toString ().trim ())){
                    if (fromWhere.equals (VALUE_COMMENT_TO_SEND)){
                        ToastUtil.showShort (ReportCommentActivity.this, etInput.getText ().toString ().trim ());
                    }else {
                        ToastUtil.showShort (ReportCommentActivity.this, etInput.getText ().toString ().trim ());
                        Intent intent = new Intent ();
                        intent.putExtra (KEY_CHILD_POS,commentChildPos);
                        intent.putExtra (KEY_GROUP_POS,commentGroupPos);
                        setResult (RESULT_OK,intent);
                        finish ();
                    }

                }else {
                    ToastUtil.showShort (ReportCommentActivity.this,"发表内容不能为空");
                }
            }
        });
    }
}
