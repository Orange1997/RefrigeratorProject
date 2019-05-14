package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;

import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_URL;
import static com.example.dc.refrigeratorproject.config.Config.KYE_COMMENT_FROM_WHERE;
import static com.example.dc.refrigeratorproject.config.Config.VALUE_COMMENT_TO_SEND;

/**
 * Created by DC on 2019/5/12.
 */

public class DetailActivity extends BaseActivity implements View.OnClickListener {
    private WebView webView;
    private String url;
    private TextView tvToComment;
    private RelativeLayout comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_details);
        url = getIntent ().getStringExtra (KEY_FOUND_URL);
        initView ();
        updateList ();
    }

    private void initView() {
        tvToComment = findViewById (R.id.tv_to_comment);
        comment = findViewById (R.id.comment);
        Toolbar toolbar = findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View pView) {
                finish ();
            }
        });
        webView = findViewById (R.id.wv_details);
        webView.loadUrl (url);//加载url

        tvToComment.setOnClickListener (this);
        comment.setOnClickListener (this);
    }

    private void updateList() {
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId ()) {
            case R.id.tv_to_comment:
                intent = new Intent (DetailActivity.this, ReportCommentActivity.class);
                intent.putExtra (KYE_COMMENT_FROM_WHERE,VALUE_COMMENT_TO_SEND);
                startActivity (intent);
                break;
            case R.id.comment:
                intent = new Intent (DetailActivity.this, CommentActivity.class);
                startActivity (intent);
                break;
        }
    }


}
