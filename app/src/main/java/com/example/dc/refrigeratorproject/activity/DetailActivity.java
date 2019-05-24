package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.iView.INoticeDetailView;
import com.example.dc.refrigeratorproject.presenter.NoticeDetailPresenter;
import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;
import com.example.dc.refrigeratorproject.resposeBean.User;
import com.example.dc.refrigeratorproject.util.ToastUtil;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import java.util.List;

import static com.example.dc.refrigeratorproject.config.Config.KEY_FOUND_URL;
import static com.example.dc.refrigeratorproject.config.Config.KYE_COMMENT_FROM_WHERE;
import static com.example.dc.refrigeratorproject.config.Config.VALUE_COMMENT_TO_SEND;

/**
 * Created by DC on 2019/5/12.
 */

public class DetailActivity extends BaseActivity implements View.OnClickListener, INoticeDetailView {
    private WebView webView;
    private String url;
    private TextView tvToComment;
    private RelativeLayout comment;
    private NoticeRes noticeRes;
    private NoticeDetailPresenter presenter;
    private ImageView ivCollect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_details);
        presenter = new NoticeDetailPresenter (DetailActivity.this, this);
        url = getIntent ().getStringExtra (KEY_FOUND_URL);
        noticeRes = (NoticeRes) getIntent ().getSerializableExtra ("notice");
        presenter.getCollectedNotice ();
        initView ();
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
        findViewById (R.id.iv_share).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                onClickShare ();
            }
        });
        ivCollect = findViewById (R.id.iv_collect);
        final User user = Config.getUser (DetailActivity.this);
        ivCollect.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                int userId = -1;
                if (user != null) {
                    userId = user.getUserId ();
                }
                presenter.addNoticeCollection (noticeRes.getNoticeId (), noticeRes.getNoticeTitle (),
                        noticeRes.getNoticeImgUr (), noticeRes.getNoticeUrl (),
                        noticeRes.getCreateTime (), noticeRes.getAuthor (), noticeRes.getNoticeType (), userId);
            }
        });
    }

    @Override
    public void onCollectSuccess(String s) {
        ToastUtil.showShort (DetailActivity.this, s);
        ivCollect.setImageResource (R.drawable.ic_collected);
    }

    @Override
    public void getCollects(List<NoticeRes> noticeResList) {
        if (noticeResList != null && noticeResList.size () > 0) {
            for (NoticeRes noticeRes1 : noticeResList) {
                if (noticeRes1.getNoticeId () == noticeRes.getNoticeId ()) {
                    ivCollect.setImageResource (R.drawable.ic_collected);
                }
            }
        }
    }

    @Override
    public void onError(String s) {

    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object var1) {

        }

        @Override
        public void onError(UiError var1) {
        }

        @Override
        public void onCancel() {

        }
    }

    private void onClickShare() {
        final Bundle params = new Bundle ();
        if (noticeRes != null) {
            params.putInt (QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
            params.putString (QQShare.SHARE_TO_QQ_TITLE, noticeRes.getNoticeTitle ());
            params.putString (QQShare.SHARE_TO_QQ_SUMMARY, noticeRes.getDes ());
            params.putString (QQShare.SHARE_TO_QQ_TARGET_URL, noticeRes.getNoticeUrl ());
            params.putString (QQShare.SHARE_TO_QQ_IMAGE_URL, noticeRes.getNoticeImgUr ());
            params.putString (QQShare.SHARE_TO_QQ_APP_NAME, "冰箱贴");
            params.putInt (QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
            mTencent.shareToQQ (DetailActivity.this, params, new BaseUiListener ());
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId ()) {
            case R.id.tv_to_comment:
                intent = new Intent (DetailActivity.this, ReportCommentActivity.class);
                intent.putExtra (KYE_COMMENT_FROM_WHERE, VALUE_COMMENT_TO_SEND);
                startActivity (intent);
                break;
            case R.id.comment:
                intent = new Intent (DetailActivity.this, CommentActivity.class);
                startActivity (intent);
                break;
        }
    }


}
