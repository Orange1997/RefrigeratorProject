package com.example.dc.refrigeratorproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.CommentExpandAdapter;
import com.example.dc.refrigeratorproject.iView.ICommentView;
import com.example.dc.refrigeratorproject.presenter.CommentPresenter;
import com.example.dc.refrigeratorproject.resposeBean.CommentRes;

import java.util.List;

/**
 * Created by DC on 2019/5/12.
 */

public class CommentActivity extends BaseActivity implements ICommentView {
    private static final int REQUEST_CODE_FROM_COMMENT = 1001;
    public static final String KEY_CHILD_POS = "childPosition";
    public static final String KEY_GROUP_POS = "groupPosition";
    private ExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    private CommentPresenter presenter;
    private int noticeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_comment);
        noticeId = getIntent ().getIntExtra ("noticeId", -1);
        presenter = new CommentPresenter (CommentActivity.this, this);
        initView ();
    }

    private void initView() {
        Toolbar toolbar = findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        expandableListView = findViewById (R.id.detail_page_lv_comment);
        expandableListView.setGroupIndicator (null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter (this);
        expandableListView.setAdapter (adapter);
    }

    @Override
    public void addCommentSuccess(String s) {

    }

    @Override
    public void getComment(List<CommentRes> object) {
        if (object != null && object.size () > 0) {
            adapter.updateList (object);
            initExpandableListView (object);
            findViewById (R.id.empty).setVisibility (View.GONE);
            expandableListView.setVisibility (View.VISIBLE);
        } else {
            findViewById (R.id.empty).setVisibility (View.VISIBLE);
            expandableListView.setVisibility (View.GONE);
        }
    }

    @Override
    public void onError(String s) {

    }


    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<CommentRes> commentList) {
        for (int i = 0; i < commentList.size (); i++) {
            expandableListView.expandGroup (i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_FROM_COMMENT:
                if (resultCode == Activity.RESULT_OK) {
                    expandableListView.expandGroup (1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume ();
        if (noticeId > 0) {
            presenter.getComment (noticeId);
        }
    }
}
