package com.example.dc.refrigeratorproject.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.example.dc.refrigeratorproject.R;

import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_URL;

/**
 * Created by DC on 2019/5/12.
 */

public class DetailActivity extends BaseActivity {
    private WebView webView;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_details);
        url = getIntent ().getStringExtra (KEY_FOUND_URL);
        initView ();
        updateList ();
    }
    private void initView() {
        Toolbar toolbar = findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View pView) {
                finish ();
            }
        });
        webView = findViewById (R.id.wv_details);
        webView.loadUrl(url);//加载url
    }

    private void updateList() {
    }

}
