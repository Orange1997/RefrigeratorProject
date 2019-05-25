package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.ICommentView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.resposeBean.CommentRes;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/25.
 */

public class CommentPresenter extends BasePresenter {
    private ICommentView iCommentView;

    public CommentPresenter(Context mContext, IView iView) {
        super (mContext);
        iCommentView = (ICommentView) iView;
    }

    public void addComment(int noticeId, String content,long createTime) {
        mCompositeSubscription.add (manager.addComment (noticeId, content, createTime)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<String> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();

                    }

                    @Override
                    public void onNext(String res) {
                       if (res!=null){
                           iCommentView.addCommentSuccess (res);
                       }
                    }
                })
        );
    }

    public void getComment(int noticeId) {
        mCompositeSubscription.add (manager.getComment (noticeId)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<List<CommentRes>> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();

                    }

                    @Override
                    public void onNext(List<CommentRes> res) {
                        if (res != null) {
                            iCommentView.getComment (res);
                        }
                    }
                })
        );
    }


    @Override
    public void onStop() {
        super.onStop ();
    }
}