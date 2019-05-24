package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.INoticeDetailView;
import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/25.
 */

public class NoticeDetailPresenter extends BasePresenter {
    private INoticeDetailView iNoticeView;

    public NoticeDetailPresenter(Context mContext, INoticeDetailView iView) {
        super (mContext);
        iNoticeView = iView;
    }

    public void addNoticeCollection(int noticeId, String noticeTitle, String noticeImgUr, String noticeUrl, String createTime, String author, int type,int userId) {
        mCompositeSubscription.add (manager.addNoticeCollection (noticeId, noticeTitle, noticeImgUr, noticeUrl, createTime, author, type,userId)
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
                    public void onNext(String s) {
                        iNoticeView.onCollectSuccess (s);
                    }
                })
        );
    }

    public void getCollectedNotice() {
        mCompositeSubscription.add (manager.getCollectedNotice ()
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<List<NoticeRes>> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace ();

                    }

                    @Override
                    public void onNext(List<NoticeRes> s) {
                      iNoticeView.getCollects (s);
                    }
                })
        );
    }

    @Override
    public void onStop() {
        super.onStop ();
    }
}
